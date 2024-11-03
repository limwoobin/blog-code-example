package com.example.springtestexample.add_test;

import com.example.springtestexample.MyServiceTestConfig;
import com.example.springtestexample.service.MyService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Import;

@CoreTest
@Import(MyServiceTestConfig.class)
public class CoreScenarioTest {

  @Autowired
  private MyService myService;

  @Autowired
  @Qualifier("mockMyService")
  private MyService mockMyService;

  @Autowired
  @Qualifier("spyMyService")
  private MyService spyMyService;

  @Test
  void test2() {
    System.out.println(myService);
    System.out.println(mockMyService);
    System.out.println(spyMyService);

    System.out.println(myService.get());
    System.out.println(mockMyService.get());
    System.out.println(spyMyService.get());
  }

  @Test
  void test3() {
    System.out.println("CoreScenarioTest");
  }
}
