package io.algocasts;

import org.junit.Assert;

import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;

class SortTest {
  private final int CNT = Helper.getRandomInt(10, 100);
  private final int LENGTH = Helper.getRandomInt(1, 200);

  // some special cases
  private final List<int[]> specialArrays = Arrays.asList(
    null,            // 空
    new int[]{0},    // 一个元素
    new int[]{0,1},  // 两个元素，递增
    new int[]{1,0},  // 两个元素，递减
    new int[]{1,1},  // 两个元素，相同
    new int[]{0,1,2},  // 三个元素，递增
    new int[]{2,1,0},  // 三个元素，递减
    new int[]{1,1,1},  // 三个元素，相同
    Helper.getSameElementArray(LENGTH, 42),      // LENGTH 个元素，相同
    Helper.getSortedArray(LENGTH),               // LENGTH 个元素，递增
    Helper.getReverseSortedArray(LENGTH),        // LENGTH 个元素，递减
    Helper.getSameElementArray(2*LENGTH, 42),    // 2*LENGTH 个元素(偶数个元素)，相同
    Helper.getSortedArray(2*LENGTH),             // 2*LENGTH 个元素(偶数个元素)，递增
    Helper.getReverseSortedArray(2*LENGTH),      // 2*LENGTH 个元素(偶数个元素)，递减
    Helper.getSameElementArray(2*LENGTH+1, 42),  // 2*LENGTH+1 个元素(奇数个元素)，相同
    Helper.getSortedArray(2*LENGTH+1),           // 2*LENGTH+1 个元素(奇数个元素)，递增
    Helper.getReverseSortedArray(2*LENGTH+1)     // 2*LENGTH+1 个元素(奇数个元素)，递减
  );

  void test(Consumer<int[]> sort) {
    // test special cases
    testSpecialCases(sort);
    // test random cases
    for (int i = 0; i < CNT; ++i) {
      int[] arr = Helper.getRandomArray(LENGTH);
      int[] sorted = Helper.sortByBuiltinMethod(arr);
      sort.accept(arr);
      Assert.assertArrayEquals(sorted, arr);
    }
  }

  private void testSpecialCases(Consumer<int[]> sort) {
    for (int[] array: specialArrays) {
      int[] arr = array == null ? null : array.clone();
      int[] sorted = Helper.sortByBuiltinMethod(arr);
      sort.accept(arr);
      Assert.assertArrayEquals(sorted, arr);
    }
  }

}
