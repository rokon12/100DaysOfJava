package com.bazlur;

import java.util.List;
import java.util.Optional;

public class Day009 {
  static List<Book> books;

  static {
    books = List.of(new Book("Java Programming", 2017));
  }

  public static void main(String[] args) {
    findBookByAuthorName("Java Programming")
            .map(Book::releasedYear)
            .ifPresentOrElse((releasedYear)
                            -> System.out.println("Java Programming was published in " + releasedYear),
                    () -> System.out.println("Book was not found"));
  }

  public static Optional<Book> findBookByAuthorName(String name) {

    return books.stream()
            .filter(book -> book.title().equalsIgnoreCase(name))
            .findAny();
  }

  record Book(String title, int releasedYear) {
  }
}
