package org.calculator;

import org.calculator.impl.Calculator;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    /**
     * main entry point for the application responsible for reading the input file and calling the evaluation logic.
     * and the end of the calculation the result will be printed
     * @param args not used in this application
     */
    public static void main (String [] args){
        String inputFilePath = "src/main/resources/input.txt";
        try {
            List<String> expressions = readInputFromFile(inputFilePath);
            Calculator calculator = new Calculator();
            List<String> result = calculator.calculate(expressions);
            System.out.println("Calculation result is: "+result);
        } catch (FileNotFoundException e) {
            System.out.println("the input file:"+inputFilePath+" was not found");
        }
    }

    /**
     * the method is responsible for reading the input file,
     * and separating the input into expressions
     * @param path to the input file
     * @return a list of expression in the order they apper in the file.
     * @throws FileNotFoundException thrown in case the provided path does not hold the input file.
     */
    private static List<String> readInputFromFile(String path) throws FileNotFoundException {
        File inputFile = new File(path);
        ArrayList<String> expressions = new ArrayList<>();
        Scanner scanner= new Scanner(inputFile);
        while(scanner.hasNextLine()){
            String expression = scanner.nextLine();
            expressions.add(expression);
        }
        return expressions;
    }
}
