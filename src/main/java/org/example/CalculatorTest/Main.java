package org.example.CalculatorTest;

import java.util.Scanner;

public class Main {

    public static String calc(String input) {
        String[] parts = input.split("\\s+");
        if (parts.length != 3) {
            throw new IllegalArgumentException("Некорректное выражение");
        }

        int a = Integer.parseInt(parts[0].trim());
        int b = Integer.parseInt(parts[2].trim());
        String operator = parts[1].trim();

        int result;

        switch (operator) {
            case "+":
                result = a + b;
                break;
            case "-":
                result = a - b;
                break;
            case "*":
                result = a * b;
                break;
            case "/":
                if (b == 0) {
                    throw new ArithmeticException("Деление на ноль");
                }
                result = a / b;
                break;
            default:
                throw new IllegalArgumentException("Некорректное выражение");
        }

        return String.valueOf(result);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.print("Введите арифметическое выражение (q для выхода): ");
            String input = scanner.nextLine();

            if (input.equalsIgnoreCase("q")) {
                break;
            }

            try {
                String result = calc(input);
                System.out.println("Результат: " + result);
            } catch (NumberFormatException e) {
                System.out.println("Ошибка: некорректное число");
            } catch (IllegalArgumentException e) {
                System.out.println("Ошибка: " + e.getMessage());
            } catch (ArithmeticException e) {
                System.out.println("Ошибка: " + e.getMessage());
            }
        }

        scanner.close();
    }
}
