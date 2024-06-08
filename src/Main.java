
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        System.out.println(calc(input));
    }

    public static String calc(String input) throws Exception {
        String[] expression = input.split(" ");
        String first = expression[0];
        String second = expression[2];
        String operator = expression[1];
        String result;

        if (expression.length > 3) throw new RuntimeException("Числа должны быть: один операнд, один оператор и второй операнд");
        if ((Roman.isRoman(first) && !Roman.isRoman(second)) || (!Roman.isRoman(first) && Roman.isRoman(second)))
            throw new RuntimeException("Числа должны быть только на римском или арбаских числах");
        if (Roman.isRoman(first) && Roman.isRoman(second)) {
            int convertedFirst = convertFromRomanToInt(first);
            int convertedSecond = convertFromRomanToInt(second);
            int expressionResult = doExpressionByOperator(operator, convertedFirst, convertedSecond);
            result = Roman.convertResultRoman(expressionResult);
            return result;
        }
        int arabicFirst = Integer.parseInt(first);
        int arabicSecond = Integer.parseInt(second);
        if (arabicFirst <= 0 || arabicSecond <= 0) throw new RuntimeException("Вводимое число должно быть от 1 до 10");
        int arabicExpressionResult = doExpressionByOperator(operator, arabicFirst, arabicSecond);
        result = String.valueOf(arabicExpressionResult);
        return result;
    }


    public static int doExpressionByOperator(String operator, int first, int second) {
        if (operator.equals("-")) {
            return first - second;
        } else if (operator.equals("+")) {
            return first + second;
        } else if (operator.equals("*")) {
            return first * second;
        } else if (operator.equals("/")) {
            if (second == 0) throw new RuntimeException("На ноль делить нельзя");
            else return first / second;
        } else throw new RuntimeException("Вы ввели неверный математический оператор");
    }


    public static int convertFromRomanToInt(String roman) {
        return switch (roman) {
            case "I" -> 1;
            case "II" -> 2;
            case "III" -> 3;
            case "IV" -> 4;
            case "V" -> 5;
            case "VI" -> 6;
            case "VII" -> 7;
            case "VIII" -> 8;
            case "IX" -> 9;
            case "X" -> 10;
            default -> throw new RuntimeException("Вы ввели некорректное число. Числа должны начинаться от 1 до 10");
        };
    }

    class Roman {
        public static String[] romanArray = new String[]{
                "0", "I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX", "X", "XI",
                "XII", "XIII", "XIV", "XV", "XVI", "XVII", "XVIII", "XIX", "XX", "XXI", "XXII", "XXIII", "XXIV",
                "XXV", "XXVI", "XXVII", "XXVIII", "XXIX", "XXX", "XXXI", "XXXII", "XXXIII", "XXXIV", "XXXV", "XXXVI",
                "XXXVII", "XXXVIII", "XXXIX", "XL", "XLI", "XLII", "XLIII", "XLIV", "XLV", "XLVI", "XLVII", "XLVIII",
                "XLIX", "L", "LI", "LII", "LIII", "LIV", "LV", "LVI", "LVII", "LVIII", "LIX", "LX", "LXI", "LXII",
                "LXIII", "LXIV", "LXV", "LXVI", "LXVII", "LXVIII", "LXIX", "LXX", "LXXI", "LXXII", "LXXIII", "LXXIV",
                "LXXV", "LXXVI", "LXXVII", "LXXVIII", "LXXIX", "LXXX", "LXXXI", "LXXXII", "LXXXIII", "LXXXIV", "LXXXV",
                "LXXXVI", "LXXXVII", "LXXXVIII", "LXXXIX", "XC", "XCI", "XCII", "XCIII", "XCIV", "XCV", "XCVI", "XCVII",
                "XCVIII", "XCIX", "C"};

        public static String convertResultRoman(int number) {
            String result = null;
            for (int i = 0; i < romanArray.length; i++) {
                if (number == i) {
                    result = romanArray[i];
                }
            }
            if (result == null || result.equals("0"))
                throw new RuntimeException("Результат римского числа не должен быть отрицательным числом и не должен быть 0");
            else return result;
        }

        public static boolean isRoman(String argument) {
            boolean isRoman = false;
            for (int i = 1; i < romanArray.length - 1; i++) {
                if (argument.equals(romanArray[i])) {
                    isRoman = true;
                    break;
                }
            }
            return isRoman;
        }
    }
}
