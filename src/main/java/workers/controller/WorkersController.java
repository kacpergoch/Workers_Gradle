package workers.controller;
import workers.model.*;

import workers.view.WorkersView;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static workers.controller.Constants.attributesManager;
import static workers.controller.Constants.attributesSalesman;

public class WorkersController {
    private final WorkersView view;
    Scanner scanForInput;
    public WorkersController(){
        this.view = new WorkersView();
        this.scanForInput = new Scanner(System.in);
    }
    public void start(){
        Menu();
    }
    private void Menu(){
        int input;
        do {
            view.printMenu();
            input = scanForInput.nextInt();
            switch (input) {
                case 1:
                    EmployeeList();
                    break;
                case 2:
                    AddEmployee();
                    break;
                case 3:
                    DeleteEmployee();
                    break;
                case 4:
                    Backup();
                case 9:
                    break;
            }
        } while (input != 9);
    }

    private void EmployeeList(){
        try {
            scanForInput.nextLine();
            boolean exit = false;
            for (Employee employee : Repository.getEmployees()) {
                view.printEmployeeList(employee);
                do {
                    if (scanForInput.nextLine().equals("p")) {
                        exit = true;
                        break;
                    }
                } while (!(scanForInput.nextLine().equals("n") || scanForInput.nextLine().equals("p")));
                if (exit || Repository.getEmployees().indexOf(employee) + 1 == Repository.getEmployees().size()) {
                    break;
                }
            }
        }
        catch (Exception e) {
            System.out.println("There are no employees added");
        }
    }

    private void AddEmployee() {
        try {
            scanForInput.nextLine();
            List<String> employeeData = new ArrayList<>();
            System.out.print("[D]yrektor/[H]andlowiec: ");
            String input;
            List<String> attributes;
            char pos;

            do {
                input = scanForInput.nextLine();
                pos = input.toCharArray()[0];
            }while (!InputValidation.validatePosition(input));

            if (pos == 'd'){
                attributes = attributesManager;
            }
            else {
                attributes = attributesSalesman;
            }

           for (String attribute: attributes) {
               System.out.print("Podaj " + attribute + ": ");
               employeeData.add(scanForInput.nextLine());
           }

           Employee employee;
           if(pos == 'd'){
               if (InputValidation.validateManager(employeeData)){
                    employee = new Manager(
                           employeeData.get(0),
                           employeeData.get(1),
                           employeeData.get(2),
                           employeeData.get(3),
                           employeeData.get(4),
                           employeeData.get(5),
                           employeeData.get(6),
                           employeeData.get(7)
                   );
               }
               else {
                   throw new RuntimeException("Wrong input");
               }
           }
           else{
               if(InputValidation.validateSalesman(employeeData)){
                   employee = new Salesman(
                           employeeData.get(0),
                           employeeData.get(1),
                           employeeData.get(2),
                           employeeData.get(3),
                           employeeData.get(4),
                           employeeData.get(5),
                           employeeData.get(6)
                   );
               }
               else {
                   throw new RuntimeException("Wrong input");
               }
           }

            view.printAddEmployee(employee);
            do {
                input = scanForInput.nextLine();
                if (input.equals("z")){
                    Repository.addEmployee(employee);
                }
                else if (input.equals("p")){
                    break;
                }
            }while (!InputValidation.validateExit(input));
        } catch (Exception e) {
            System.out.println("Wrong input");
        }
    }

    private void DeleteEmployee(){
        try{
            System.out.println("3. USUN PRACOWNIKA");
            scanForInput.nextLine();
            System.out.println("Podaj identyfikator PESEL");
            view.separator();
            String input = scanForInput.nextLine();
            for (Employee employee: Repository.getEmployees()){
                boolean exit = false;
                if(employee.getPesel().equals(input)){
                    view.printDeleteEmployee(employee);
                    view.separator();
                    System.out.println("[U] - usuń \t [P] - porzuć");

                    do {
                        if (scanForInput.nextLine().equals("u")) {
                            Repository.removeEmployee(employee);
                            exit = true;
                            break;
                        }
                    } while (!(scanForInput.nextLine().equals("u") || scanForInput.nextLine().equals("p")));
                }
                if (exit) {
                    break;
                }
            }
        } catch (Exception e){
            System.out.println("There is no employee with given PESEL");
        }
    }

    private void Backup(){
        try{
            String input;
            boolean flagOperation = false;
            boolean flagCompressionType = false;
            String  directoryName;

            System.out.println("4. KOPIA ZAPASOWA");
            System.out.println("[Z]achowaj / [O]dtwórz\t:");
            do {
                input = scanForInput.nextLine();
                if (input.equals("z")) {
                    flagOperation = true;
                    break;
                }
                else if (input.equals("o")){
                    break;
                }
            } while (true);

            if (flagOperation) {
                System.out.println("[G]zip / [Z]ip\t:");
                do {
                    input = scanForInput.nextLine();
                    if (input.equals("g")) {
                        flagCompressionType = true;
                        break;
                    } else if (input.equals("z")) {
                        break;
                    }
                } while (true);
            }
            System.out.println("Nazwa folderu: ");
            directoryName = scanForInput.nextLine();

            BackupService.start(flagOperation, flagCompressionType, directoryName);
        }
        catch (Exception e){
            System.out.println("error");
        }
    }
}
