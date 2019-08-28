package io.algocasts;

public class CountingSortFixedK {

  private int k;

  // 对于数组中元素 x，有 0 <= x <= k, k 是一个较小的数字。
  public CountingSortFixedK(int k) {
    this.k = k;
  }

  // indexes 最后存储的是排序后，相同数字在结果数组的开始位置，相同数字会依次向后（右）填充。
  // Time: O(n+k), Space: O(n+k)
  public void sortLeft2Right(int[] arr) {
    if (arr == null || arr.length == 0) return;
    int[] indexes = new int[k+1];
    for (int num: arr) ++indexes[num];

    int start = 0;
    for (int i = 0; i <= k; ++i) {
      int count = indexes[i];
      indexes[i] = start;
      start += count;
    }

    int[] tmp = new int[arr.length];
    for (int num: arr) {
      int idx = indexes[num];
      tmp[idx] = num;
      ++indexes[num];
    }
    System.arraycopy(tmp, 0, arr, 0, arr.length);
  }

  // indexes 最后存储的是排序后，相同数字在结果数组的结束位置，相同数字会依次向前（左）填充。
  // Time: O(n+k), Space: O(n+k)
  public void sortRight2Left(int[] arr) {
    if (arr == null || arr.length == 0) return;
    int[] indexes = new int[k+1];
    for (int num: arr) ++indexes[num];

    --indexes[0];
    for (int i = 1; i <= k; ++i)
      indexes[i] = indexes[i] + indexes[i-1];

    int[] tmp = new int[arr.length];
    for (int i = arr.length-1; i >= 0; --i) {
      int idx = indexes[arr[i]];
      tmp[idx] = arr[i];
      --indexes[arr[i]];
    }
    System.arraycopy(tmp, 0, arr, 0, arr.length);
  }

}
