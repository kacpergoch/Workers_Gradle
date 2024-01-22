package workers.view;
import workers.controller.Repository;
import workers.model.*;

public class WorkersView {
    public void printMenu(){
        System.out.println("MENU");
        System.out.println("\t1. LISTA PRACOWNIKÓW");
        System.out.println("\t2. DODAJ PRACOWNIKA");
        System.out.println("\t3. USUŃ PRACOWNIKA");
        System.out.println("\t4. KOPIA ZAPASOWA");
        System.out.println("WYBÓR>");
    }

    public void printEmployeeList(Employee employee){
        System.out.println("1. LISTA PRACOWNIKÓW");
        separator();
        printEmployee(employee);
        separator();
        System.out.print("\t\t\t" + "[Pozycja: " + (Repository.getEmployees().indexOf(employee) + 1) + "/" + Repository.getEmployees().size() + "]");
        System.out.print("[N] - następny \t [P] - powrót\n");
    }

    public void printAddEmployee(Employee employee){
        System.out.println("2. DODAJ PRACOWNIKA");
        separator();
        printEmployee(employee);
        separator();
        System.out.println("[Z] - zapisz \t [P] - porzuć");
    }

    public void printEmployee(Employee employee){
        System.out.println("\t Identyfikator PESEL\t" + ":\t" + employee.getPesel());
        System.out.println("\t Imię\t" + ":\t" + employee.getName());
        System.out.println("\t Nazwisko\t" + ":\t" + employee.getSurname());
        System.out.println("\t Wynagrodzenie (zł)\t" + ":\t" + employee.getSalary());
        System.out.println("\t Telefon służbowy numer\t" + ":\t" + employee.getPhone());
        if (employee instanceof Manager) {
            System.out.println("\t Stanowisko (zł)\t" + ":\t" + "Dyrektor");
            System.out.println("\t Dodatek służbowy (zł)\t" + ":\t" + ((Manager)employee).getBonus());
            System.out.println("\t Karta służbowa numer\t" + ":\t" + ((Manager)employee).getCard());
            System.out.println("\t Limit kosztów/miesiąc (zł)\t" + ":\t" + ((Manager)employee).getCardLimit());
        }
        else if (employee instanceof Salesman){
            System.out.println("\t Stanowisko (zł)\t" + ":\t" + "Handlowiec");
            System.out.println("\t Prowizja (%)\t" + ":\t" + ((Salesman)employee).getCommission());
            System.out.println("\t Limit prowizji/miesiąc (zł)\t" + ":\t" + ((Salesman)employee).getCommissionLimit());
        }
    }

    public void printDeleteEmployee(Employee employee){
        System.out.println("\t Imię\t" + ":\t" + employee.getName());
        System.out.println("\t Nazwisko\t" + ":\t" + employee.getSurname());
        System.out.println("\t Wynagrodzenie (zł)\t" + ":\t" + employee.getSalary());
        System.out.println("\t Telefon służbowy numer\t" + ":\t" + employee.getPhone());
        if (employee instanceof Manager) {
            System.out.println("\t Stanowisko\t" + ":\t" + "Dyrektor");
            System.out.println("\t Dodatek służbowy (zł)\t" + ":\t" + ((Manager)employee).getBonus());
            System.out.println("\t Karta służbowa numer\t" + ":\t" + ((Manager)employee).getCard());
            System.out.println("\t Limit kosztów/miesiąc (zł)\t" + ":\t" + ((Manager)employee).getCardLimit());
        }
        else if (employee instanceof Salesman){
            System.out.println("\t Stanowisko\t" + ":\t" + "Handlowiec");
            System.out.println("\t Prowizja (%)\t" + ":\t" + ((Salesman)employee).getCommission());
            System.out.println("\t Limit prowizji/miesiąc (zł)\t" + ":\t" + ((Salesman)employee).getCommissionLimit());
        }
    }

    public void separator(){
        System.out.println("\t------------------------------------------------------------------");
    }
}
