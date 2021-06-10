package com.bazlur;

import java.util.List;
import java.util.stream.Collectors;

public class Day003 {
  public static void main(String[] args) {
    var books = List.of(
            new Book("Book1", 2021, "0-4720-1191-X"),
            new Book("Book2", 2021, "0-1212-6198-0"),
            new Book("Book3", 2020, "0-3052-7666-2"),
            new Book("Book4", 2020, "0-3141-7058-8")
    );
    var bookCountByReleaseYear = books.stream()
            .collect(Collectors.groupingBy(Book::releaseYear, Collectors.counting()));
    System.out.println("bookCountByReleaseYear = " + bookCountByReleaseYear);
  }
}

record Book(String name, int releaseYear, String isbn) {
}
