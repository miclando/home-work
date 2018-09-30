package org.calculator;

import static org.junit.Assert.*;

import org.calculator.Exceptions.InvalidExpression;
import org.calculator.Exceptions.UndefinedVariableException;
import org.calculator.Exceptions.UnsupportedOperatorException;
import org.calculator.impl.Calculator;
import org.junit.Before;
import org.junit.Rule;

import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.util.ArrayList;
import java.util.List;

public class CalculatorTest {

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    private List<String> expressions;
    private List<String> expectedResult;

    @Before
    public void setUp() {
        this.expressions=new ArrayList<>();
        this.expectedResult=new ArrayList<>();
    }

    @Test
    public void basicExpression1(){
        this.expressions.add("i = 1");
        Calculator calculator = new Calculator();
        List<String> res = calculator.calculate(this.expressions);
        this.expectedResult.add("i=1.0");
        assertEquals("Calculate expression:"+this.expressions+":",this.expectedResult,res);
    }

    @Test
    public void basicExpression2(){
        this.expressions.add("i = 1 + 1");
        Calculator calculator = new Calculator();
        List<String> res = calculator.calculate(this.expressions);
        this.expectedResult.add("i=2.0");
        assertEquals("Calculate expression:"+this.expressions+":",this.expectedResult,res);
    }

    @Test
    public void basicExpression5(){
        this.expressions.add("i = 1 - 1");
        Calculator calculator = new Calculator();
        List<String> res = calculator.calculate(this.expressions);
        this.expectedResult.add("i=0.0");
        assertEquals("Calculate expression:"+this.expressions+":",this.expectedResult,res);
    }

    @Test
    public void basicExpression6(){
        this.expressions.add("i = 1 * 2");
        Calculator calculator = new Calculator();
        List<String> res = calculator.calculate(this.expressions);
        this.expectedResult.add("i=2.0");
        assertEquals("Calculate expression:"+this.expressions+":",this.expectedResult,res);
    }

    @Test
    public void basicExpression7(){
        this.expressions.add("i = 4 / 2");
        Calculator calculator = new Calculator();
        List<String> res = calculator.calculate(this.expressions);
        this.expectedResult.add("i=2.0");
        assertEquals("Calculate expression:"+this.expressions+":",this.expectedResult,res);
    }

    @Test
    public void basicExpression8(){
        this.expressions.add("i = 0");
        this.expressions.add("i = i++");
        Calculator calculator = new Calculator();
        List<String> res = calculator.calculate(this.expressions);
        this.expectedResult.add("i=0.0");
        assertEquals("Calculate expression:"+this.expressions+":",this.expectedResult,res);
    }

    @Test
    public void basicExpression9(){
        this.expressions.add("i = 0");
        this.expressions.add("i = ++i");
        Calculator calculator = new Calculator();
        List<String> res = calculator.calculate(this.expressions);
        this.expectedResult.add("i=1.0");
        assertEquals("Calculate expression:"+this.expressions+":",this.expectedResult,res);
    }

    @Test
    public void basicExpression10(){
        this.expressions.add("i = 2+2");
        this.expressions.add("i = 1");
        Calculator calculator = new Calculator();
        List<String> res = calculator.calculate(this.expressions);
        this.expectedResult.add("i=1.0");
        assertEquals("Calculate expression:"+this.expressions+":",this.expectedResult,res);
    }

    @Test
    public void basicExpression11(){
        this.expressions.add("i = 0");
        this.expressions.add("i++");
        Calculator calculator = new Calculator();
        List<String> res = calculator.calculate(this.expressions);
        this.expectedResult.add("i=1.0");
        assertEquals("Calculate expression:"+this.expressions+":",this.expectedResult,res);
    }

    @Test
    public void basicExpression12(){
        this.expressions.add("i = 2*3+1-5");
        Calculator calculator = new Calculator();
        List<String> res = calculator.calculate(this.expressions);
        this.expectedResult.add("i=2.0");
        assertEquals("Calculate expression:"+this.expressions+":",this.expectedResult,res);
    }
    @Test
    public void basicExpression13(){
        this.expressions.add("i = 2");
        this.expressions.add("j = i*3+1-5");
        Calculator calculator = new Calculator();
        List<String> res = calculator.calculate(this.expressions);
        this.expectedResult.add("i=2.0");
        this.expectedResult.add("j=2.0");
        assertEquals("Calculate expression:"+this.expressions+":",this.expectedResult,res);
    }

