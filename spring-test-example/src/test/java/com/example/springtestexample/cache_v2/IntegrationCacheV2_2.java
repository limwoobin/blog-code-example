package com.example.springtestexample.cache_v2;

import com.example.springtestexample.ApplicationContextInitListener;
import com.example.springtestexample.config.MyService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest
public class IntegrationCacheV2_2 {

  @Autowired
  private MyService myService;

  private MyService mockMyService;
  private MyService spyMyService;

  @Autowired
  private ApplicationContext applicationContext;

  @Autowired
  private ApplicationContextInitListener initListener;

  @BeforeEach
  void setUp() {
    this.mockMyService = Mockito.mock(MyService.class);
    this.spyMyService = Mockito.spy(myService);
  }

  @Test
  void test() {
    System.out.println(spyMyService.getComponentValue(any()));
    when(spyMyService.getComponentValue("zz")).thenReturn("werwerwer");
    System.out.println(spyMyService.getComponentValue("zz"));

    System.out.println("myService: " + myService);
    System.out.println("mockMyService: " + mockMyService);
    System.out.println("spyMyService: " + spyMyService);

    System.out.println("applicationContext.hashCode(): " + applicationContext.hashCode());
    System.out.println("context init Count: " + initListener.getContextInitCount());
  }
}
