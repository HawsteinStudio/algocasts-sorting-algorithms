package io.algocasts;

import org.junit.Test;

public class InsertionSortTest extends SortTest {

  @Test
  public void test() {
    InsertionSort i = new InsertionSort();
    test(i::sort);
  }

}
