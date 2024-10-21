package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.LongStream;

public class ArrayParallelStreamExample {
  static List<Long> arrayNumbers = new ArrayList<>();

  static {
    LongStream.rangeClosed(1, 50_000_000).forEach(it -> {
      arrayNumbers.add(it);
    });
  }

  public static void main(String[] args) {
    long startSequential = System.currentTimeMillis();
    long sumSequential = arrayNumbers.stream()
      .reduce(0L, Long::sum);

    long endSequential = System.currentTimeMillis();
    System.out.println("Sequential sum: " + sumSequential);
    System.out.println("Sequential processing time: " + (endSequential - startSequential) + "ms");

    long startParallel = System.currentTimeMillis();
    long sumParallel = arrayNumbers.stream()
      .parallel()
      .reduce(0L, Long::sum);

    long endParallel = System.currentTimeMillis();
    System.out.println("Parallel sum: " + sumParallel);
    System.out.println("Parallel processing time: " + (endParallel - startParallel) + "ms");
  }
}
