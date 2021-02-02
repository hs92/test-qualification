import java.util.Arrays;
import java.util.List;

public class EnglishCalculator {

    private static final String AND_THEN = " and then ";

    private static final String ADD = "add";
    private static final String to = "to";

    private static final String MULTIPLY = "multiply";
    private static final String DIVIDE = "divide";

    private static final String by = "by";

    private static final String SUBTRACT = "subtract";
    private static final String from = "from";

    private static final String BLANK = "";

    private static final String SPACE = " ";

    private static List<String> allowedNumberTexts = Arrays.asList("zero", "one", "two", "three", "four", "five", "six", "seven",
            "eight", "nine", "ten", "eleven", "twelve", "thirteen", "fourteen", "fifteen", "sixteen", "seventeen",
            "eighteen", "nineteen", "twenty", "thirty", "forty", "fifty", "sixty", "seventy", "eighty", "ninety",
            "hundred", "thousand", "million", "billion", "trillion");

    private static String[] unitsArray = {"zero", "one", "two", "three", "four", "five", "six", "seven", "eight", "nine", "ten", "eleven", "twelve",
            "thirteen", "fourteen", "fifteen", "sixteen", "seventeen", "eighteen", "nineteen"};

    private static String[] tensArray = {"zero", "ten", "twenty", "thirty", "forty", "fifty", "sixty", "seventy", "eighty", "ninety"};

    private static long getNumber(String input) {

        long result = 0;
        long finalResult = 0;
        boolean isValid = true;
        if (input != null && input.length() > 0) {
            input = input.toLowerCase().replaceAll(" and", " ");
            String[] splittedParts = input.trim().split("\\s+");
            for (String str : splittedParts) {
                if (!allowedNumberTexts.contains(str)) {
                    isValid = false;
                    System.out.println("Invalid word found : " + str);
                    break;
                }
            }
            if (isValid) {
                for (String str : splittedParts) {
                    if (str.equalsIgnoreCase("zero")) {
                        result += 0;
                    } else if (str.equalsIgnoreCase("one")) {
                        result += 1;
                    } else if (str.equalsIgnoreCase("two")) {
                        result += 2;
                    } else if (str.equalsIgnoreCase("three")) {
                        result += 3;
                    } else if (str.equalsIgnoreCase("four")) {
                        result += 4;
                    } else if (str.equalsIgnoreCase("five")) {
                        result += 5;
                    } else if (str.equalsIgnoreCase("six")) {
                        result += 6;
                    } else if (str.equalsIgnoreCase("seven")) {
                        result += 7;
                    } else if (str.equalsIgnoreCase("eight")) {
                        result += 8;
                    } else if (str.equalsIgnoreCase("nine")) {
                        result += 9;
                    } else if (str.equalsIgnoreCase("ten")) {
                        result += 10;
                    } else if (str.equalsIgnoreCase("eleven")) {
                        result += 11;
                    } else if (str.equalsIgnoreCase("twelve")) {
                        result += 12;
                    } else if (str.equalsIgnoreCase("thirteen")) {
                        result += 13;
                    } else if (str.equalsIgnoreCase("fourteen")) {
                        result += 14;
                    } else if (str.equalsIgnoreCase("fifteen")) {
                        result += 15;
                    } else if (str.equalsIgnoreCase("sixteen")) {
                        result += 16;
                    } else if (str.equalsIgnoreCase("seventeen")) {
                        result += 17;
                    } else if (str.equalsIgnoreCase("eighteen")) {
                        result += 18;
                    } else if (str.equalsIgnoreCase("nineteen")) {
                        result += 19;
                    } else if (str.equalsIgnoreCase("twenty")) {
                        result += 20;
                    } else if (str.equalsIgnoreCase("thirty")) {
                        result += 30;
                    } else if (str.equalsIgnoreCase("forty")) {
                        result += 40;
                    } else if (str.equalsIgnoreCase("fifty")) {
                        result += 50;
                    } else if (str.equalsIgnoreCase("sixty")) {
                        result += 60;
                    } else if (str.equalsIgnoreCase("seventy")) {
                        result += 70;
                    } else if (str.equalsIgnoreCase("eighty")) {
                        result += 80;
                    } else if (str.equalsIgnoreCase("ninety")) {
                        result += 90;
                    } else if (str.equalsIgnoreCase("hundred")) {
                        result *= 100;
                    } else if (str.equalsIgnoreCase("thousand")) {
                        result *= 1000;
                        finalResult += result;
                        result = 0;
                    } else if (str.equalsIgnoreCase("million")) {
                        result *= 1000000;
                        finalResult += result;
                        result = 0;
                    } else if (str.equalsIgnoreCase("billion")) {
                        result *= 1000000000;
                        finalResult += result;
                        result = 0;
                    } else if (str.equalsIgnoreCase("trillion")) {
                        result *= 1000000000000L;
                        finalResult += result;
                        result = 0;
                    }
                }
                finalResult += result;
            }
        }
        return finalResult;
    }

