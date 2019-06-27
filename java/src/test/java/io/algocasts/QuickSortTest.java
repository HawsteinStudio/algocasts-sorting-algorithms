package io.algocasts;

import org.junit.Test;

public class QuickSortTest extends SortTest {

  @Test
  public void test() {
    QuickSort q = new QuickSort();
    test(q::lomutoSort);
    test(q::hoareSort);
  }

}
