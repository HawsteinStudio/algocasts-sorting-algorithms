package io.algocasts;

import org.junit.Assert;
import org.junit.Test;

public class BucketSortDoubleTest {

  private final int CNT = Helper.getRandomInt(10, 100);
  private final int LENGTH = Helper.getRandomInt(1000, 2000);
  private final double DELTA = 0.000001;

  @Test
  public void test() {
    // Possible bucket size: 5~15
    int bucketSize = Helper.getRandomInt(5, 16);
    BucketSortDouble b = new BucketSortDouble(bucketSize);
    for (int i = 0; i < CNT; ++i) {
      double[] arr = Helper.getRandomDoubleArray(LENGTH);
      double[] sorted = Helper.sortByBuiltinMethod(arr);
      b.sort(arr);
      Assert.assertArrayEquals(sorted, arr, DELTA);
    }
  }

}
