package workers;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import workers.model.Employee;
import workers.model.Manager;
import workers.model.Salesman;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

public class TestEmployeeContainer {
    private List<Employee> employees;

        @BeforeEach
        void setUp(){
            employees = new ArrayList<>();
        }

        private void addTenEmployees() {
            for (int i = 0; i < 10; i++){
                employees.add(new Salesman("","","","0","0","0","0"));
            }
        }

        private Salesman addSalesman(){
            Salesman salesman = new Salesman("","","","0","0","0","0");
            employees.add(salesman);
            return salesman;
        }

        private Manager addManager() {
            Manager manager = new Manager("","","","0","0","0","0","0");
            employees.add(manager);
            return manager;
        }

        @Test
        public void TestAddSalesman() {
            Salesman s = addSalesman();
            assertTrue(employees.contains(s));
            assertEquals(1, employees.size());
        }

        @Test
        public void TestAddManager() {
            Manager m = addManager();
            assertTrue(employees.contains(m));
            assertEquals(1, employees.size());
        }

        @Test
        public void TestAddSalesmanToFilled(){
            addTenEmployees();
            int size = employees.size();
            Salesman s = addSalesman();
            assertTrue(employees.contains(s));
            assertEquals(size + 1, employees.size());
        }

        @Test
        public void TestAddManagerToFiled() {
            addTenEmployees();
            int size = employees.size();
            Manager m = addManager();
            assertTrue(employees.contains(m));
            assertEquals(size + 1, employees.size());
        }

        @Test
        public void TestAddTenRandomEmployees() {
            for (int i = 0; i < 10; i++){
                Random r = new java.util.Random();
                int num = r.nextInt();
                if (num % 2 == 0){
                    Salesman s = addSalesman();
                    assertTrue(employees.contains(s));
                }
                else {
                    Manager m = addManager();
                    assertTrue(employees.contains(m));
                }
            }
            assertEquals(10, employees.size());
        }

        @Test
        public void TestRemoveSalesman() {
            addTenEmployees();
            Salesman s = addSalesman();
            int size = employees.size();
            employees.remove(s);
            assertFalse(employees.contains(s));
            assertEquals(size - 1, employees.size());
        }
}
