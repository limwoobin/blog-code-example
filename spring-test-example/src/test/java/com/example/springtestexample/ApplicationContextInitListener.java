package com.example.springtestexample;

import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.util.concurrent.atomic.AtomicInteger;

@Component
public class ApplicationContextInitListener implements ApplicationListener<ContextRefreshedEvent> {
  private static final AtomicInteger contextInitCount = new AtomicInteger(0);

  @Override
  public void onApplicationEvent(ContextRefreshedEvent event) {
    contextInitCount.incrementAndGet();
  }

  public Integer getContextInitCount() {
    return contextInitCount.get();
  }
}
