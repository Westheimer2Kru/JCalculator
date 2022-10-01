package org.Calculator.operations.consoleOperations;

import java.math.BigDecimal;

public class Subtraction implements IOperation {
    @Override
    public int getPriority() {
        return 1;
    }

    @Override
    public BigDecimal operationResult(BigDecimal first, BigDecimal second) {
        return second.subtract(first); // порядок изменен из-за использования Stack
    }
}
