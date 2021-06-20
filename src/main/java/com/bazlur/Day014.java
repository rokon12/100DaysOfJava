package com.bazlur;


import java.util.List;
import java.util.Random;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveAction;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Day014 {

  public static void main(String[] args) {
    var random = new Random();

    var list = IntStream.range(0, 1000)
            .mapMulti((value, ic) -> {
              var i = random.nextInt(1_000);
              ic.accept(i);
            }).boxed()
            .collect(Collectors.toList());
    System.out.println("list = " + list);

    var action = new QuickSortAction<>(list);
    var pool = ForkJoinPool.commonPool();
    pool.invoke(action);
    System.out.println(list);
  }

  static class QuickSortAction<T extends Comparable<T>> extends RecursiveAction {
    private final List<T> data;
    private final int left;
    private final int right;

    public QuickSortAction(List<T> data, int left, int right) {
      this.data = data;
      this.left = left;
      this.right = right;
    }

    public QuickSortAction(List<T> data) {
      this.data = data;
      this.left = 0;
      this.right = data.size() - 1;
    }

    @Override
    protected void compute() {
      if (left >= right) {
        return;
      }
      var pivotIndex = left + ((right - left) / 2);
      pivotIndex = partition(pivotIndex);
      var leftTask = new QuickSortAction<T>(data, left, pivotIndex - 1);
      var rightTask = new QuickSortAction<T>(data, pivotIndex + 1, right);
      leftTask.fork();
      rightTask.compute();
      leftTask.join();
    }

    private int partition(int pivotIndex) {
      var pivotValue = data.get(pivotIndex);
      swap(pivotIndex, right);
      int storeIndex = left;
      for (int i = left; i < right; i++) {
        if (data.get(i).compareTo(pivotValue) < 0) {
          swap(i, storeIndex);
          storeIndex++;
        }
      }
      swap(storeIndex, right);
      return storeIndex;
    }

    private void swap(int pivotIndex, int right) {
      if (pivotIndex != right) {
        T pivotValue = data.get(pivotIndex);
        data.set(pivotIndex, data.get(right));
        data.set(right, pivotValue);
      }
    }
  }
}
