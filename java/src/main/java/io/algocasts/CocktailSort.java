package io.algocasts;

public class CocktailSort {

  private void swap(int[] arr, int i, int j) {
    int tmp = arr[i];
    arr[i] = arr[j];
    arr[j] = tmp;
  }

  // Time: O(n^2), Space: O(1)
  public void sort(int[] arr) {
    if (arr == null || arr.length == 0) return;
    int left = 0, right = arr.length - 1;

    while (left < right) {
      for (int i = left; i < right; ++i)
        if (arr[i] > arr[i+1])
          swap(arr, i, i+1);
      --right;

      for (int i = right; i > left; --i)
        if (arr[i-1] > arr[i])
          swap(arr, i-1, i);
      ++left;
    }
  }

  // Time: O(n^2), Space: O(1)
  public void sortEarlyReturn(int[] arr) {
    if (arr == null || arr.length == 0) return;
    int left = 0, right = arr.length - 1;
    boolean swapped;

    while (left < right) {
      swapped = false;

      for (int i = left; i < right; ++i) {
        if (arr[i] > arr[i+1]) {
          swap(arr, i, i+1);
          swapped = true;
        }
      }
      --right;

      for (int i = right; i > left; --i) {
        if (arr[i-1] > arr[i]) {
          swap(arr, i-1, i);
          swapped = true;
        }
      }
      ++left;

      if (!swapped) return;
    }
  }

  // Time: O(n^2), Space: O(1)
  public void sortSkip(int[] arr) {
    if (arr == null || arr.length == 0) return;
    int left = 0, right = arr.length - 1;
    int newLeft, newRight;

    while (left < right) {
      newRight = left;
      for (int i = left; i < right; ++i) {
        if (arr[i] > arr[i+1]) {
          swap(arr, i, i+1);
          newRight = i;
        }
      }
      right = newRight;

      newLeft = right;
      for (int i = right; i > left; --i) {
        if (arr[i-1] > arr[i]) {
          swap(arr, i-1, i);
          newLeft = i;
        }
      }
      left = newLeft;
    }
  }

}
