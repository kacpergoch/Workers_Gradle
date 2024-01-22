package workers.controller;

import java.util.List;

public class InputValidation {

    public static boolean validatePosition(String str){
        return str.equals("d") || str.equals("h");
    }

    public static boolean validateExit(String str) { return str.equals("z") || str.equals("p");}
    public static boolean validateManager(List<String> managerData){
        try{
        validatePesel(managerData.get(0));
        validateString(managerData.get(1));
        validateString(managerData.get(2));
        validateInt(managerData.get(3));
        validatePhone(managerData.get(4));
        validateInt(managerData.get(5));
        validateCard(managerData.get(6));
        validateInt(managerData.get(7));
        return true;
        }
        catch (Exception e){
            return false;
        }
    }

    public static boolean validateSalesman(List<String> salesmanData){
        try{
            validatePesel(salesmanData.get(0));
            validateString(salesmanData.get(1));
            validateString(salesmanData.get(2));
            validateInt(salesmanData.get(3));
            validatePhone(salesmanData.get(4));
            validateCommission(salesmanData.get(5));
            validateInt(salesmanData.get(6));
            return true;
        }
        catch (Exception e){
            return false;
        }
    }
    private static void validateString(String str){
        if (str.matches(".*[^\\w].*")){
            throw new IllegalArgumentException("Wrong input");
        }
    }

    private static void validateInt(String str){

        if (!(str != null && str.matches("[0-9.]+"))){
            throw new RuntimeException("Input is not numeric");
        }
    }

    private static void validatePesel(String str){
        char[] pesel = str.toCharArray();
        validateInt(str);
        boolean flag = !(((pesel[0] + 3*pesel[1] + 7*pesel[2] + 9*pesel[3] + pesel[4] + 3*pesel[5] +
                7*pesel[6] + 9*pesel[7] + pesel[8] + 3*pesel[9] + pesel[10] ) % 10) == 0);
        
        if (flag){
            throw new IllegalArgumentException("Wrong PESEL");
        }
    }

    private static void validateCard(String str){
        if (!str.matches("[0-9]{3}-[0-9]{3}-[0-9]{2}-[0-9]{3}")) {
            throw new IllegalArgumentException("Wrong card number");
        }
    }

    private static void validatePhone(String str){
        if (!str.matches("[0-9]{9}")) {
            throw new IllegalArgumentException("Wrong phone number");
        }
    }

    private static void validateCommission(String str){
        validateInt(str);
        int commission = Integer.parseInt(str);
        if (commission < 0 || commission > 100){
            throw new IllegalArgumentException("Wrong argument: percentage out of range");
        }
    }
}
