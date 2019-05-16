package io.algocasts;

import org.junit.Assert;
import org.junit.Test;

public class BubbleSortTest {

  @Test
  public void test() {
    BubbleSort b = new BubbleSort();
    int cnt = Helper.getRandomInt(10, 100);
    int length = Helper.getRandomInt(20, 200);

    for (int i = 0; i < cnt; ++i) {
      int[] arr = Helper.getRandomArray(length);
      int[] sorted = Helper.sortByBuiltinMethod(arr);
      b.sort(arr);
      Assert.assertArrayEquals(sorted, arr);
    }
  }

  @Test
  public void testShortVersion() {
    BubbleSort b = new BubbleSort();
    int cnt = Helper.getRandomInt(10, 100);
    int length = Helper.getRandomInt(20, 200);

    for (int i = 0; i < cnt; ++i) {
      int[] arr = Helper.getRandomArray(length);
      int[] sorted = Helper.sortByBuiltinMethod(arr);
      b.sortShort(arr);
      Assert.assertArrayEquals(sorted, arr);
    }
  }

  @Test
  public void testEarlyReturnVersion() {
    BubbleSort b = new BubbleSort();
    int cnt = Helper.getRandomInt(10, 100);
    int length = Helper.getRandomInt(20, 200);

    for (int i = 0; i < cnt; ++i) {
      int[] arr = Helper.getRandomArray(length);
      int[] sorted = Helper.sortByBuiltinMethod(arr);
      b.sortEarlyReturn(arr);
      Assert.assertArrayEquals(sorted, arr);
    }
  }

  @Test
  public void testSkipVersion() {
    BubbleSort b = new BubbleSort();
    int cnt = Helper.getRandomInt(10, 100);
    int length = Helper.getRandomInt(20, 200);

    for (int i = 0; i < cnt; ++i) {
      int[] arr = Helper.getRandomArray(length);
      int[] sorted = Helper.sortByBuiltinMethod(arr);
      b.sortSkip(arr);
      Assert.assertArrayEquals(sorted, arr);
    }
  }

}
