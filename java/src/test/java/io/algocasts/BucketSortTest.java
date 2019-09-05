package io.algocasts;

import org.junit.Assert;
import org.junit.Test;

public class BucketSortTest {

  private final int CNT = Helper.getRandomInt(10, 100);
  private final int LENGTH = Helper.getRandomInt(1000, 2000);

  @Test
  public void test() {
    // Possible bucket size: 5~15
    int bucketSize = Helper.getRandomInt(5, 16);
    BucketSort b = new BucketSort(bucketSize);
    for (int i = 0; i < CNT; ++i) {
      int lowerBound = Helper.getRandomInt(-100, 100);
      int[] arr = Helper.getRandomIntArray(LENGTH, lowerBound, lowerBound+LENGTH);
      int[] sorted = Helper.sortByBuiltinMethod(arr);
      b.sort(arr);
      Assert.assertArrayEquals(sorted, arr);
    }
  }

}
