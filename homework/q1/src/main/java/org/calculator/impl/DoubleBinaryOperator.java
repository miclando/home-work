package org.calculator.impl;


public interface DoubleBinaryOperator {
    /**
     * apply the math operation on the two Doubles
     * in the foloing order left <operation> right
     * @param left
     * @param right
     * @return the result of the calculation as a double
     */
    Double apply(Double left,Double right);

}
