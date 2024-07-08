package com.example.springasync;

import com.example.springasync.service.AsyncService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class AsyncServiceTest {

  @Autowired
  private AsyncService asyncService;

  @Test
  void test() {
    asyncService.asyncMethod();
  }
}
