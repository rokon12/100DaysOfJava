package com.bazlur;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Stream;

public class Day012 {

  public static void main(String[] args) {
    var ingredients = List.of("Tim Horton");
    var coffeeCup = new CoffeeCup(ingredients);

    var coffee = getCoffeeWithExtra(coffeeCup,
            Coffee::withDarkCookieCrumb,
            Coffee::withSaltedCaramelFudge,
            Coffee::withSweetenedMilk,
            Coffee::withVanillaAlmondExtract);

    System.out.println("Coffee with " + String.join(", ", coffee.ingredients()));
  }

  @SafeVarargs
  static Coffee getCoffeeWithExtra(Coffee coffee, Function<Coffee, Coffee>... ingredients) {

    var reduce1 = Stream.of(ingredients)
            .reduce(kopi -> kopi, (func1, func2) -> func1.andThen(func2));


    var reduce = Stream.of(ingredients)
            .reduce(Function.identity(), Function::andThen);
    return reduce.apply(coffee);
  }

  @FunctionalInterface
  interface Coffee {
    static Coffee withSaltedCaramelFudge(Coffee coffee) {
      return () -> coffee.add("Salted Caramel Fudge");
    }

    default List<String> add(String item) {
      return new ArrayList<>(ingredients()) {{
        add(item);
      }};
    }

    List<String> ingredients();

    static Coffee withSweetenedMilk(Coffee coffee) {
      return () -> coffee.add("Sweetened Milk");
    }

    static Coffee withDarkCookieCrumb(Coffee coffee) {
      return () -> coffee.add("Dark Cookie Crumb");
    }

    static Coffee withVanillaAlmondExtract(Coffee coffee) {
      return () -> coffee.add("Vanilla/Almond Extract");
    }
  }

  record CoffeeCup(List<String> initialIngredient) implements Coffee {
    @Override
    public List<String> ingredients() {
      return initialIngredient;
    }
  }
}
