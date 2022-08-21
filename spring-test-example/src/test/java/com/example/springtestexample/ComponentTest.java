package com.example.springtestexample;

import com.example.springtestexample.config.MyComponent;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.MethodMode;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class ComponentTest {

    @Autowired
    private MyComponent myComponent;

    @Test
    @Order(1)
    void test() {
        System.out.println(myComponent.hashCode());
    }

    @Test
    @Order(2)
    @DirtiesContext(methodMode = MethodMode.BEFORE_METHOD)
    void test2() {
        System.out.println(myComponent.hashCode());
    }
}
