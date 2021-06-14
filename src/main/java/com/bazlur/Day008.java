package com.bazlur;

import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Day008 {
  public static void main(String[] args) {
    var executorService = Executors.newSingleThreadExecutor();
    executorService.submit((Runnable) () -> {
      while (true) {
        doingAStupendousJob();
      }
    });

    Runtime.getRuntime().addShutdownHook(new Thread(() -> {
      try {
        executorService.shutdown();
        if (executorService.awaitTermination(100, TimeUnit.MILLISECONDS)) {
          System.out.println("Still waiting 100ms...");
          executorService.shutdownNow();
        }
        System.out.println("System exited gracefully");
      } catch (InterruptedException e) {
        executorService.shutdownNow();
      }
    }));
  }

  private static void doingAStupendousJob() {
    try {
      Thread.sleep(100);
    } catch (InterruptedException e) {
      Thread.currentThread().interrupt();
    }
  }
}
