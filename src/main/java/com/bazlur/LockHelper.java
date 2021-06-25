package com.bazlur;

import java.util.concurrent.locks.Lock;
import java.util.function.Supplier;

public class LockHelper {
  public static void withLock(Lock lock, Runnable runnable) {
    lock.lock();
    try {
      runnable.run();
    } finally {
      lock.unlock();
    }
  }

  public static <T> T withLock(Lock lock, Supplier<T> supplier) {
    lock.lock();
    try {
      return supplier.get();
    } finally {
      lock.unlock();
    }
  }
}
