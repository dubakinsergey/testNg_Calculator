import Calculator.CalculatorServiceImpl;
import org.testng.Assert;
import org.testng.annotations.*;
import java.io.*;


public class CalculatorServiceImplTest {
    private static final String promt = "Числа для деления";
    private final InputStream originalSystemIn = System.in;
    private ByteArrayOutputStream outputStreamCaptor;
    private CalculatorServiceImpl calculatorService;

    @BeforeMethod
    public void setUpMethod() {
        outputStreamCaptor = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStreamCaptor));
        calculatorService = new CalculatorServiceImpl();
    }

    @AfterMethod
    public void tearDown() {
        System.setOut(System.out);
        System.setIn(originalSystemIn);
    }

    @DataProvider
    public Object[][] dataPositiveTest() {
        return new Object[][]{
                {"10", "5", 2.0},
                {"20.4", "5", 4.08},
                {"-100", "200", -0.5}
        };
    }

    @DataProvider
    public Object[][] dataNegativeTest() {
        return new Object[][]{
                {"256", "*"},
                {"85", "abc"},
                {"17", " "}
        };
    }

    @Test(dataProvider = "dataPositiveTest",
            testName = "Позитивный тест для метода divideTwoDigits")
    public void positiveTestDivideTwoDigits(String p1, String p2, double expectedResult) {
        double actualResult = calculatorService.divideTwoDigits(promt, p1, p2);
        Assert.assertEquals(actualResult, expectedResult);
    }

    @Test(testName = "Выбрасывается ли исключение при делении на 0 в divideTwoDigits")
    public void shouldThrowArithmeticExceptionInDivideTwoDigits() {
        Assert.assertThrows(ArithmeticException.class, () -> calculatorService.divideTwoDigits(promt, "8", "0"));
    }

    @Test(dataProvider = "dataNegativeTest",
            testName = "Негативный тест на некорректный вввод",
            expectedExceptions = NumberFormatException.class)
    public void shouldThrowNumberFormatExceptionInDivideTwoDigits(String p1, String p2) {
        calculatorService.divideTwoDigits("Негативный тест на некорректный вввод", p1, p2);
    }

    @Test(testName = "Вывод в консоль метода divideTwoDigits")
    public void shouldConsoleInDivideTwoDigits() {
        calculatorService.divideTwoDigits(promt, "6", "6");
        String consoleActualResult = outputStreamCaptor.toString();
        Assert.assertTrue(consoleActualResult.contains("1"));
    }

    @Test(dataProvider = "dataPositiveTest",
            testName = "Позитивный тест readTwoDigitsAndDivide")
    public void positiveTestReadTwoDigitsAndDivide(String num1, String num2, double expectedResult) {
        String strIn = num1 + "\n" + num2;
        System.setIn(new ByteArrayInputStream(strIn.getBytes()));
        CalculatorServiceImpl calculator = new CalculatorServiceImpl();
        calculator.readTwoDigitsAndDivide(promt);
        Assert.assertTrue(outputStreamCaptor.toString().contains(String.valueOf(expectedResult)));
    }

    @Test(dataProvider = "dataNegativeTest",
            testName = "Негативный тест readTwoDigitsAndDivide")
    public void negativeTestReadTwoDigitsAndDivide(String num1, String num2) {
        String strIn = num1 + "\n" + num2;
        System.setIn(new ByteArrayInputStream(strIn.getBytes()));
        CalculatorServiceImpl calculator = new CalculatorServiceImpl();
        Assert.assertThrows(NumberFormatException.class, () -> calculator.readTwoDigitsAndDivide(promt));
    }

    @Test(testName = "Выбрасывается ли исключение при делении на 0 в readTwoDigitsAndDivide",
            expectedExceptions = ArithmeticException.class)
    public void shouldThrowArithmeticExceptionInReadTwoDigitsAndDivide() {
        String strIn = "200" + "\n" + "0";
        System.setIn(new ByteArrayInputStream(strIn.getBytes()));
        CalculatorServiceImpl calculator = new CalculatorServiceImpl();
        calculator.readTwoDigitsAndDivide(promt);
    }

    @Test(testName = "Проверка вывода в консоль promt readTwoDigitsAndDivide")
    public void shouldConsolePromtInReadTwoDigitsAndDivide() {
        String strIn = "33" + "\n" + "5.6";
        System.setIn(new ByteArrayInputStream(strIn.getBytes()));
        CalculatorServiceImpl calculator = new CalculatorServiceImpl();
        calculator.readTwoDigitsAndDivide(promt);
        Assert.assertTrue(outputStreamCaptor.toString().contains(promt));
    }
}
