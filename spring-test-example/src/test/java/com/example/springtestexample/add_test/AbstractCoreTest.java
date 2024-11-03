package com.example.springtestexample.add_test;

import com.example.springtestexample.service.MyService;
import org.springframework.boot.test.mock.mockito.MockBean;

@CoreTest
public abstract class AbstractCoreTest {

  @MockBean
  private MyService myService;
}
