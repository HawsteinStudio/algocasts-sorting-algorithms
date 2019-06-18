package io.algocasts;

import org.junit.Test;

public class ShellSortTest extends SortTest {

  @Test
  public void test() {
    ShellSort s = new ShellSort();
    test(s::sort);
  }

}
