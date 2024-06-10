package com.example.springeventexample.domain;

import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Component
public class UserEventListener {

  @Async
  @EventListener
  public void listen(UserEvent event) {
    try {
      Thread.sleep(500);
    } catch (InterruptedException e) {
      throw new RuntimeException(e);
    }

    System.out.println("event: " + event.getName());
  }
}
