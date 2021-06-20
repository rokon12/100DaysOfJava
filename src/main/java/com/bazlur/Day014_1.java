package com.bazlur;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;

public class Day014_1 {

  public static void main(String[] args) throws URISyntaxException, IOException, InterruptedException, ExecutionException {
    var webPages = List.of("https://bazlur.com/categories/article/",
            "https://bazlur.com/categories/java/",
            "https://bazlur.com/categories/100daysofjava/");

    var futures = webPages.stream()
            .map(Day014_1::downloadWebPage)
            .collect(Collectors.toList());

    var allFutures = CompletableFuture.allOf(
            futures.toArray(new CompletableFuture[0])
    );

    var text = allFutures.thenApply(v -> futures.stream()
            .map(CompletableFuture::join)
            .collect(Collectors.joining(",")))
            .get();
    System.out.println("text = " + text);
  }

  static CompletableFuture<String> downloadWebPage(String pageLink) {
    return CompletableFuture.supplyAsync(() -> {

      return "Hello world";
    });
  }
}
