package org.Calculator.operations.consoleOperations;

import java.math.BigDecimal;

public class Addition implements IOperation {
    @Override
    public int getPriority() {
        return 1;
    }

    @Override
    public BigDecimal operationResult(BigDecimal first, BigDecimal second) {
        return first.add(second);
    }
}
