package com.example.springtestexample.cache;

import com.example.springtestexample.ApplicationContextInitListener;
import com.example.springtestexample.IntegrationTest;
import com.example.springtestexample.service.MyService;
import org.junit.jupiter.api.Test;
import org.mockito.internal.util.MockUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

import static org.assertj.core.api.Assertions.assertThat;

@IntegrationTest
public class IntegrationCacheTestV4 {

  @Autowired
  private MyService spyMyService;

  @Autowired
  private ApplicationContext applicationContext;

  @Autowired
  private ApplicationContextInitListener initListener;

  @Test
  void test() {
    System.out.println("spyMyService: " + spyMyService);

    System.out.println("applicationContext.hashCode(): " + applicationContext.hashCode());
    System.out.println("context init Count: " + initListener.getContextInitCount());

    assertThat(MockUtil.isSpy(spyMyService)).isTrue();
  }

}
