package org.Calculator.operations.consoleOperations;

import java.math.BigDecimal;

public class Division implements IOperation {
    @Override
    public int getPriority() {
        return 2;
    }

    @Override
    public BigDecimal operationResult(BigDecimal first, BigDecimal second) {
        if(first.doubleValue() != 0) {
            return second.divide(first); // порядок изменен из-за использования Stack
        } else {
            throw new ArithmeticException("dividing by zero");
        }
    }
}
