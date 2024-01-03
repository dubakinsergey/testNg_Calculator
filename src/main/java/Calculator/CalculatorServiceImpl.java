package Calculator;

import java.util.Scanner;

public class CalculatorServiceImpl implements CalculatorService {
    private Scanner scanner = new Scanner(System.in);

    @Override
    public void readTwoDigitsAndDivide(String prompt) {
        System.out.println(prompt);
        String d1 = scanner.nextLine();
        String d2 = scanner.nextLine();
        divideTwoDigits(prompt, d1, d2);
    }

    @Override
    public double divideTwoDigits(String promt, String d1, String d2) {
        double dividend = Double.parseDouble(d1);
        double divisor = Double.parseDouble(d2);
        if (divisor == 0) {
            throw new ArithmeticException("Деление на ноль недопустимо!");
        }
        System.out.println(dividend / divisor);
        return dividend / divisor;
    }
}
