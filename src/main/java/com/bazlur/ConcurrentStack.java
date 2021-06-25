package com.bazlur;

import java.util.Arrays;
import java.util.NoSuchElementException;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ConcurrentStack<T> {
  private final Lock lock = new ReentrantLock();
  private T[] elements;
  private int size = -1;

  public ConcurrentStack() {
    this(10);
  }

  public ConcurrentStack(int initialCapacity) {
    this.elements = (T[]) new Object[initialCapacity];
  }

  public void push(T value) {
    lock.lock();
    try {
      growIfNeeded();
      elements[size] = value;
    } finally {
      lock.unlock();
    }
  }

  public T pop() {
    lock.lock();
    try {
      if (size == -1) {
        throw new NoSuchElementException();
      }
      trimToSizeIfNeeded();

      var element = elements[size];
      elements[size] = null;
      size--;
      return element;
    } finally {
      lock.unlock();
    }
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
