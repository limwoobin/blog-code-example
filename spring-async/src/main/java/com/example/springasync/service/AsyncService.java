package com.example.springasync.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;

@Slf4j
@Service
public class AsyncService {

  @Async
  public void asyncMethod() {
    try {
      Thread.sleep(1);
      log.info("thread name: {}", Thread.currentThread().getName());
    } catch (InterruptedException e) {
      throw new RuntimeException(e);
    }
  }

  @Async
  public Future<String> asyncReturnFuture() {
    String threadName = Thread.currentThread().getName();
    System.out.println("Execute Async Method Return Future: " + threadName);
    return CompletableFuture.completedFuture(threadName);
  }

  @Async
  public CompletableFuture<String> asyncReturnCompletableFuture() {
    String threadName = Thread.currentThread().getName();
    System.out.println("Execute Async Method Return CompletableFuture: " + threadName);
    return CompletableFuture.completedFuture(threadName);
  }

}
