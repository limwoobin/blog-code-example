package org.example;

import java.util.List;
import java.util.stream.LongStream;

public class ParallelStreamExampleV2 {
  public static void main(String[] args) {
    // 1부터 10,000,000까지의 숫자 리스트 생성
    List<Long> numbers = LongStream.rangeClosed(1, 50_000_000).boxed().toList();

    long startSequential = System.currentTimeMillis();
    long sumSequential = numbers.stream()
      .reduce(0L, Long::sum);
//      .mapToLong(n -> n).sum();

//    int sumSequential = numbers.stream()
//      .mapToInt(n -> n * n)  // 각 숫자를 제곱
//      .sum();                // 제곱한 값들의 합
    long endSequential = System.currentTimeMillis();

    System.out.println("Sequential sum: " + sumSequential);
    System.out.println("Sequential processing time: " + (endSequential - startSequential) + "ms");

    long startParallel = System.currentTimeMillis();
    long sumParallel = numbers.parallelStream()
      .reduce(0L, Long::sum);
//      .mapToLong(n -> n).sum();

//    int sumParallel = numbers.parallelStream().
//      .mapToInt(n -> n * n)  // 각 숫자를 병렬로 제곱
//      .sum();                // 제곱한 값들의 합
    long endParallel = System.currentTimeMillis();

    System.out.println("Parallel sum: " + sumParallel);
    System.out.println("Parallel processing time: " + (endParallel - startParallel) + "ms");
  }
}
