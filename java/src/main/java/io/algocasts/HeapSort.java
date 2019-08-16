package io.algocasts;

public class HeapSort {

  private void swap(int[] arr, int i, int j) {
    int tmp = arr[i];
    arr[i] = arr[j];
    arr[j] = tmp;
  }

  // Time: O(log(n))
  private void siftDown(int[] arr, int i, int end) {
    int parent = i, child = 2 * parent + 1;
    while (child <= end) {
      if (child+1 <= end && arr[child+1] > arr[child]) ++child;
      if (arr[parent] >= arr[child]) break;
      swap(arr, parent, child);
      parent = child;
      child = 2 * parent + 1;
    }
  }

  // i 从 end/2 开始即可，因为在二叉堆中，更大的 i 是没有子节点的，没必要做 siftDown
  // Time: O(n)
  // Reference:
  // * https://www.geeksforgeeks.org/time-complexity-of-building-a-heap/
  // * https://www2.cs.sfu.ca/CourseCentral/307/petra/2009/SLN_2.pdf
  private void buildMaxHeap(int[] arr, int end) {
    for (int i = end/2; i >= 0; --i) {
      siftDown(arr, i, end);
    }
  }

  // Time: O(n*log(n)), Space: O(1)
  public void sort(int[] arr) {
    if (arr == null || arr.length == 0) return;
    buildMaxHeap(arr, arr.length - 1);
    for (int end = arr.length - 1; end > 0; --end) {
      swap(arr, 0, end);
      siftDown(arr, 0, end - 1);
    }
  }

}
