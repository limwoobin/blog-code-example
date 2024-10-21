package org.example;

import java.util.stream.LongStream;

public class NumberStreamExample {
  public static void main(String[] args) {


    long startSequential = System.currentTimeMillis();
    long sumSequential = LongStream.rangeClosed(1, 1_000_000)
      .reduce(0L, Long::sum);

    long endSequential = System.currentTimeMillis();
    System.out.println("Sequential sum: " + sumSequential);
    System.out.println("Sequential processing time: " + (endSequential - startSequential) + "ms");

    long startParallel = System.currentTimeMillis();
    long sumParallel = LongStream.rangeClosed(1, 1_000_000)
      .parallel()
      .reduce(0L, Long::sum);

    long endParallel = System.currentTimeMillis();
    System.out.println("Parallel sum: " + sumParallel);
    System.out.println("Parallel processing time: " + (endParallel - startParallel) + "ms");
  }
}
