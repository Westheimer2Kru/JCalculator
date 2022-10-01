package org.CalculatorTest;


import org.Calculator.ConsoleCalculator;
import org.Calculator.ICalculator;
import org.junit.Test;

import java.math.BigDecimal;

import static junit.framework.TestCase.assertEquals;


public class ConsoleCalculatorTest {

    ICalculator calculator = ConsoleCalculator.getInstance();


    @Test
    public void requestedInputTest(){
        String input = "1+2-3";

        assertEquals(new BigDecimal("0"), calculator.calculate(input));
    }

    @Test
    public void simpleCalculationsTest(){
        String input = "30 * 2 - 15";

        assertEquals(new BigDecimal("45"), calculator.calculate(input));

        input = "3.25 * 4 - 10.5";

        assertEquals(0, new BigDecimal("2.5").compareTo(calculator.calculate(input)));
    }

    @Test
    public void orderTest(){
        String input = "4 + 8 / 2 * 3";

        assertEquals(new BigDecimal("16"), calculator.calculate(input));

        input = "30 - 9.68 / 2.2 * 3.15";

        assertEquals(0, new BigDecimal("16.14").compareTo(calculator.calculate(input)));
    }

    @Test
    public void calculationsWithBracketTest(){
        String input = "12 + 4 * (30 - 22) - (10 + 5) / (12 - 9)";

        assertEquals(new BigDecimal("39"), calculator.calculate(input));

        input = "10 + 3.4 * (13.3 + 6.7) - (11.1 - 1.1) / (2 * 2.5)";

        assertEquals(0, new BigDecimal("76").compareTo(calculator.calculate(input)));
    }

    @Test
    public void additionalSpacesRemoveTest(){
        String input = "    13 +   5 -   4 / 2    " +
                "                                   ";

        assertEquals(new BigDecimal("16"), calculator.calculate(input));

        input = "   13.3 +   5   .5 - 5 .  6 / 2";

        assertEquals(0, new BigDecimal("16").compareTo(calculator.calculate(input)));
    }
}

