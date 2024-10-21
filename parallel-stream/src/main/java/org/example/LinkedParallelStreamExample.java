package org.example;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.LongStream;

public class LinkedParallelStreamExample {
  static List<Long> linkedNumbers = new LinkedList<>();

  static {
    LongStream.rangeClosed(1, 1_000_000).forEach(it -> {
      linkedNumbers.add(it);
    });
  }

  public static void main(String[] args) {
    long startSequential = System.currentTimeMillis();
    long sumSequential = linkedNumbers.stream()
      .reduce(0L, Long::sum);

    long endSequential = System.currentTimeMillis();
    System.out.println("Sequential sum: " + sumSequential);
    System.out.println("Sequential processing time: " + (endSequential - startSequential) + "ms");

    long startParallel = System.currentTimeMillis();
    long sumParallel = linkedNumbers.parallelStream()
      .reduce(0L, Long::sum);

    long endParallel = System.currentTimeMillis();
    System.out.println("Parallel sum: " + sumParallel);
    System.out.println("Parallel processing time: " + (endParallel - startParallel) + "ms");
  }
}
