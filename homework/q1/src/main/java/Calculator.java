
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Calculator{

    Map<Character,Integer> variables;

    public Calculator() {
        this.variables = new HashMap<>();
    }

    public List<String> calculate(List<String> expressions){

        for(String expression : expressions){
            Expression exp = new Expression(expression,variables);
            exp.evaluate();
            variables.put(exp.getVar(),exp.getResult());
        }

        return variables.entrySet()
                .stream()
                .map((e)-> (e.getKey()+"="+e.getValue()) )
                .collect(Collectors.toList());
        
    }


}