    private static String numberToWord(long number) {

        String words = "";
        if (number == 0) {
            return "zero";
        }
        if (number < 0) {
            String numberStr = "" + number;
            numberStr = numberStr.substring(1);
            return "minus " + numberToWord(Integer.parseInt(numberStr));
        }
        if ((number / 1000000) > 0) {
            words += numberToWord(number / 1000000) + " million ";
            number %= 1000000;
        }
        if ((number / 1000) > 0) {
            words += numberToWord(number / 1000) + " thousand ";
            number %= 1000;
        }
        if ((number / 100) > 0) {
            words += numberToWord(number / 100) + " hundred ";
            number %= 100;
        }
        if (number > 0) {
            if (number < 20) {
                words += unitsArray[(int) number];
            } else {
                words += tensArray[(int) (number / 10)];
                if ((number % 10) > 0) {
                    words += " " + unitsArray[(int) (number % 10)];
                }
            }
        }
        return words;
    }

    public static void main(String[] args) {

        String input_1 = "add fifty three to forty four and then multiply by ten";
        getResult(input_1.trim().toLowerCase());

        String input_2 = "divide ninety nine by eleven and then subtract four";
        getResult(input_2.trim().toLowerCase());

        String input_3 = "add seventy seven to fifty and then add two";
        getResult(input_3.trim().toLowerCase());

        String input_4 = "subtract five from seventeen";
        getResult(input_4.trim().toLowerCase());
    }

    private static void getResult(String str) {

        long res = 0;
        String[] split = str.split(AND_THEN);
        for (int i = 0; i < split.length; i++) {
            String s = split[i];
            String operator = s.substring(0, s.indexOf(SPACE));
            switch (operator) {
                case ADD:
                    res = performAddition(s, i, res);
                    break;
                case SUBTRACT:
                    res = performSubtraction(s, i, res);
                    break;
                case MULTIPLY:
                    res = performMultiplication(s, i, res);
                    break;
                case DIVIDE:
                    res = performDivision(s, i, res);
                    break;
            }
        }
        System.out.println(numberToWord(res));
    }

    private static long performAddition(String s, int i, long res) {

        String operand1, operand2;
        long op1, op2;
        if (i == 0) {
            operand1 = s.substring(s.indexOf(ADD), s.indexOf(to)).replaceAll(ADD, BLANK).trim();
            op1 = getNumber(operand1);
            operand2 = s.substring(s.indexOf(to)).replaceAll(to, BLANK).trim();
            op2 = getNumber(operand2);
            res = op1 + op2;
        } else {
            operand2 = s.substring(s.indexOf(ADD)).replaceAll(ADD, BLANK).trim();
            op2 = getNumber(operand2);
            res += op2;
        }
        return res;
    }

    private static long performSubtraction(String s, int i, long res) {

        String operand1, operand2;
        long op1, op2;
        if (i == 0) {
            operand1 = s.substring(s.indexOf(SUBTRACT), s.indexOf(from)).replaceAll(SUBTRACT, BLANK).trim();
            op1 = getNumber(operand1);
            operand2 = s.substring(s.indexOf(from)).replaceAll(from, BLANK).trim();
            op2 = getNumber(operand2);
            res = op2 - op1;
        } else {
            operand2 = s.substring(s.indexOf(SUBTRACT)).replaceAll(SUBTRACT, BLANK).trim();
            op2 = getNumber(operand2);
            res -= op2;
        }
        return res;
    }

    private static long performMultiplication(String s, int i, long res) {

        String operand1, operand2;
        long op1, op2;
        if (i == 0) {
            operand1 = s.substring(s.indexOf(MULTIPLY), s.indexOf(by)).replaceAll(MULTIPLY, BLANK).trim();
            op1 = getNumber(operand1);
            operand2 = s.substring(s.indexOf(by)).replaceAll(by, BLANK).trim();
            op2 = getNumber(operand2);
            res = op1*op2;
        } else {
            operand2 = s.substring(s.indexOf(by)).replaceAll(by, BLANK).trim();
            op2 = getNumber(operand2);
            res = res*op2;
        }
        return res;
    }

    private static long performDivision(String s, int i, long res) {

        String operand1, operand2;
        long op1, op2;
        if (i == 0) {
            operand1 = s.substring(s.indexOf(DIVIDE), s.indexOf(by)).replaceAll(DIVIDE, BLANK).trim();
            op1 = getNumber(operand1);
            operand2 = s.substring(s.indexOf(by)).replaceAll(by, BLANK).trim();
            op2 = getNumber(operand2);
            res = op1/op2;
        } else {
            operand2 = s.substring(s.indexOf(by)).replaceAll(by, BLANK).trim();
            op2 = getNumber(operand2);
            res = res/op2;
        }
        return res;
    }
}
