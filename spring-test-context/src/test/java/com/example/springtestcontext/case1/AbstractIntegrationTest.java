package com.example.springtestcontext.case1;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;

@TestExecutionListeners(value = {
  CustomTestExecutionListener.class,
  DependencyInjectionTestExecutionListener.class
})
@SpringBootTest
public abstract class AbstractIntegrationTest {
}
