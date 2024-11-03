package com.example.springtestexample;

import com.example.springtestexample.service.MyService;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;

@TestConfiguration
public class MyServiceTestConfig {

  @Autowired
  private MyService testMyService;

  @Bean
  @Primary
  public MyService testMyService() {
    return testMyService;
  }

  @Bean
  public MyService mockMyService() {
    return Mockito.mock(MyService.class);
  }

  @Bean
  public MyService spyMyService() {
    return Mockito.spy(testMyService);
  }
}
