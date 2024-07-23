package com.example.redistrylock;

import org.junit.jupiter.api.Test;

import java.util.concurrent.TimeUnit;

public class UnitTest {

  @Test
  void test() {
    TimeUnit unit = TimeUnit.SECONDS;
    long time = unit.toMillis(5);
    long current = System.currentTimeMillis();

    time -= System.currentTimeMillis() - current;

    System.out.println(time);
  }

  @Test
  void test2() {
    int a = 5;

    a -= 2 - 1;
    System.out.println(a);
  }
}
