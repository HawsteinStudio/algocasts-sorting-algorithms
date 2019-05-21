package io.algocasts;

import org.junit.Test;

public class BubbleSortTest extends SortTest {

  @Test
  public void test() {
    BubbleSort b = new BubbleSort();
    test(b::sort);
  }

  @Test
  public void testShortVersion() {
    BubbleSort b = new BubbleSort();
    test(b::sortShort);
  }

  @Test
  public void testEarlyReturnVersion() {
    BubbleSort b = new BubbleSort();
    test(b::sortEarlyReturn);
  }

  @Test
  public void testSkipVersion() {
    BubbleSort b = new BubbleSort();
    test(b::sortSkip);
  }

}
