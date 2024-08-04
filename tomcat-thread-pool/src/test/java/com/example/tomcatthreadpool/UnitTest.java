package com.example.tomcatthreadpool;

import org.junit.jupiter.api.Test;

public class UnitTest {

  @Test
  void test() {
    int val = Integer.MAX_VALUE;
    int val2 = 2147483647;
    boolean result = val == val2;

    System.out.println(val);
    System.out.println(val2);
    System.out.println(result);
  }
}
