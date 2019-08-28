package io.algocasts;

public class CountingSort {

  // indexes 最后存储的是排序后，相同数字在结果数组的开始位置，相同数字会依次向后（右）填充。
  // Time: O(n+k), Space: O(n+k)
  public void sortLeft2Right(int[] arr) {
    if (arr == null || arr.length == 0) return;
    int max = arr[0], min = arr[0];
    for (int num: arr) {
      if (num > max) max = num;
      if (num < min) min = num;
    }

    int k = max - min;
    int[] indexes = new int[k+1];
    for (int num: arr) ++indexes[num-min];

    int start = 0;
    for (int i = 0; i <= k; ++i) {
      int count = indexes[i];
      indexes[i] = start;
      start += count;
    }

    int[] tmp = new int[arr.length];
    for (int num: arr) {
      int idx = indexes[num-min];
      tmp[idx] = num;
      ++indexes[num-min];
    }
    System.arraycopy(tmp, 0, arr, 0, arr.length);
  }

  // indexes 最后存储的是排序后，相同数字在结果数组的结束位置，相同数字会依次向前（左）填充。
  // Time: O(n+k), Space: O(n+k)
  public void sortRight2Left(int[] arr) {
    if (arr == null || arr.length == 0) return;
    int max = arr[0], min = arr[0];
    for (int num: arr) {
      if (num > max) max = num;
      if (num < min) min = num;
    }

    int k = max - min;
    int[] indexes = new int[k+1];
    for (int num: arr) ++indexes[num-min];

    --indexes[0];
    for (int i = 1; i <= k; ++i)
      indexes[i] = indexes[i] + indexes[i-1];

    int[] tmp = new int[arr.length];
    for (int i = arr.length-1; i >= 0; --i) {
      int idx = indexes[arr[i]-min];
      tmp[idx] = arr[i];
      --indexes[arr[i]-min];
    }
    System.arraycopy(tmp, 0, arr, 0, arr.length);
  }

}
