package com.example.springtestexample.no_cache;

import com.example.springtestexample.ApplicationContextInitListener;
import com.example.springtestexample.config.MyService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;

@SpringBootTest
public class IntegrationNoCacheTest {

  @Autowired
  private MyService myService;

  @Autowired
  private ApplicationContext applicationContext;

  @Autowired
  private ApplicationContextInitListener initListener;

  @Test
  void test() {
    System.out.println("myService: " + myService);

    System.out.println("applicationContext.hashCode(): " + applicationContext.hashCode());
    System.out.println("context init Count: " + initListener.getContextInitCount());
  }

}
