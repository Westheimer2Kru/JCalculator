package org.Calculator.operations.consoleOperations;

import java.math.BigDecimal;

public class Multiplication implements IOperation {
    @Override
    public int getPriority() {
        return 2;
    }

    @Override
    public BigDecimal operationResult(BigDecimal first, BigDecimal second) {
        return first.multiply(second);
    }
}
