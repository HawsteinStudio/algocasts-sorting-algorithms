package io.algocasts;

import org.junit.Test;

public class HeapSortTest extends SortTest {

  @Test
  public void test() {
    HeapSort h = new HeapSort();
    test(h::sort);
  }

}
