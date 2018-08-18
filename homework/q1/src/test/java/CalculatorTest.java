

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class CalculatorTest {

    private List<String> expressions;

    @Before
    public void setUp() throws Exception {
        this.expressions=new ArrayList<>();
    }

    @Test
    public void basicExpression1(){
        this.expressions.add("i = 1");
        Calculator calculator = new Calculator();
        List<String> res = calculator.calculate(this.expressions);
        ArrayList<String> expectedList = new ArrayList<>();
        expectedList.add("i=1");
        assertTrue("expression:"+this.expressions+" expected result:",res.equals(expectedList));
    }


    @Test
    public void basicExpression3(){
        this.expressions.add("i = 1 + 1");
        Calculator calculator = new Calculator();
        List<String> res = calculator.calculate(this.expressions);
        assertTrue(res.get(1).equals("i=2"));
    }

    @Test
    public void basicExpression4(){
        this.expressions.add("i = 1 - 1");
        Calculator calculator = new Calculator();
        List<String> res = calculator.calculate(this.expressions);
        assertTrue(res.get(1).equals("i=0"));
    }

    @Test
    public void basicExpression5(){
        this.expressions.add("i = 1 * 2");
        Calculator calculator = new Calculator();
        List<String> res = calculator.calculate(this.expressions);
        assertTrue(res.get(1).equals("i=2"));
    }

    @Test
    public void basicExpression6(){
        this.expressions.add("i = 4 / 2");
        Calculator calculator = new Calculator();
        List<String> res = calculator.calculate(this.expressions);
        assertTrue(res.get(1).equals("i=2"));
    }

    @Test
    public void basicExpression7(){
        this.expressions.add("i = 0");
        this.expressions.add("i = i++");
        Calculator calculator = new Calculator();
        List<String> res = calculator.calculate(this.expressions);
        assertTrue(res.get(1).equals("i=2"));
    }

}