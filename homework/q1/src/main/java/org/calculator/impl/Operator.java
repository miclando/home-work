package org.calculator.impl;

/**
 * the enum represents the math operations suported by the aplication
 * the supported operations are :
 * +
 * -
 * /
 * *
 * each option includes the method to execute for calculation oit the operation.
 */
public enum Operator {
    ADD(1,(l , r) -> l + r),
    SUBTRACT(1,(l , r) -> l - r),
    MULTIPLY(2,(l , r) -> l * r),
    DIVIDE(2,(l , r) -> l / r);

    int priority;
    DoubleBinaryOperator binaryOperator;

    Operator(int priority,DoubleBinaryOperator binaryOperator){
        this.priority=priority;
        this.binaryOperator=binaryOperator;

    }

    private int getPriority() {
        return priority;
    }

    public DoubleBinaryOperator getBinaryOperator() {
        return binaryOperator;
    }

    public boolean isPriorityHigher(Operator operator){
        return this.getPriority()<operator.getPriority();
    }
}