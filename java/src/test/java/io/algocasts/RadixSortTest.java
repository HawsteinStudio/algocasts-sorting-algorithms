package io.algocasts;

import org.junit.Test;

import java.util.Arrays;

public class RadixSortTest extends SortTest {

  @Test
  public void test() {
    RadixSort r = new RadixSort();
    test(r::sort32pass);
    test(r::sort16pass);
    test(r::sort8pass);
    test(r::sort4pass);
    test(r::sort2pass);
  }

  @Test
  public void benchmark() {
    final int CNT = Helper.getRandomInt(100, 200);
    final int LENGTH = Helper.getRandomInt(100000, 200000);
    final double ONE_BILLION = 1000000000.0;

    RadixSort r = new RadixSort();
    // warm up
    for (int i = 0; i < 10; ++i) {
      int[] arr = Helper.getRandomIntArray(LENGTH);
      int[] arr2 = arr.clone();
      int[] arr4 = arr.clone();
      int[] arr8 = arr.clone();
      Arrays.sort(arr);
      r.sort2pass(arr2);
      r.sort4pass(arr4);
      r.sort8pass(arr8);
    }

    // bench mark
    long timeQuickSort = 0L;
    long timeRadixSort2Pass = 0L;
    long timeRadixSort4Pass = 0L;
    long timeRadixSort8Pass = 0L;
    long start;
    for (int i = 0; i < CNT; ++i) {
      int[] arr = Helper.getRandomIntArray(LENGTH);
      int[] arrFor2pass = arr.clone();
      int[] arrFor4pass = arr.clone();
      int[] arrFor8pass = arr.clone();
      // measure builtin quick sort
      start = System.nanoTime();
      Arrays.sort(arr);
      timeQuickSort += (System.nanoTime() - start);

      // measure radix sort (2 pass)
      start = System.nanoTime();
      r.sort2pass(arrFor2pass);
      timeRadixSort2Pass += (System.nanoTime() - start);

      // measure radix sort (4 pass)
      start = System.nanoTime();
      r.sort4pass(arrFor4pass);
      timeRadixSort4Pass += (System.nanoTime() - start);

      // measure radix sort (8 pass)
      start = System.nanoTime();
      r.sort8pass(arrFor8pass);
      timeRadixSort8Pass += (System.nanoTime() - start);
    }

    System.out.println("Loop count: " + CNT + ", array length: " + LENGTH);
    System.out.println("Builtin Quick Sort Time(s): " + timeQuickSort/ONE_BILLION);
    System.out.println("Radix Sort(2 pass) Time(s): " + timeRadixSort2Pass/ONE_BILLION);
    System.out.println("Radix Sort(4 pass) Time(s): " + timeRadixSort4Pass/ONE_BILLION);
    System.out.println("Radix Sort(8 pass) Time(s): " + timeRadixSort8Pass/ONE_BILLION);
  }

}
