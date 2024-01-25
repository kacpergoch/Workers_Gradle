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
            String dirName = "TestDeserialization";
            BackupService.start(true, true, dirName);
            BackupService.start(true, false, dirName);
        }

        @Test
        public void CorrectParallelDeserialization(){

        }

        @Test
        public void IncorrectParallelDeserialization(){

        }
    }
}
