package org.calculator.impl;

import org.calculator.Exceptions.InvalidExpression;
import org.calculator.Exceptions.UndefinedVariableException;
import org.calculator.Exceptions.UnsupportedOperatorException;

import java.util.Map;
import java.util.Stack;

public class Expression {

    private final Map<Character, Double> variables;
    private Character var;
    private ExpressionTypeOperator expressionTypeOperator;
    private String expression;
    private Stack<Double> numberStack;
    private Stack<Operator> operationStack;


    /**
     * the constructor
     * @param expression the expreasion for evaluation
     * @param variables a map with the variables calculated so far.
     */
    public Expression(String expression, Map<Character,Double> variables) {
        this.expression=expression;
        this.variables=variables;
        this.numberStack=new Stack<>();
        this.operationStack=new Stack<>();
    }


    /**
     * the method starts the evolution of the expression passed to the class.
     *
     * @return a map of variables including the newly calculated one.
     */
    public Map<Character, Double> evaluate(){
        int offset =0;
        offset= parseVariableAndExpressionType(this.expression,offset);
        return calculate(this.expression,offset);

    }

    /**
     * the method parses the expression to retrieve the variable and the type of action to do on it after the expression is evaluated to.
     * @param expression the expression we are working on
     * @param offset the current position in the expression
     * @return the index we should work on now.
     */
    private int parseVariableAndExpressionType(String expression, int offset){
        offset= trimSpaces(expression,offset);
        offset = getVariable(expression, offset);
        offset= trimSpaces(expression,offset);
        offset = getExpressionType(expression, offset);
        return offset;
    }

    /**
     * the method parses the expression to identify the type.
     * the type can be =,++,+=
     * @param expression the expression we are working on
     * @param offset the current position in the expression
     * @return the index we should work on now.
     */
    private int getExpressionType(String expression, int offset) {
        char character;
        if(offset<=expression.length()) {
            character = expression.charAt(offset);
            if (character == '=') {
                offset++;
                this.expressionTypeOperator = ExpressionTypeOperator.EQUALS;
                return offset;
            } else if (character == '+') {
                if (offset + 1 < expression.length()) {
                    offset++;
                    character = expression.charAt(offset);
                    if (character == '+') {
                        this.expressionTypeOperator = ExpressionTypeOperator.PLUS_PLUS;
                        offset++;
                        return offset;
                    } else if (character == '=') {
                        this.expressionTypeOperator = ExpressionTypeOperator.PLUS_EQUALS;
                        offset++;
                        return offset;
                    }
                }
            }
        }
        throw new InvalidExpression(createMessage(expression));

    }

    /**
     * the method used to retrieve the variable the expression should be assigned to.
     * @param expression the expression we are working on
     * @param offset the current position in the expression
     * @return the index we should work on now.
     */
    private int getVariable(String expression, int offset) {
        char character ;
        if(offset <= expression.length() &&  Character.isAlphabetic(character = expression.charAt(offset))){
            this.var = character;
            offset++;
        }
        else{
            throw new InvalidExpression(createMessage(expression));
        }
        return offset;
    }


    /**
     * the method calculates the expression.
     * it will evaluate the mathe expression ands it will store the result in the map.
     * any changes to the variables values during the evaluation will be updated in the map returned
     * if the provided expression is not valid an {@link InvalidExpression} will be thrown
     * @param expression the expression we want to evaluate
     * @param offset the position from which the expression should be located at, the position is after the parsing of the variable and the operation on it.
     * @return a map of variables including the newly calculated ones.
     */
    private Map<Character, Double> calculate(String expression, int offset) {
        Double result=null;
        if(!ExpressionTypeOperator.PLUS_PLUS.equals(this.expressionTypeOperator)){
            for (int index = offset; index < expression.length(); index++) {
                index = parseNumber(expression, index);
                index = parseVariable(expression, index);
                index = parseOperatorAndApply(expression, index);
            }
            while (numberStack.size() >= 2 && this.operationStack.size() >= 1) {
                collapseStack();
            }
            if (numberStack.size() == 1 && this.operationStack.size() == 0) {
                result=numberStack.pop();
            }
            else{
                throw new InvalidExpression("the provide expression "+this.expression+" is not a valid expression.");
            }

        }
        variables.put(this.getVar(),this.expressionTypeOperator.getExpressionOperator().apply(variables.get(this.getVar()),result));
        return variables;
    }


    /**
     * the method handles the digits in the expression,
     * it will aggregate them into a double and push it to the stack for calculation.
     * @param expression the expression we want to evaluate
     * @param offset the position from which the expression should be located at
     * @return the new offset to be used after handling the digits
     */
    private int parseNumber(String expression, int offset) {
        offset = trimSpaces(expression,offset);
        if(offset < expression.length()) {
            Character character = expression.charAt(offset);
            if (Character.isDigit(character)) {

                StringBuilder sb = new StringBuilder();
                sb.append(expression.charAt(offset));
                offset++;
                while (offset < expression.length() && Character.isDigit(expression.charAt(offset))) {
                    sb.append(expression.charAt(offset));
                    offset++;
                }
                this.numberStack.push(Double.parseDouble(sb.toString()));
            }
            offset = trimSpaces(expression, offset);
        }
        return offset;
    }


