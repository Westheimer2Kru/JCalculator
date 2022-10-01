package org.Calculator.operations.consoleOperations;

import java.math.BigDecimal;

public interface IOperation{
    int getPriority();

    BigDecimal operationResult(BigDecimal first, BigDecimal second);
}
