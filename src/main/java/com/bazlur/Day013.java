package com.bazlur;

import java.io.IOException;
import java.math.BigInteger;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.Duration;
import java.time.Instant;
import java.util.function.Supplier;

public class Day013 {
  public static void main(String[] args) throws IOException {
    //2^82,589,933 − 1
    var theLargestKnownPrime = withElapsedTime(() -> {
      var two = BigInteger.TWO;
      var largest = two.pow(82_589_933);
      return largest.subtract(BigInteger.ONE);
    });

    Files.writeString(Path.of("theLargestKnownPrime.txt"), theLargestKnownPrime.toString());
  }

  private static <T> T withElapsedTime(Supplier<T> supplier) {
    var start = Instant.now();
    var result = supplier.get();
    var duration = Duration.between(start, Instant.now());
    System.out.println("Time elapsed: " + duration.toMillis() + " ms");
    return result;
  }
}