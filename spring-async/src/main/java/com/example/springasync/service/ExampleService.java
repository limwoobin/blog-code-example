package com.example.springasync.service;

import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

@Service
public class ExampleService {
  private final AsyncService asyncService;

  public ExampleService(AsyncService asyncService) {
    this.asyncService = asyncService;
  }

  public void getFuture() throws ExecutionException, InterruptedException {
    Future<String> future = asyncService.asyncReturnFuture();
    System.out.println(future.get());
  }

  public void getCompletableFuture() {
    CompletableFuture<String> completableFuture = asyncService.asyncReturnCompletableFuture();
    System.out.println(completableFuture.join());
  }
}
