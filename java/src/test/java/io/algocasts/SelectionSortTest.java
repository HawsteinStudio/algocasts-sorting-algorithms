package io.algocasts;

import org.junit.Test;

public class SelectionSortTest extends SortTest {

  @Test
  public void test() {
    SelectionSort s = new SelectionSort();
    test(s::sort);
  }

  @Test
  public void testFromBackVersion() {
    SelectionSort s = new SelectionSort();
    test(s::sortFromEnd);
  }

}
