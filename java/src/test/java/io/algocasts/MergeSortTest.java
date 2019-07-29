package io.algocasts;

import org.junit.Test;

public class MergeSortTest extends SortTest {

  @Test
  public void test() {
    MergeSort m = new MergeSort();
    test(m::sortRecursive);
    test(m::sortIterative);
  }

}
