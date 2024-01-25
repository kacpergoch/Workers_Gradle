package workers;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import workers.controller.BackupService;
import workers.controller.Repository;
import workers.model.Employee;
import workers.model.Salesman;

import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.RandomAccessFile;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class TestBackupService {
    @BeforeEach
    public void setUp(){
        Repository.getEmployees().clear();
    }

    @Nested
    class TestSerialization{
        @BeforeEach
        public void setUp(){
            for (int i = 0; i < 10; i++){
                Salesman salesman = new Salesman(
                        Integer.toString(i),
                        Integer.toString(i),
                        Integer.toString(i),
                        Integer.toString(i),
                        Integer.toString(i),
                        Integer.toString(i),
                        Integer.toString(i)
                );
                Repository.addEmployee(salesman);
            }
        }

        @ParameterizedTest
        @ValueSource(booleans = {true, false})
        public void CorrectParallelSerialization(boolean flagCompressionType){
            String dirName = "TestSerialization";
            BackupService.start(true, flagCompressionType, dirName);

            String fileExtension;
            if (flagCompressionType) {
                fileExtension = ".gz";
            }
            else{
                fileExtension = ".zip";
            }

            assertTrue(new File(dirName).exists());
            for(Employee employee : Repository.getEmployees()){
                assertTrue(new File(dirName + "/" + employee.getPesel() + fileExtension).exists());
            }
        }
    }

    @Nested
    class TestDeserialization{

        private void corruptFile(String filePath){
            try {
                FileWriter writer = new FileWriter(filePath);
                writer.close();
            } catch (Exception e){
                System.out.println("Could not write to file");
            }
        }

        @BeforeEach
        public void setUp(){
            for (int i = 0; i < 10; i++){
                Salesman salesman = new Salesman(
                        Integer.toString(i),
                        Integer.toString(i),
                        Integer.toString(i),
                        Integer.toString(i),
                        Integer.toString(i),
                        Integer.toString(i),
                        Integer.toString(i)
                );
                Repository.addEmployee(salesman);
            }
            BackupService.start(true, true, "TestDeserializationGzip");
            BackupService.start(true, false, "TestDeserializationZip");
        }

        @ParameterizedTest
        @ValueSource(strings = {"TestDeserializationGzip", "TestDeserializationZip"})
        public void SuccessfulParallelDeserialization(String dirName){
            List<Employee> before = Repository.getEmployees();
            Repository.getEmployees().clear();

            BackupService.start(false, false, dirName);
            for (int i = 0; i < before.size(); i++) {
                assertEquals(before.get(i), Repository.getEmployees().get(i));
            }
        }

        @ParameterizedTest
        @ValueSource(strings = {"TestDeserializationGzip", "TestDeserializationZip"})
        public void UnsuccessfulParallelDeserialization(String dirName){
            List<Employee> before = Repository.getEmployees();
            Repository.getEmployees().clear();
            String fileExtension;
            if (dirName.contains("Gzip")) {
                fileExtension = ".gz";
            } else {
                fileExtension = ".zip";
            }

            for (Employee employee : before) {
                String filePath = dirName + "/" + employee.getPesel() + fileExtension;
                corruptFile(filePath);
            }

            BackupService.start(false, false, dirName);

            for (int i = 0; i < before.size(); i++) {
                assertNotEquals(before.get(i), Repository.getEmployees().get(i));
            }
        }
    }
}
