package com.bazlur;

import java.util.List;
import java.util.Optional;

public class Day009 {
  static List<Book> books;

  static {
    books = List.of(new Book("Java Programming", 2017));
  }

  public static void main(String[] args) {

    var bookOpt = findBookByName("Java Programming");
    if (bookOpt.isPresent()) {
      var book = bookOpt.get();
      var releasedYear = book.releasedYear();
      System.out.println("Java Programming was published in " + releasedYear);
    } else {
      System.out.println("Book was not found");
    }

    //or

    findBookByName("Java Programming")
            .map(Book::releasedYear)
            .ifPresentOrElse((releasedYear)
                            -> System.out.println("Java Programming was published in " + releasedYear),
                    () -> System.out.println("Book was not found"));
  }

  public static Optional<Book> findBookByName(String name) {

    return books.stream()
            .filter(book -> book.title().equalsIgnoreCase(name))
            .findAny();
  }

  record Book(String title, int releasedYear) {
  }
}
