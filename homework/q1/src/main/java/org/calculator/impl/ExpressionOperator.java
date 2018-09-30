package org.calculator.impl;

public interface ExpressionOperator {

    /**
     * the method will execute the calculation where currant is the existing value of a variable
     * and result is the expression result as it was calculated.
     * @param current
     * @param result
     * @return result of the execution of the variable operation on the input.
     */
    Double apply(Double current,Double result);
}
