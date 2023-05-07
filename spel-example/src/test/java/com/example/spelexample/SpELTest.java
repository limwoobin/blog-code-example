package com.example.spelexample;

import com.example.spelexample.application.SpELApplication;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class SpELTest {
    @Autowired
    private SpELApplication spELApplication;

    @Test
    void spELCallTest() {
        spELApplication.spELCall("test annotation");
    }
}
