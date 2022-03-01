package com.company;

import java.util.HashMap;
import java.util.Scanner;

public class Calculator implements Sum,Multiply,Subtraction,Divide{

    static HashMap<String, Double> Vars = new HashMap<>();
    public static Scanner sc = new Scanner(System.in);

    public static int cont = 0;

    public static void main(String[] args) {

        Double x = null, y = null;
        String input;
        do {
            System.out.println("Type the command to do mathematical operation");
            input = sc.nextLine();
            String[] arrOfOperations = input.split(" ");

            if (arrOfOperations.length != 1) {
                if (arrOfOperations.length == 2) {
                    System.out.println("The input doesn't meet program requirements");
                } else if (arrOfOperations.length == 3) {
                    if(verifyInput(arrOfOperations[1]) == null || verifyInput(arrOfOperations[2]) == null){
                        System.out.println("Variable not found");
                    }else {
                        x = verifyInput(arrOfOperations[1]);
                        y = verifyInput(arrOfOperations[2]);
                        decideOperation(arrOfOperations[0].toUpperCase(), x, y);
                    }
                }
                else if (arrOfOperations.length == 4) {
                    if(verifyInput(arrOfOperations[1]) == null || verifyInput(arrOfOperations[2]) == null){
                        System.out.println("Variable not found");
                    }else {
                        x = verifyInput(arrOfOperations[1]);
                        y = verifyInput(arrOfOperations[2]);
                        decideOperation(arrOfOperations[0].toUpperCase(), x, y, arrOfOperations[3]);
                    }
                }
            } else {
                if (arrOfOperations[0].toUpperCase().equals("EXIT")) {
                    input = "EXIT";
                }
            }
        } while (input != "EXIT");

    }

    public static void decideOperation(String operation, Double x, Double y){
        switch (operation) {
            case "SUM":
                createVariable(Sum.sum(x, y));
                break;
            case "MINUS":
                createVariable(Subtraction.subtraction(x, y));
                break;
            case "MULTIPLY":
                createVariable(Multiply.multiply(x, y));
                break;
            case "DIVIDE":
                if (y == 0){
                    System.out.println("its not possible divide per zero");
                    break;
                }
                createVariable(Divide.divide(x, y));
                break;
            default:
                System.out.println("Not valid Operation");
                break;
        }
    }

    public static void decideOperation(String operation, Double x, Double y, String v){
        switch (operation) {
            case "SUM":
                createVariable(v,Sum.sum(x, y));
                break;
            case "MINUS":
                createVariable(v,Subtraction.subtraction(x, y));
                break;
            case "MULTIPLY":
                createVariable(v,Multiply.multiply(x, y));
                break;
            case "DIVIDE":
                if (y == 0){
                    System.out.println("its not possible divide per zero");
                    break;
                }
                createVariable(v,Divide.divide(x, y));
                break;
            default:
                System.out.println("Not valid Operation");
        }
    }

    public static Double verifyInput(String variable){
        try {
            return Double.parseDouble(variable);
        }catch (Exception e) {
            if(Vars.containsKey(variable)){
                return Vars.get(variable);
            }
            return null;
        }
    }

    public static void createVariable(double result) {
        String newVar = "Result" + cont;
        Vars.put(newVar,result);
        System.out.println("A new Variable called: " + newVar+ " was created with value: " + result);
        cont++;
    }

    public static void createVariable(String key, double result) {
        Vars.put(key,result);
        System.out.println("A new Variable called: " + key+ " was created with value: " + result);
    }

}
