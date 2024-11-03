package com.example.springtestexample.no_cache;

import com.example.springtestexample.ApplicationContextInitListener;
import com.example.springtestexample.service.MyService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.ApplicationContext;

@SpringBootTest
public class IntegrationNoCacheTestV2 {

  @MockBean
  private MyService mockMyService;

  @Autowired
  private ApplicationContext applicationContext;

  @Autowired
  private ApplicationContextInitListener initListener;

  @Test
  void test() {
    System.out.println("mockMyService: " + mockMyService);

    System.out.println("applicationContext.hashCode(): " + applicationContext.hashCode());
    System.out.println("context init Count: " + initListener.getContextInitCount());
  }

}
