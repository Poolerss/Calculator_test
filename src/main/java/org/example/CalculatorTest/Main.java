package org.example.CalculatorTest;

import java.util.Scanner;

public class Main {
    private static final int[] intervals = {0, 1, 4, 5, 9, 10, 40, 50, 90, 100, 400, 500, 900, 1000};
    private static final String[] numerals = {"", "I", "IV", "V", "IX", "X", "XL", "L", "XC", "C", "CD", "D", "CM", "M"};

    /**
     * Преобразует римское число в арабское.
     *
     * @param roman римское число
     * @return арабское число
     * @throws IllegalArgumentException если переданная строка не является римским числом
     */
    public static int toArabic(String roman) {
        int result = 0;
        for (int i = intervals.length - 1; i >= 0; i--) {
            while (roman.indexOf(numerals[i]) == 0 && !numerals[i].isEmpty()) {
                result += intervals[i];
                roman = roman.substring(numerals[i].length());
            }
        }
        if (!roman.isEmpty()) {
            throw new IllegalArgumentException("Invalid Roman numeral: " + roman);
        }
        return result;
    }

    private static int findFloor(int number, int firstIndex, int lastIndex) {
        if (firstIndex == lastIndex)
            return firstIndex;
        if (intervals[firstIndex] == number)
            return firstIndex;
        if (intervals[lastIndex] == number)
            return lastIndex;
        final int median = (lastIndex + firstIndex) / 2;
        if (median == firstIndex)
            return firstIndex;
        if (number == intervals[median])
            return median;
        if (number > intervals[median])
            return findFloor(number, median, lastIndex);
        else
            return findFloor(number, firstIndex, median);
    }

    /**
     * Преобразует арабское число в римское.
     *
     * @param arabic арабское число
     * @return римское число
     * @throws IllegalArgumentException если переданное арабское число не входит в допустимый диапазон от 1 до 3999
     */
    public final static String toRomanian(int arabic) {
        if (arabic < 1 || arabic > 3999) {
            throw new IllegalArgumentException("Invalid Arabic numeral: " + arabic);
        }
        int floorIndex = findFloor(arabic, 0, intervals.length - 1);
        if (arabic == intervals[floorIndex]) {
            return numerals[floorIndex];
        }
        return numerals[floorIndex] + toRomanian(arabic - intervals[floorIndex]);
    }


    public static boolean isRoman(String str) {
        return str.matches("[IXVLCDM]");
    }
    /**
     * Вычисляет результат выражения записанного строкой.
     *
     * @param operation строка состоящая из 2х чисер (римских или арабских) и одного операнда (+, -, * или /)
     * @return строковое представление результата выражения. если числа римские то результат представляет собой римское число
     * @throws IllegalArgumentException если более трех операндов
     */
    public static String calculate(String operation) {


        String[] tokens = operation.split(" ");
        if (tokens.length != 3) {
            throw new IllegalArgumentException("Неверный формат выражения");
        }
        String num1 = tokens[0];
        String operator = tokens[1];
        String num2 = tokens[2];
        int operand1, operand2, result = 0;
        boolean isRoman = false;
        if (isRoman(num1) && isRoman(num2)) {
            operand1 = toArabic(num1);
            operand2 = toArabic(num2);
            isRoman = true;
        } else {
            operand1 = Integer.parseInt(num1);
            operand2 = Integer.parseInt(num2);
        }
        switch (operator) {
            case "+":
                result = operand1 + operand2;
                break;
            case "-":
                result = operand1 - operand2;
                break;
            case "*":
                result = operand1 * operand2;
                break;
            case "/":
                result = operand1 / operand2;
                break;
            default:
                System.out.println("Неверный оператор");
        }

        if (isRoman) {
            return toRomanian(result);
        } else {
            return String.valueOf(result);
        }
    }


    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Введите выражение в формате 'число оператор число':");
        String input = scanner.nextLine();

        String result = calculate(input);
        System.out.println("Результат: " + result);
    }
}


