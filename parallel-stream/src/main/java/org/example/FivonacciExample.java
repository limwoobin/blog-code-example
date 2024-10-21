package org.example;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class FivonacciExample {
  // 피보나치 수 계산: 매우 비효율적인 재귀적 방식 (시간 소모가 큰 작업)
  public static long fibonacci(int n) {
    if (n <= 1) return n;
    return fibonacci(n - 1) + fibonacci(n - 2);
  }

  public static void main(String[] args) {
    List<Integer> numbers = IntStream.rangeClosed(30, 40).boxed().toList();

    long startSequential = System.currentTimeMillis();
    List<Long> sequentialResult = numbers.stream()
      .map(FivonacciExample::fibonacci)
      .toList();
    long endSequential = System.currentTimeMillis();

    System.out.println("Sequential processing time: " + (endSequential - startSequential) + "ms");

    long startParallel = System.currentTimeMillis();
    List<Long> parallelResult = numbers.parallelStream()
      .map(FivonacciExample::fibonacci)
      .toList();
    long endParallel = System.currentTimeMillis();

    System.out.println("Parallel processing time: " + (endParallel - startParallel) + "ms");
  }
}
