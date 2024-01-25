package workers.controller;

import workers.model.Employee;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Repository implements Serializable {
    private static List<Employee> employees = new ArrayList<>();

    public static void addEmployee(Employee e) {
        employees.add(e);
    }

    public static void removeEmployee(Employee e) {
        employees.remove(e);
    }

    public static List<Employee> getEmployees() {
        return employees;
    }
}
