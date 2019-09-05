package io.algocasts;

import org.junit.Assert;
import org.junit.Test;

public class CountingSortTest {

  private final int CNT = Helper.getRandomInt(10, 100);
  private final int LENGTH = Helper.getRandomInt(1000, 2000);

  @Test
  public void testLeft2RightVersion() {
    CountingSort c = new CountingSort();
    for (int i = 0; i < CNT; ++i) {
      int smallK = Helper.getRandomInt(50, 100);
      int[] arr = Helper.getRandomIntArray(LENGTH, 0, smallK+1);
      int[] sorted = Helper.sortByBuiltinMethod(arr);
      c.sortLeft2Right(arr);
      Assert.assertArrayEquals(sorted, arr);
    }
  }

  @Test
  public void testRight2LeftVersion() {
    CountingSort c = new CountingSort();
    for (int i = 0; i < CNT; ++i) {
      int smallK = Helper.getRandomInt(50, 100);
      int[] arr = Helper.getRandomIntArray(LENGTH, 0, smallK+1);
      int[] sorted = Helper.sortByBuiltinMethod(arr);
      c.sortRight2Left(arr);
      Assert.assertArrayEquals(sorted, arr);
    }
  }

}
