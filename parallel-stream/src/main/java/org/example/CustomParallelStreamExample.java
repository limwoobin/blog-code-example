package org.example;

import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;

public class CustomParallelStreamExample {
  public static void main(String[] args) throws ExecutionException, InterruptedException {
    List<Integer> list = List.of(1, 2, 3, 4, 5);

    ForkJoinPool customThreadPool = new ForkJoinPool(3);

    customThreadPool.submit(() -> list.stream()
      .parallel()
      .forEach(System.out::println)).get();

    try {
      customThreadPool.submit(() -> list.stream()
        .parallel()
        .forEach(System.out::println)).get();
    } finally {
      customThreadPool.shutdown();
    }

  }
}
