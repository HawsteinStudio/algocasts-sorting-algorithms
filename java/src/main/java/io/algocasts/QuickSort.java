package io.algocasts;

public class QuickSort {

  private void swap(int[] arr, int i, int j) {
    int tmp = arr[i];
    arr[i] = arr[j];
    arr[j] = tmp;
  }

  // [ ... elem < pivot ... | ... elem >= pivot ... | unprocessed elements ]
  //                          i                       j
  private int lomutoPartition(int[] arr, int low, int high) {
    int pivot = arr[high];
    int i = low;
    for (int j = low; j < high; ++j) {
      if (arr[j] < pivot) {
        swap(arr, i, j);
        ++i;
      }
    }
    swap(arr, i, high);
    return i;
  }

  // lomuto partition 的另一种实现，可以把最后的 swap 合并到循环中。
  private int lomutoPartition2(int[] arr, int low, int high) {
    int pivot = arr[high];
    int i = low;
    for (int j = low; j <= high; ++j) {
      if (arr[j] <= pivot) {
        swap(arr, i, j);
        ++i;
      }
    }
    return i-1;
  }

  private void lomutoSort(int[] arr, int low, int high) {
    if (low < high) {
      int k = lomutoPartition(arr, low, high);
      lomutoSort(arr, low, k-1);
      lomutoSort(arr, k+1, high);
    }
  }

  private int hoarePartitionDoWhile(int[] arr, int low, int high) {
    int pivot = arr[low + (high-low)/2];
    int i = low-1, j = high+1;
    while (true) {
      do {
        ++i;
      } while (arr[i] < pivot);
      do {
        --j;
      } while (arr[j] > pivot);
      if (i >= j) return j;
      swap(arr, i, j);
    }
  }

  // [ ... elem <= pivot ... | unprocessed elements | ... elem >= pivot ... ]
  //                         i                      j
  private int hoarePartition(int[] arr, int low, int high) {
    int pivot = arr[low + (high-low)/2];
    int i = low, j = high;
    while (true) {
      while (arr[i] < pivot) ++i;
      while (arr[j] > pivot) --j;
      if (i >= j) return j;
      swap(arr, i++, j--);
    }
  }

  private void hoareSort(int[] arr, int low, int high) {
    if (low < high) {
      int k = hoarePartition(arr, low, high);
      hoareSort(arr, low, k);
      hoareSort(arr, k+1, high);
    }
  }

  // Time: O(n*log(n)), Space: O(n)
  public void lomutoSort(int[] arr) {
    if (arr == null || arr.length == 0) return;
    lomutoSort(arr, 0, arr.length-1);
  }

  // Time: O(n*log(n)), Space: O(n)
  public void hoareSort(int[] arr) {
    if (arr == null || arr.length == 0) return;
    hoareSort(arr, 0, arr.length-1);
  }

}
