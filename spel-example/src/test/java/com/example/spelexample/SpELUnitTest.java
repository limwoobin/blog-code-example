package com.example.spelexample;


import com.example.spelexample.domain.Car;
import org.junit.jupiter.api.Test;
import org.springframework.expression.EvaluationContext;
import org.springframework.expression.Expression;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;

public class SpELUnitTest {

    @Test
    void test01() {
        ExpressionParser expressionParser = new SpelExpressionParser();
        Expression expression = expressionParser.parseExpression("'Any string'");
        String result = (String) expression.getValue();

        System.out.println(result);
    }

    @Test
    void test02() {
        ExpressionParser expressionParser = new SpelExpressionParser();
        Expression expression = expressionParser.parseExpression("'Any string'.length()");
        int result = (int) expression.getValue();

        System.out.println(result);
    }

    @Test
    void test03() {
        Car car = new Car();
        car.setMake("Good manufacturer");
        car.setModel("Model 3");

        ExpressionParser parser = new SpelExpressionParser();
//        Expression expression = parser.parseExpression("model");
        Expression expression = parser.parseExpression("#model");

        EvaluationContext context = new StandardEvaluationContext(car);
        String result = (String) expression.getValue(context);

        System.out.println(result);
    }
}
