package com.bazlur;

import java.math.BigInteger;
import java.time.Duration;
import java.time.Instant;
import java.util.HashMap;
import java.util.Map;

public class Day001 {
  static Map<Integer, BigInteger> cache = new HashMap<>(
          Map.of(0, BigInteger.ZERO, 1, BigInteger.ONE)
  );

  public static BigInteger fibonacci(int n) {
    if (!cache.containsKey(n)) {
      var computed = fibonacci(n - 1).add(fibonacci(n - 2));
      cache.put(n, computed);
    }

    return cache.get(n);
  }

  public static void main(String[] args) {
    var now = Instant.now();

    for (int i = 0; i < 100; i++) {
      var fibonacciOf100 = fibonacci(100);
      System.out.println("fibonacciOf100 = " + fibonacciOf100);
    }
    System.out.println("time =" + (Duration.between(Instant.now(), now).getNano() / 100));

  }
}
