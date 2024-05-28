package com.example.springtestexample.temp;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;

@SpringBootTest
@DisplayName(value = "zz")
public class TempTestB {

  @Autowired
  private ApplicationContext applicationContext;

  @Test
  void test() {
    System.out.println("Test B");
  }
}
