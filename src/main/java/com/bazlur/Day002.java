package com.bazlur;

import java.math.BigInteger;
import java.util.Map;
import java.util.concurrent.ConcurrentSkipListMap;

public class Day002 {
  static Map<Integer, BigInteger> cache = new ConcurrentSkipListMap<>(
          Map.of(0, BigInteger.ZERO, 1, BigInteger.ONE)
  );

  public static BigInteger fibonacci(int n) {
    return cache.computeIfAbsent(n,
            key -> fibonacci(key - 1).add(fibonacci(key - 2)));
  }

  public static void main(String[] args) {
    var fibonacciOf100 = fibonacci(100);
    System.out.println("fibonacciOf100 = " + fibonacciOf100);
  }
}
