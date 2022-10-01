package org.CalculatorTest.operations;

import org.Calculator.operations.consoleOperations.*;
import org.junit.Assert;
import org.junit.Test;

import java.math.BigDecimal;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertTrue;


public class OperationsTest {

    private IOperation operation;

    private String first;
    private String second;

    @Test
    public void additionTest(){
        first = "1";

        second = "3";

        operation = new Addition();

        assertEquals(new BigDecimal("4"), operation.operationResult(new BigDecimal(first), new BigDecimal(second)));

        first = "1.1";

        second = "3.6";

        assertEquals(new BigDecimal("4.7"), operation.operationResult(new BigDecimal(first), new BigDecimal(second)));
    }

    @Test
    public void divisionTest(){
        first = "3";

        second = "9";

        operation = new Division();

        assertEquals(new BigDecimal("3"), operation.operationResult(new BigDecimal(first), new BigDecimal(second)));

        first = "4";

        assertEquals(new BigDecimal("2.25"), operation.operationResult(new BigDecimal(first), new BigDecimal(second)));
    }

    @Test
    public void multiplicationTest(){
        first = "2";

        second = "3";

        operation = new Multiplication();

        assertEquals(new BigDecimal("6"), operation.operationResult(new BigDecimal(first), new BigDecimal(second)));

        first = "1.1";

        second = "3.6";

        assertEquals(new BigDecimal("3.96"), operation.operationResult(new BigDecimal(first), new BigDecimal(second)));
    }

    @Test
    public void subtractionTest(){
        first = "3";

        second = "1";

        operation = new Subtraction();

        assertEquals(new BigDecimal("-2"), operation.operationResult(new BigDecimal(first), new BigDecimal(second)));

        first = "3.6";

        second = "9.1";

        assertEquals(new BigDecimal("5.5"), operation.operationResult(new BigDecimal(first), new BigDecimal(second)));
    }

    @Test
    public void priorityTest() {
        assertTrue(new Addition().getPriority() < new Multiplication().getPriority());
        assertTrue(new Subtraction().getPriority() < new Division().getPriority());
        assertEquals(new Multiplication().getPriority(), new Division().getPriority());
    }

    @Test
    public void dividingByZeroTest() {
        try {
            first = "0";

            second = "3";

            operation = new Division();

            operation.operationResult(new BigDecimal(first), new BigDecimal(second));

            Assert.fail();
        } catch (ArithmeticException ex) {
            assertEquals("dividing by zero", ex.getMessage());
        }
    }
}
