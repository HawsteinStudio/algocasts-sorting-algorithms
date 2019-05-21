package io.algocasts;

import org.junit.Assert;

import java.util.function.Consumer;

class SortTest {
  private final int CNT = Helper.getRandomInt(10, 100);
  private final int LENGTH = Helper.getRandomInt(20, 200);

  void test(Consumer<int[]> sort) {
    for (int i = 0; i < CNT; ++i) {
      int[] arr = Helper.getRandomArray(LENGTH);
      int[] sorted = Helper.sortByBuiltinMethod(arr);
      sort.accept(arr);
      Assert.assertArrayEquals(sorted, arr);
    }
  }

}
