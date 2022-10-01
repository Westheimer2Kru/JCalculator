package org.CalculatorTest.Utils;

import org.Calculator.Utils.Utils;
import org.junit.Test;

import static junit.framework.TestCase.assertEquals;

public class UtilsTest {

    @Test
    public void trimTest(){
        String input = "  10 + 123 - 3        "
                + "- 23 + 23 +            2   ";
        String expected = "10+123-3-23+23+2";

        assertEquals(expected, Utils.removeSpaces(input));
    }
}
