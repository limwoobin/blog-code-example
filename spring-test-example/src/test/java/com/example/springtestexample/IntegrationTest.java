package com.example.springtestexample;

import com.example.springtestexample.config.CustomFeatureRegisterer;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ContextConfiguration;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(value = RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@SpringBootTest
@ContextConfiguration(classes = MyServiceTestConfig.class)
@Import(CustomFeatureRegisterer.class)
public @interface IntegrationTest {
}
