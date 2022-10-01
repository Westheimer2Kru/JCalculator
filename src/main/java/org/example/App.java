package org.example;

import org.Calculator.ConsoleCalculator;
import org.Calculator.ICalculator;

import java.math.BigDecimal;
import java.util.Scanner;

public class App
{
    public static void main( String[] args )
    {
        ICalculator calculator = ConsoleCalculator.getInstance();

        try(Scanner in = new Scanner(System.in)){
            System.out.println("your expression:");
            String input = in.nextLine();

            BigDecimal result = calculator.calculate(input);
            System.out.println("Answer:");
            System.out.println(result);
        } catch (Exception ex) {
            System.out.println("something went wrong!");
            System.out.println(ex.getMessage());
        }
    }
}
