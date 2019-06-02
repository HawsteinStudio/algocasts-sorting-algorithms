package io.algocasts;

public class InsertionSort {

  // Time: O(n^2), Space: O(1)
  public void sort(int[] arr) {
    if (arr == null || arr.length == 0) return;
    for (int i = 1; i < arr.length; ++i) {
      int cur = arr[i];
      int j = i - 1;
      while (j >= 0 && arr[j] > cur) {
        arr[j+1] = arr[j];
        --j;
      }
      arr[j+1] = cur;
    }
  }

}
