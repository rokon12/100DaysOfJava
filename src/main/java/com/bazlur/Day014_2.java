package com.bazlur;

import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Day014_2 {
  public static void main(String[] args) throws ExecutionException, InterruptedException {
    var stockExchangeService = new StockExchangeService();
    var futureCAD = CompletableFuture.supplyAsync(stockExchangeService::getBitcoinValueInCAD);
    var futureUSD = CompletableFuture.supplyAsync(stockExchangeService::getBitcoinValueInUSD);

    var combined = futureCAD.thenCombine(futureUSD, (cad, usd) -> Stream.of(cad, usd)
            .flatMap(Optional::stream)
            .collect(Collectors.joining(", ")));

    System.out.println("combined = " + combined.get());
  }
}
