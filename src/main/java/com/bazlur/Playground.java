package com.bazlur;

import java.util.Arrays;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Playground {
  public static void main(String[] args) {
//    Function<Integer, Integer> doubleIt = a -> a * 2;
//
//
//    var doubled = transform(4, doubleIt);
//    System.out.println("doubled = " + doubled);
//
//
//    Function<Integer, Integer> squareIt = a -> a * a;
//
//    Function<Integer, Integer> cubeIt = a -> a * a * a;
//    Function<Integer, Integer> incrementByOne = a -> a + 1;
//
//    var cubedOf5 = cubeIt.apply(5);
//    var cubedAndIncremented = incrementByOne.apply(cubedOf5);
//
//    transform(5, cubeIt.andThen(incrementByOne));
//
//
//    var squared = transform(4, squareIt);
//    System.out.println("square of 4 = " + squared);
//    var cubed = transform(5, cubeIt);
//    System.out.println("cube of 5 = " + cubed);

    var hash = "";
    hash.hashCode();


    int[] ints = {1, 2, 3, 4, 5, 6};
    var listOfInts = Arrays.stream(ints)
            .boxed()
            .collect(Collectors.toList());
  }

  static Integer transform(Integer value, Function<Integer, Integer> func) {
    var applied = func.apply(value);
    return applied;
  }

  class User {
    private String name;

    public User(String name) {
      this.name = name;
    }

    @Override
    public boolean equals(Object o) {
      if (this == o) return true;
      if (!(o instanceof User)) return false;
      User user = (User) o;
      return Objects.equals(name, user.name);
    }

    @Override
    public int hashCode() {
      return Objects.hash(name);
    }
  }
}

