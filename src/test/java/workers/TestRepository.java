package workers;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import workers.controller.Repository;
import workers.model.Employee;
import workers.model.Manager;
import workers.model.Salesman;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

public class TestRepository {
        @BeforeEach
        void setUp(){
            Repository.getEmployees().clear();
        }

        private void addTenEmployees() {
            for (int i = 0; i < 10; i++){
                Salesman salesman = new Salesman("","","","0","0","0","0");
                Repository.addEmployee(salesman);
            }
        }

        private Salesman addSalesman(){
            Salesman salesman = new Salesman("","","","0","0","0","0");
            Repository.addEmployee(salesman);
            return salesman;
        }

        private Manager addManager() {
            Manager manager = new Manager("","","","0","0","0","0","0");
            Repository.addEmployee(manager);
            return manager;
        }

        @Test
        public void TestAddSalesman() {
            Salesman s = addSalesman();
            assertTrue(Repository.getEmployees().contains(s));
            assertEquals(1, Repository.getEmployees().size());
        }

        @Test
        public void TestAddManager() {
            Manager m = addManager();
            assertTrue(Repository.getEmployees().contains(m));
            assertEquals(1, Repository.getEmployees().size());
        }

        @Test
        public void TestAddSalesmanToFilled(){
            addTenEmployees();
            int size = Repository.getEmployees().size();
            Salesman s = addSalesman();
            assertTrue(Repository.getEmployees().contains(s));
            assertEquals(size + 1, Repository.getEmployees().size());
        }

        @Test
        public void TestAddManagerToFilled() {
            addTenEmployees();
            int size = Repository.getEmployees().size();
            Manager m = addManager();
            assertTrue(Repository.getEmployees().contains(m));
            assertEquals(size + 1, Repository.getEmployees().size());
        }

        @Test
        public void TestAddTenRandomEmployees() {
            for (int i = 0; i < 10; i++){
                Random r = new java.util.Random();
                int num = r.nextInt();
                if (num % 2 == 0){
                    Salesman s = addSalesman();
                    assertTrue(Repository.getEmployees().contains(s));
                }
                else {
                    Manager m = addManager();
                    assertTrue(Repository.getEmployees().contains(m));
                }
            }
            assertEquals(10, Repository.getEmployees().size());
        }

        @Test
        public void TestRemoveSalesman() {
            addTenEmployees();
            Salesman s = addSalesman();
            int size = Repository.getEmployees().size();
            Repository.removeEmployee(s);
            assertFalse(Repository.getEmployees().contains(s));
            assertEquals(size - 1, Repository.getEmployees().size());
        }

    @Test
    public void TestRemoveManager() {
        addTenEmployees();
        Manager m = addManager();
        int size = Repository.getEmployees().size();
        Repository.removeEmployee(m);
        assertFalse(Repository.getEmployees().contains(m));
        assertEquals(size - 1, Repository.getEmployees().size());
    }
}
