package org.calculator.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Calculator{

    private Map<Character,Double> variables;

    public Calculator() {
        this.variables = new HashMap<>();
    }

    /**
     * the method calculates all the provided expressions
     * @param expressions a list of string expressions
     * @return a list of variables with there value after the calculation of the expressions.
     */
    public List<String> calculate(List<String> expressions){

        for(String expression : expressions){
            Expression exp = new Expression(expression,variables);
            this.variables=exp.evaluate();
        }
        return createVariableResultList();
        
    }

    /**
     * the method reads all the variables after computation,
     * and creates the result list with the variable and its value after the full computation
     * @return a list of variables with there values.
     */
    private List<String> createVariableResultList(){
        return variables.entrySet()
                .stream()
                .map((e)-> (e.getKey()+"="+e.getValue()) )
                .collect(Collectors.toList());
    }


}