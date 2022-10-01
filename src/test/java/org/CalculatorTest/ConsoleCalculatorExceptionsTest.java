package org.CalculatorTest;

import org.Calculator.ConsoleCalculator;
import org.Calculator.ICalculator;
import org.junit.Assert;
import org.junit.Test;

import java.util.EmptyStackException;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertNull;

public class ConsoleCalculatorExceptionsTest {

    ICalculator calculator = ConsoleCalculator.getInstance();

    @Test
    public void additionalOperatorTest() {
        String input;

        try {
            input = "3 + 2 +* 1";

            calculator.calculate(input);
            Assert.fail();
        } catch (ArithmeticException exception) {
            assertEquals("Too much operators!", exception.getMessage());
        }

        try {
            input = "3 +- 2 - 1";

            calculator.calculate(input);
            Assert.fail();
        } catch (EmptyStackException exception) {
            assertNull(exception.getMessage());
        }

    }

    @Test
    public void incorrectBracketOrderTest() {
        try {
            String input = ") 3 + 2 (";

            calculator.calculate(input);
            Assert.fail();
        } catch (IllegalArgumentException ex) {
            assertEquals("incorrect bracket order or number!", ex.getMessage());
        }
    }

    @Test
    public void incorrectBracketNumberTest() {
        try {
            String input = "((3 + 2)))";
            calculator.calculate(input);
            Assert.fail();
        } catch (IllegalArgumentException ex) {
            assertEquals("incorrect bracket order or number!", ex.getMessage());
        }
    }

    @Test
    public void incorrectDecimalSeparatorPositionTest() {
        try{
            String input = "3 + .23";

            calculator.calculate(input);
            Assert.fail();
        } catch (IllegalArgumentException ex) {
            assertEquals("incorrect Decimal separator position", ex.getMessage());
        }
    }

    @Test
    public void incorrectCharacterTest() {
        try {
            String input = "3 $ 2 % 1";

            calculator.calculate(input);
            Assert.fail();
        }catch (IllegalArgumentException ex) {
            assertEquals("Incorrect character!", ex.getMessage());
        }
    }
}