    @Test
    public void basicExpression14(){
        this.expressions.add("i = 2");
        this.expressions.add("j = i++*3+1-5");
        Calculator calculator = new Calculator();
        List<String> res = calculator.calculate(this.expressions);
        this.expectedResult.add("i=3.0");
        this.expectedResult.add("j=2.0");
        assertEquals("Calculate expression:"+this.expressions+":",this.expectedResult,res);

    }

    @Test
    public void basicExpression15(){
        this.expressions.add("i = 2");
        this.expressions.add("j = ++i*3+1-5");
        Calculator calculator = new Calculator();
        List<String> res = calculator.calculate(this.expressions);
        this.expectedResult.add("i=3.0");
        this.expectedResult.add("j=5.0");
        assertEquals("Calculate expression:"+this.expressions+":",this.expectedResult,res);

    }



    @Test
    public void basicExpression16(){
        this.expressions.add("i = 20");
        Calculator calculator = new Calculator();
        List<String> res = calculator.calculate(this.expressions);
        this.expectedResult.add("i=20.0");
        assertEquals("Calculate expression:"+this.expressions+":",this.expectedResult,res);
    }


    @Test
    public void basicExpression17(){
        this.expressions.add("i = 10");
        this.expressions.add("j = ++i*3+10-50");
        Calculator calculator = new Calculator();
        List<String> res = calculator.calculate(this.expressions);
        this.expectedResult.add("i=11.0");
        this.expectedResult.add("j=-7.0");
        assertEquals("Calculate expression:"+this.expressions+":",this.expectedResult,res);
    }
    @Test
    public void basicExpression18(){
        this.expressions.add("i = 10/3");
        Calculator calculator = new Calculator();
        List<String> res = calculator.calculate(this.expressions);
        this.expectedResult.add("i=3.3333333333333335");
        assertEquals("Calculate expression:"+this.expressions+":",this.expectedResult,res);
    }

    @Test
    public void basicExpression19(){
        this.expressions.add("i = 10");
        this.expressions.add("j = 10");
        this.expressions.add("i += j");
        Calculator calculator = new Calculator();
        List<String> res = calculator.calculate(this.expressions);
        this.expectedResult.add("i=20.0");
        this.expectedResult.add("j=10.0");
        assertEquals("Calculate expression:"+this.expressions+":",this.expectedResult,res);
    }

    @Test
    public void basicExpression20(){
        this.expressions.add("j = 1-3+10*2");
        Calculator calculator = new Calculator();
        List<String> res = calculator.calculate(this.expressions);
        this.expectedResult.add("j=18.0");
        assertEquals("Calculate expression:"+this.expressions+":",this.expectedResult,res);
    }

    @Test
    public void basicExpression21(){
        thrown.expect(InvalidExpression.class);
        this.expressions.add("i =+ 2");
        Calculator calculator = new Calculator();
        calculator.calculate(this.expressions);

    }


    @Test
    public void basicExpression22(){
        thrown.expect(InvalidExpression.class);
        this.expressions.add("i 2");
        Calculator calculator = new Calculator();
        calculator.calculate(this.expressions);

    }


    @Test
    public void basicExpression23(){
        thrown.expect(InvalidExpression.class);
        this.expressions.add("2");
        Calculator calculator = new Calculator();
        calculator.calculate(this.expressions);

    }

    @Test
    public void basicExpression24(){
        thrown.expect(UnsupportedOperatorException.class);
        this.expressions.add("i =2^2");
        Calculator calculator = new Calculator();
        calculator.calculate(this.expressions);

    }

    @Test
    public void basicExpression25(){
        thrown.expect(UnsupportedOperatorException.class);
        this.expressions.add("i =1+2%2");
        Calculator calculator = new Calculator();
        List<String> res = calculator.calculate(this.expressions);

    }

    @Test
    public void basicExpression26(){
        thrown.expect(UndefinedVariableException.class);
        this.expressions.add("i =1+k-2");
        Calculator calculator = new Calculator();
        List<String> res = calculator.calculate(this.expressions);

    }


}