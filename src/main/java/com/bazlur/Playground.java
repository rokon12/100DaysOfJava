package com.bazlur;

import java.util.HashMap;
import java.util.Map;

public class Playground {
  public static void main(String[] args) {
    Map<String, String> cache = new HashMap<>();
    cache.computeIfAbsent("dep2", ignored -> "dep2_value");
    System.out.println(cache.get("dep1"));
    System.out.println(cache.get("dep2"));
  }
}

