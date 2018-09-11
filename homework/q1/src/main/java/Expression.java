import java.util.Map;
import java.util.Stack;

public class Expression {

    private final Map<Character, Double> variables;
    private Character var;
    private VarOperation varOperation;
    private String expression;
    private Double result;
    Stack<Double> numberStack;
    Stack<Operator> operationStack;




    public Expression(String expression, Map<Character,Double> variables) {
        this.expression=expression;
        this.variables=variables;
        this.numberStack=new Stack<>();
        this.operationStack=new Stack<>();
    }


    /**
     * i++
     * i = 1
     * i += 1
     * i = 1 + 1
     */
    public void evaluate(){
        int index =0;

        index= getIndexAfterSpace(index);
        char character = expression.charAt(index);
        if(Character.isAlphabetic(character)){
            this.var = character;
            index++;
        }
        else{
            throw new InvalidExpression(createMessage());
        }
        index=getIndexAfterSpace(index);
        character = expression.charAt(index);
        if(character == '='){
            index++;
            this.varOperation= VarOperation.EQUALS;
        }
        else if (character == '+' ){
            if(index +1 < expression.length()){
                index++;
                character = expression.charAt(index);
                if(character == '+' ){
                    this.varOperation= VarOperation.PLUS_PLUS;
                    Double varValue = variables.get(var);

                }
                else if (character == '=' ){
                    this.varOperation= VarOperation.PLUS_EQUALS;
                }
                else{
                    throw new InvalidExpression(createMessage());
                }
            }
            else{
                throw new InvalidExpression(createMessage());
            }
        }
        calculate(index);
    }


    private Double calculate(int offset) {

        for (int index = offset; index < expression.length(); index++) {
            index = parseNumber(index);
            index = parseVariable(index);
            index = parseOperator(index);
        }
        while (numberStack.size() >= 2 && this.operationStack.size() >= 1) {
            double second = numberStack.pop();
            double first = numberStack.pop();
            Operator op = this.operationStack.pop();
            double result = applyOp(first, op, second);
            numberStack.push(result);
        }
        if(numberStack.size() == 1 && this.operationStack.size() == 0){
            return numberStack.pop();
        }
        throw new InvalidExpression("");
    }


    private String  createMessage(){
        StringBuilder sb =new StringBuilder();
        sb.append("the provided expression:")
                .append(expression)
                .append(" does not match  the exacted pattern, the expression must start with:")
                .append("i++").append("i =").append("i +=");
        return sb.toString();
    }

    int getIndexAfterSpace(int index){
        while (true){
            char character = expression.charAt(index);
            if(Character.isSpaceChar(character)){
                if( index <expression.length()){
                    index++;
                }
                else{

                    throw new InvalidExpression(createMessage());
                }
            }
            else if(index == expression.length()){
                throw new InvalidExpression(createMessage());
            }
            else{
                break;
            }

        }
        return index;
    }



    public Character getVar() {
        return var;
    }

    public Double getResult() {
        return result;
    }

    enum VarOperation {
        PLUS_PLUS,
        PLUS_EQUALS,
        EQUALS;
    }

    @Override
    public String toString() {
        return "Expression{" +
                "variables=" + variables +
                ", var=" + var +
                ", varOperation=" + varOperation +
                ", expression='" + expression + '\'' +
                ", result=" + result +
                ", numberStack=" + numberStack +
                ", operationStack=" + operationStack +
                '}';
    }


    private int parseNumber(int index) {
        index = getNextIndexAfterSpaces(index);
        Character character = expression.charAt(index);
        if(Character.isDigit(character)) {
            index++;
            StringBuilder sb = new StringBuilder();
            while (index < expression.length() && Character.isDigit(expression.charAt(index))) {
                sb.append(expression.charAt(index));
                index++;
            }
        }
        else if(Character.isLetter(character) ){
            if(index +2 < expression.length()
                    && expression.charAt(index+1)=='+'
                    && expression.charAt(index+2)=='+'){
                Double varValue = variables.get(character);
                this.numberStack.push(varValue);
                variables.put(character,variables.get(character)+1);
                index+=2;
            }
            else{
                Double varValue = variables.get(character);
                this.numberStack.push(varValue);
                index++;
            }
        }
        else if(character== '+' ){
            if(index +2 < expression.length()
                    && expression.charAt(index+1)=='+'
                    && Character.isLetter(expression.charAt(index+2))){
                character=expression.charAt(index+2);
                Double varValue = variables.get(character)+1;
                this.numberStack.push(varValue);
                variables.put(character,varValue);
                index+=2;
            }
        }
        index = getNextIndexAfterSpaces(index);
        return index;
    }

