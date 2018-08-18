import java.util.Map;
import java.util.Stack;

public class Expression {

    private final Map<Character, Integer> variables;
    private Character var;
    private VarOperation varOperation;
    private String expression;
    private Integer result;
    Stack<Integer> numberStack;
    Stack<Operation> operationStack;




    public Expression(String expression, Map<Character,Integer> variables) {
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
            this.varOperation= VarOperation.EQUALS;
        }
        else if (character == '+' ){
            if(index +1 < expression.length()){
                index++;
                character = expression.charAt(index);
                if(character == '+' ){
                    this.varOperation= VarOperation.PLUS_PLUS;
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




    private void calculate(int index) {
        while( index < expression.length()){
            char character = expression.charAt(index);
            String temp;
            if (character == '-'){

            }
            else if (character == '+'){

            }
            else if ( character == '*'){

            }
            else if ( character == '/' ) {

            }
            else if(Character.isDigit(character)){

            }
            else if(Character.isLetter(character){
                
            }
        }
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

    public Integer getResult() {
        return result;
    }

    enum VarOperation {
        PLUS_PLUS,
        PLUS_EQUALS,
        EQUALS;
    }

    enum Operation {
        ADDITION,
        SUBTRACTION,
        MULTIPLICATION,
        DIVISION;
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

    public static void main(String [] args){

        Expression e = new Expression("i++", null);
        e.evaluate();
        System.out.println(e);

        e = new Expression("i+=", null);
        e.evaluate();
        System.out.println(e);

        e = new Expression("i +=", null);
        e.evaluate();
        System.out.println(e);
        e = new Expression("i +=", null);
        e.evaluate();
        System.out.println(e);

        e = new Expression("i =", null);
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


}
