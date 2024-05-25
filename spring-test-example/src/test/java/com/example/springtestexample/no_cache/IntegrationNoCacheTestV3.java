package com.example.springtestexample.no_cache;

import com.example.springtestexample.ApplicationContextInitListener;
import com.example.springtestexample.config.MyComponent;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.ApplicationContext;

@SpringBootTest
public class IntegrationNoCacheTestV3 {

  @MockBean
  private MyComponent mockMyComponent;

  @Autowired
  private ApplicationContext applicationContext;

  @Autowired
  private ApplicationContextInitListener initListener;

  @Test
  void test() {
    System.out.println("myComponent: " + mockMyComponent);

    System.out.println("applicationContext.hashCode(): " + applicationContext.hashCode());
    System.out.println("context init Count: " + initListener.getContextInitCount());
  }
}
