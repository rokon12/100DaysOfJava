package com.bazlur;

import java.util.function.Function;

public class Playground {
  public static void main(String[] args) {
    Function<Integer, Integer> doubleIt = a -> a * 2;


    var doubled = transform(4, doubleIt);
    System.out.println("doubled = " + doubled);


    Function<Integer, Integer> squareIt = a -> a * a;

    Function<Integer, Integer> cubeIt = a -> a * a * a;
    Function<Integer, Integer> incrementByOne = a -> a + 1;

    var cubedOf5 = cubeIt.apply(5);
    var cubedAndIncremented = incrementByOne.apply(cubedOf5);

    transform(5, cubeIt.andThen(incrementByOne));


    var squared = transform(4, squareIt);
    System.out.println("square of 4 = " + squared);
    var cubed = transform(5, cubeIt);
    System.out.println("cube of 5 = " + cubed);


  }

  static Integer transform(Integer value, Function<Integer, Integer> func) {
    var applied = func.apply(value);
    return applied;
  }
}

