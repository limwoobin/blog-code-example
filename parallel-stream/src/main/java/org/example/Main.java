package org.example;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ForkJoinPool;
import java.util.stream.IntStream;

public class Main {
  public static void main(String[] args) {
    Set<Integer> sets = new HashSet<>();
    sets.add(1);
    sets.add(2);
    sets.add(3);

    System.out.println(sets.size());
  }
}
