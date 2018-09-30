package org.calculator.impl;


/**
 * the enum represents the expression operation in regards to the variable the expression will be assigned to.
 * the supported options are
 * ++
 * +=
 * =
 * each option will also include the function which needs to be exacted on the current value and result based on the type.
 */
enum ExpressionTypeOperator {
        PLUS_PLUS((c,r)-> ++c,"i++"),
        PLUS_EQUALS((c,r)-> c+r,"i+=1"),
        EQUALS((c,r)-> r,"i=1");

    private ExpressionOperator expressionOperator;
    private String example;

    ExpressionTypeOperator(ExpressionOperator expressionOperator,String example){
        this.expressionOperator=expressionOperator;
    }

    public ExpressionOperator getExpressionOperator() {
        return expressionOperator;
    }

    public String getExample() {
        return example;
    }

    public static String getListOfSupportedExpressionTypeOperator(){
        StringBuilder sb = new StringBuilder();
        sb.append("the supported operations are: ");
        for (ExpressionTypeOperator eto: ExpressionTypeOperator.values()){
            sb.append(eto.getExample()).append(",");
        }
        return sb.toString();
    }
}