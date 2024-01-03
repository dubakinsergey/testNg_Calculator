import Calculator.CalculatorServiceImpl;

public class Main {
    public static void main(String[] args) {
        CalculatorServiceImpl calculator = new CalculatorServiceImpl();
//        calculator.readTwoDigitsAndDivide("Введите числа для деления: ");
        calculator.divideTwoDigits("Введите числа для деления",    "20.4","0");
    }
}
