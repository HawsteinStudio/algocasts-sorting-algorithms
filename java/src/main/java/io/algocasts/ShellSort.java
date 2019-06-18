package io.algocasts;

public class ShellSort {

  // Time: O(n^2), Space: O(1)
  public void sort(int[] arr) {
    if (arr == null || arr.length == 0) return;
    for (int gap = arr.length>>1; gap > 0; gap >>= 1) {
      for (int i = gap; i < arr.length; ++i) {
        int cur = arr[i];
        int j = i - gap;
        while (j >= 0 && arr[j] > cur) {
          arr[j+gap] = arr[j];
          j -= gap;
        }
        arr[j+gap] = cur;
      }
    }
  }

  // Time: O(n^2), Space: O(1)
  public void insertionSort(int[] arr) {
    if (arr == null || arr.length == 0) return;
    for (int i = 1; i < arr.length; ++i) {
      int cur = arr[i];
      int j = i - 1;
      while (j >= 0 && arr[j] > cur) {
        arr[j + 1] = arr[j];
        j -= 1;
      }
      arr[j + 1] = cur;
    }
  }

}
