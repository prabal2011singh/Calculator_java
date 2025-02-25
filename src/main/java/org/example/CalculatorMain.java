package org.example;

import java.util.Scanner;
import java.util.InputMismatchException;

public class CalculatorMain {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        boolean start = true;

        while (start) {
            System.out.println("===== Scientific Calculator =====");
            System.out.println("1. Square Root (√x)");
            System.out.println("2. Factorial (x!)");
            System.out.println("3. Natural Logarithm (ln(x))");
            System.out.println("4. Power Function (x^b)");
            System.out.println("5. Exit");
            System.out.print("Enter your choice (1-5): ");

            int ch = sc.nextInt();

            switch (ch) {
                case 1:
                    System.out.print("Enter a number to calculate square root: ");
                    try {
                        double number = sc.nextDouble();
                        double result = squareRoot(number);
                        System.out.println("Square root of " + number + " = " + result);
                    } catch (InputMismatchException e) {
                        System.out.println("Invalid input. Please enter a valid number.");
                        sc.next(); // Clear the invalid input
                    } catch (IllegalArgumentException e) {
                        System.out.println(e.getMessage());
                    }
                    break;

                case 2:
                    System.out.print("Enter a non-negative integer to calculate factorial: ");
                    try {
                        int number = sc.nextInt();
                        long result = factorial(number);
                        System.out.println("Factorial of " + number + " = " + result);
                    } catch (InputMismatchException e) {
                        System.out.println("Invalid input. Please enter a valid integer.");
                        sc.next(); // Clear the invalid input
                    } catch (IllegalArgumentException e) {
                        System.out.println(e.getMessage());
                    }
                    break;

                case 3:
                    System.out.print("Enter a positive number to calculate natural logarithm: ");
                    try {
                        double number = sc.nextDouble();
                        double result = naturalLogarithm(number);
                        System.out.println("Natural logarithm of " + number + " = " + result);
                    } catch (InputMismatchException e) {
                        System.out.println("Invalid input. Please enter a valid number.");
                        sc.next(); // Clear the invalid input
                    } catch (IllegalArgumentException e) {
                        System.out.println(e.getMessage());
                    }
                    break;

                case 4:
                    try {
                        System.out.print("Enter base (x): ");
                        double base = sc.nextDouble();
                        System.out.print("Enter exponent (b): ");
                        double exponent = sc.nextDouble();
                        double result = power(base, exponent);
                        System.out.println(base + " raised to power " + exponent + " = " + result);
                    } catch (InputMismatchException e) {
                        System.out.println("Invalid input. Please enter valid numbers.");
                        sc.next(); // Clear the invalid input
                    }
                    break;

                case 5:
                    start = false;
                    System.out.println("Exiting the calculator. Goodbye!");
                    break;

                default:
                    System.out.println("Invalid choice. Please try again.");
            }
            System.out.println();
        }
    }

    public static double squareRoot(double number) {
        if (number < 0) {
            throw new IllegalArgumentException("Cannot calculate square root of a negative number");
        }
        return Math.sqrt(number);
    }

    public static long factorial(int number) {
        if (number < 0) {
            throw new IllegalArgumentException("Cannot calculate factorial of a negative number");
        }
        if (number == 0 || number == 1) {
            return 1;
        }

        long result = 1;
        for (int i = 2; i <= number; i++) {
            result *= i;
            if (result < 0) { // Check for overflow
                throw new ArithmeticException("Factorial calculation resulted in overflow");
            }
        }
        return result;
    }

    public static double naturalLogarithm(double number) {
        if (number <= 0) {
            throw new IllegalArgumentException("Cannot calculate logarithm of a non-positive number");
        }
        return Math.log(number);
    }

    public static double power(double base, double exponent) {
        return Math.pow(base, exponent);
    }
}