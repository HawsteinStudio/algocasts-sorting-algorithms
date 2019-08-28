package io.algocasts;

import org.junit.Assert;
import org.junit.Test;

public class CountingSortFixedKTest {

  private final int k = 100;
  private final int CNT = Helper.getRandomInt(10, 100);
  private final int LENGTH = Helper.getRandomInt(1000, 2000);

  @Test
  public void testLeft2RightVersion() {
    CountingSortFixedK c = new CountingSortFixedK(k);
    for (int i = 0; i < CNT; ++i) {
      int[] arr = Helper.getRandomArray(LENGTH, 0, k+1);
      int[] sorted = Helper.sortByBuiltinMethod(arr);
      c.sortLeft2Right(arr);
      Assert.assertArrayEquals(sorted, arr);
    }
  }

  @Test
  public void testRight2LeftVersion() {
    CountingSortFixedK c = new CountingSortFixedK(k);
    for (int i = 0; i < CNT; ++i) {
      int[] arr = Helper.getRandomArray(LENGTH, 0, k+1);
      int[] sorted = Helper.sortByBuiltinMethod(arr);
      c.sortRight2Left(arr);
      Assert.assertArrayEquals(sorted, arr);
    }
  }

}