    /**
     * the method will evaluate the variable retrieve its value and aply the relevant calculation ++i or i++ according to the expression.
     * the result will be updated in the variable map and pushed into the stack as a double.
     * @param expression the expression we want to evaluate
     * @param offset the position from which the expression should be located at
     * @return the new offset to be used after handling the variable and its operation
     */
    private int parseVariable(String expression,int offset) {
        offset = trimSpaces(expression,offset);
        if(offset < expression.length()) {
            Character character = expression.charAt(offset);
            if (Character.isLetter(character)) {
                if (offset + 2 < expression.length()
                        && expression.charAt(offset + 1) == '+'
                        && expression.charAt(offset + 2) == '+') {
                    Double varValue =  getVariableValue(character);
                    this.numberStack.push(varValue);
                    variables.put(character,  getVariableValue(character) + 1);
                    offset += 3;
                } else {
                    Double varValue =  getVariableValue(character);
                    this.numberStack.push(varValue);
                    offset++;
                }
            } else if (character == '+') {
                if (offset + 2 < expression.length()
                        && expression.charAt(offset + 1) == '+'
                        && Character.isLetter(expression.charAt(offset + 2))) {
                    character = expression.charAt(offset + 2);
                    Double varValue = variables.get(character) + 1;
                    this.numberStack.push(varValue);
                    variables.put(character, varValue);
                    offset += 3;
                }
            }
            offset = trimSpaces(expression,offset);
        }

        return offset;
    }

    /**
     * the method will retrieve the value of the given variable,
     * in case thr variable has not be provided so far an {@link UndefinedVariableException} will be thrown
     * @param character the variable for which we want to retrieve the value for.
     * @return the double value of the variable.
     */
    private Double getVariableValue(Character character) {
        Double varValue = variables.get(character);
        if (varValue == null) {
            throw new UndefinedVariableException("now variable: " + character + " was defined until now");
        }
        return varValue;
    }

    /**
     * the method parses the operation to do on the digits,
     * and pushes it to the stack.
     * if the operation priority is lower then the last parced operation the previous operations will be calculated .
     * if the operation used is not supported an {@link UnsupportedOperatorException} will be thrown
     * @param expression the expression we want to evaluate
     * @param index the position from which the expression should be located at
     * @return the new offset to be used after handling the operators
     */
    private int parseOperatorAndApply(String expression, int index){
        if(index < expression.length()) {
            char character = expression.charAt(index);
            Operator operator=null;
            switch (character) {
                case '+':
                    operator = Operator.ADD;
                    break;
                case '-':
                    operator = Operator.SUBTRACT;
                    break;
                case '*':
                    operator = Operator.MULTIPLY;
                    break;
                case '/':
                    operator = Operator.DIVIDE;
                    break;
                default:
                    throw new UnsupportedOperatorException("operator "+character+" is unsupported operator.");

            }
            while (numberStack.size() >= 2
                    && this.operationStack.size() >= 1
                    && !this.operationStack.peek().isPriorityHigher(operator)) {
                collapseStack();
            }
            this.operationStack.push(operator);
        }
        return index;



    }

    /**
     * the method will pop the digits and operator from the stacks and calculate them.
     * the result will be places in the stack.
     */
    private void collapseStack() {
        double second = numberStack.pop();
        double first = numberStack.pop();
        Operator topOperator = this.operationStack.pop();
        double result1 = topOperator.getBinaryOperator().apply(first, second);
        numberStack.push(result1);
    }

    /**
     * the method created the message for the invalid exception
     * @param expression the expression we want to evaluate
     * @return a string massage for the exception
     */
    private String  createMessage(String expression){
        StringBuilder sb =new StringBuilder();
        sb.append("the provided expression:")
                .append(expression)
                .append(" does not match the suported expresion type operations.")
                .append(ExpressionTypeOperator.getListOfSupportedExpressionTypeOperator());
        return sb.toString();
    }

    /**
     * the method trims spaces and progress the ofset to the next no space character
     * @param expression the expression we want to evaluate
     * @param offset the position from which the expression should be located at
     * @return the new offset to be used after handling the spaces
     */
    private int trimSpaces(String expression, int offset){
        while (offset<expression.length() && Character.isSpaceChar(expression.charAt(offset))) {
            offset++;
        }
        return offset;
    }


    private Character getVar() {
        return var;
    }




    @Override
    public String toString() {
        return "org.calculator.impl.Expression{" +
                "variables=" + variables +
                ", var=" + var +
                ", expressionTypeOperator=" + expressionTypeOperator +
                ", expression='" + expression + '\'' +
                ", numberStack=" + numberStack +
                ", operationStack=" + operationStack +
                '}';
    }

}
