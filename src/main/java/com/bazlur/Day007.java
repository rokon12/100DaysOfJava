package com.bazlur;

import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Day007 {

  public static void main(String[] args) {
    var stockExchangeService = new StockExchangeService();
    var scheduledExecutorService
            = Executors.newSingleThreadScheduledExecutor();
    scheduledExecutorService
            .scheduleAtFixedRate(
                    () -> stockExchangeService.getBitcoinValueInCAD()
                            .ifPresent(System.out::println),
                    0, 5, TimeUnit.MINUTES);
  }
}
