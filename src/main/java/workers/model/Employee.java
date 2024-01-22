package workers.model;

import java.io.Serializable;
import java.lang.String;

abstract public class Employee implements Serializable {
    private final String pesel;
    private final String name;
    private final String surname;
    private final int salary;
    private final int phone;

    public Employee(String pesel, String name, String surname, String salary, String phone){
        this.pesel = pesel;
        this.name = name;
        this.surname = surname;
        this.salary = Integer.parseInt(salary);
        this.phone = Integer.parseInt(phone);
    }

    public String getPesel() {
        return pesel;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public int getSalary() {
        return salary;
    }

    public int getPhone() {
        return phone;
    }
}
