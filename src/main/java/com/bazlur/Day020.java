package com.bazlur;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import static com.bazlur.LockHelper.withLock;

public class Day020 {
  public static void main(String[] args) throws InterruptedException {
    var counter = new ConcurrentCounter();
    var thread1 = new Thread(() -> Times.times(10_000, counter::increment));
    var thread2 = new Thread(() -> Times.times(15_000, counter::increment));
    var thread3 = new Thread(() -> Times.times(20_000, counter::increment));

    thread1.start();
    thread2.start();
    thread3.start();

    thread1.join();
    thread2.join();
    thread3.join();

    var count = counter.get();
    System.out.println("count = " + count);
  }
}


class ConcurrentCounter {
  private final Lock lock = new ReentrantLock();
  private long count;

  public long increment() {
    return withLock(lock, () -> ++count);
  }

  public long get() {
    return withLock(lock, () -> count);
  }
}

class Times {
  static void times(int until, Runnable runnable) {
    for (int i = 0; i < until; i++) {
      runnable.run();
    }
  }
}
