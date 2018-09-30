package org.calculator.Exceptions;

public class UndefinedVariableException extends RuntimeException{
    public UndefinedVariableException(String message) {
        super(message);
    }
}
