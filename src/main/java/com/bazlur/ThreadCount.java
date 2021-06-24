package com.bazlur;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.LockSupport;

public class ThreadCount {
  public static void main(String[] args) {
    AtomicInteger threadCount = new AtomicInteger();
    for (; ; ) {
      Thread thread = new Thread(() -> {
        var count = threadCount.incrementAndGet();
        System.out.println("count = " + count);
        LockSupport.park();
      });
      thread.start();
    }
  }
}
