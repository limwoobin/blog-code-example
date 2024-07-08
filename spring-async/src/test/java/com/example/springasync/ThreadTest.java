package com.example.springasync;

import com.example.springasync.service.AsyncService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class ThreadTest {

  @Autowired
  private AsyncService asyncService;

  @Test
  void test() {
    for (int i = 0; i < 500; i++) {
      asyncService.asyncMethod();
    }

    try {
      Thread.sleep(30000);
    } catch (InterruptedException e) {
      throw new RuntimeException(e);
    }
  }
}