    private int getNextIndexAfterSpaces(int index) {
        while (index < expression.length() && Character.isSpaceChar(expression.charAt(index))) {
            index++;
        }
        return index;
    }

    private int parseVariable(int index) {
        index = getIndexAfterSpace(index);
        Character character = expression.charAt(index);
        if(Character.isLetter(character) ){
            if(index +2 < expression.length()
                        && expression.charAt(index+1)=='+'
                        && expression.charAt(index+2)=='+'){
                    Double varValue = variables.get(character);
                    this.numberStack.push(varValue);
                    variables.put(character,variables.get(character)+1);
                    index+=2;
                }
            else{
                Double varValue = variables.get(character);
                this.numberStack.push(varValue);
                index++;
            }
        }
        else if(character== '+' ){
            if(index +2 < expression.length()
                    && expression.charAt(index+1)=='+'
                    && Character.isLetter(expression.charAt(index+2))){
                character=expression.charAt(index+2);
                Double varValue = variables.get(character)+1;
                this.numberStack.push(varValue);
                variables.put(character,varValue);
                index+=2;
            }
        }
        index = getIndexAfterSpace(index);
        return index;
    }


    public static void main(String [] args){

        Expression e = new Expression("i++", null);
        e.evaluate();
        System.out.println(e);

        e = new Expression("i+=1", null);
        e.evaluate();
        System.out.println(e);

        e = new Expression("i += 1", null);
        e.evaluate();
        System.out.println(e);
        e = new Expression("i +=1+1", null);
        e.evaluate();
        System.out.println(e);

        e = new Expression("i =1 +1 ", null);
        e.evaluate();
        System.out.println(e);
        e = new Expression("i=", null);
        e.evaluate();
        System.out.println(e);

        e = new Expression(" i =", null);
        e.evaluate();
        System.out.println(e);

        e = new Expression(" i + =", null);
        e.evaluate();
        System.out.println(e);

    }


    public enum Operator{ADD, SUBTRACT, MULTIPLY, DIVIDE, BLANK}

    private double applyOp(double left, Operator op, double right){
        switch (op){
            case ADD: return left + right;
            case SUBTRACT: return left - right;
            case MULTIPLY: return left * right;
            case DIVIDE: return left / right;
            default: return right;
        }

    }

    private int priorityOfOperator(Operator op){
        switch (op){
            case ADD: return 1;
            case SUBTRACT: return 1;
            case MULTIPLY: return 2;
            case DIVIDE: return 2;
            case BLANK: return 0;
        }
        return 0;
    }

    private int parseOperator( int index){
        if(index < this.expression.length()) {
            char character = this.expression.charAt(index);
            Operator operator=null;
            switch (character) {
                case '+':
                    operator = Operator.ADD;
                    index++;
                    break;
                case '-':
                    operator = Operator.SUBTRACT;
                    index++;
                    break;
                case '*':
                    operator = Operator.MULTIPLY;
                    index++;
                    break;
                case '/':
                    operator = Operator.DIVIDE;
                    index++;
                    break;
                default:
                    throw new RuntimeException("unsuported operator");

            }

            while (numberStack.size() >= 2 && this.operationStack.size() >= 1) {
                if (priorityOfOperator(operator) <= priorityOfOperator(this.operationStack.peek())) {
                    double second = numberStack.pop();
                    double first = numberStack.pop();
                    Operator op = this.operationStack.pop();
                    double result = applyOp(first, op, second);
                    numberStack.push(result);
                } else {
                    break;
                }
            }
        }
        return index;



    }

}
