package io.algocasts;

import org.junit.Test;

public class CocktailSortTest extends SortTest {

  @Test
  public void test() {
    CocktailSort c = new CocktailSort();
    test(c::sort);
  }

  @Test
  public void testEarlyReturnVersion() {
    CocktailSort c = new CocktailSort();
    test(c::sortEarlyReturn);
  }

  @Test
  public void testSkipVersion() {
    CocktailSort c = new CocktailSort();
    test(c::sortSkip);
  }

}
