package com.bazlur;

import java.util.Arrays;
import java.util.NoSuchElementException;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ConcurrentStack2<T> {
  private final Lock lock = new ReentrantLock();
  private T[] elements;
  private int size = -1;

  public ConcurrentStack2() {
    this(10);
  }

  public ConcurrentStack2(int initialCapacity) {
    this.elements = (T[]) new Object[initialCapacity];
  }

  public void push(T value) {
    LockHelper.withLock(lock, () -> {
      growIfNeeded();
      elements[size] = value;
    });
  }

  public T pop() {
    return LockHelper.withLock(lock, () -> {
      if (size == -1) {
        throw new NoSuchElementException();
      }
      trimToSizeIfNeeded();

      var element = elements[size];
      elements[size] = null;
      size--;
      return element;
    });
  }

  private void growIfNeeded() {
    if (++size == elements.length) {
      grow();
    }
  }

  private void grow() {
    int newCapacity = elements.length * 2;
    elements = Arrays.copyOf(elements, newCapacity);
  }

  private void trimToSizeIfNeeded() {
    if (size < elements.length) {
      elements = Arrays.copyOf(elements, size + 1);
    }
  }
}
