package com.example.spelexample.application;

import com.example.spelexample.aop.CustomAnnotation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class SpELApplication {

    @CustomAnnotation(value = "#value")
    public void spELCall(String value) {
    }
}
