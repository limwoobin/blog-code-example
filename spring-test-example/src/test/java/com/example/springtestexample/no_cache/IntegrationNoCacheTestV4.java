package com.example.springtestexample.no_cache;

import com.example.springtestexample.ApplicationContextInitListener;
import com.example.springtestexample.config.MyService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.context.ApplicationContext;

@SpringBootTest
public class IntegrationNoCacheTestV4 {

  @SpyBean
  private MyService spyMyService;

  @Autowired
  private ApplicationContext applicationContext;

  @Autowired
  private ApplicationContextInitListener initListener;

  @Test
  void test() {
    System.out.println("mockMyService: " + spyMyService);

    System.out.println("applicationContext.hashCode(): " + applicationContext.hashCode());
    System.out.println("context init Count: " + initListener.getContextInitCount());
  }
}
