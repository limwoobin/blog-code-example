package com.example.springtestexample;

import com.example.springtestexample.config.MyService;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;

@TestConfiguration
public class MyServiceTestConfig {

  @Autowired
  private MyService myService;

  @Bean
  public MyService mockMyService() {
    return Mockito.mock(MyService.class);
  }

  @Bean
  public MyService spyMyService() {
    return Mockito.spy(myService);
  }
}
