package org.calculator.Exceptions;

public class InvalidExpression extends RuntimeException{

    public InvalidExpression(String message) {
        super(message);
    }
}
