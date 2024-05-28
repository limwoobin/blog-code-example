package com.example.springtestexample.temp;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.test.annotation.DirtiesContext;

@SpringBootTest
//@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_CLASS)
@DisplayName(value = "z")
public class TempTestA {

  @Autowired
  private ApplicationContext applicationContext;

  @Test
  void test() {
    System.out.println("Test A");
  }
}
