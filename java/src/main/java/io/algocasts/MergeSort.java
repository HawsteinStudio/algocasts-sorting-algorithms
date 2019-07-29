package io.algocasts;

public class MergeSort {

  // sorted sub-array 1: arr[low ... mid]
  // sorted sub-array 2: arr[mid+1 ... high]
  private void merge(int[] arr, int low, int mid, int high, int[] tmp) {
    int i = low, j = mid + 1, k = 0;
    while (i <= mid && j <= high) {
      if (arr[i] <= arr[j]) tmp[k++] = arr[i++];
      else tmp[k++] = arr[j++];
    }
    while (i <= mid) tmp[k++] = arr[i++];
    while (j <= high) tmp[k++] = arr[j++];
    System.arraycopy(tmp, 0, arr, low, k);
  }

  private void mergeSort(int[] arr, int low, int high, int[] tmp) {
    if (low < high) {
      int mid = low + (high - low) / 2;
      mergeSort(arr, low, mid, tmp);
      mergeSort(arr, mid + 1, high, tmp);
      merge(arr, low, mid, high, tmp);
    }
  }

  // Time: O(n*log(n)), Space: O(n)
  public void sortRecursive(int[] arr) {
    if (arr == null || arr.length == 0) return;
    int[] tmp = new int[arr.length];
    mergeSort(arr, 0, arr.length - 1, tmp);
  }

  // Time: O(n*log(n)), Space: O(n)
  public void sortIterative(int[] arr) {
    if (arr == null || arr.length == 0) return;
    int n = arr.length;
    int[] tmp = new int[n];
    for (int len = 1; len < n; len = 2*len) {
      for (int low = 0; low < n; low += 2*len) {
        int mid = Math.min(low+len-1, n-1);
        int high = Math.min(low+2*len-1, n-1);
        merge(arr, low, mid, high, tmp);
      }
    }
  }

}
