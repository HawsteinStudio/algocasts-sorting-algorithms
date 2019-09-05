package io.algocasts;

import java.util.ArrayList;
import java.util.List;

public class BucketSortDouble {

  private void insertionSort(List<Double> arr) {
    if (arr == null || arr.size() == 0) return;
    for (int i = 1; i < arr.size(); ++i) {
      double cur = arr.get(i);
      int j = i - 1;
      while (j >= 0 && arr.get(j) > cur) {
        arr.set(j+1, arr.get(j));
        --j;
      }
      arr.set(j+1, cur);
    }
  }

  // 每个桶的大小，由于桶内使用插入排序，因此桶的大小使用一个较小值会比较高效。
  //
  // 一般来说，当处理的数组大小在 5-15 时，使用插入排序往往会比快排或归并更高效。
  // 因此在桶排序中，我们尽量让单个桶内的元素个数是在 5-15 个之间，这样可以用插入排序高效地完成桶内排序。
  // 参考链接：https://algs4.cs.princeton.edu/23quicksort/
  // 参考段落：
  // Cutoff to insertion sort. As with mergesort,
  // it pays to switch to insertion sort for tiny arrays.
  // The optimum value of the cutoff is system-dependent,
  // but any value between 5 and 15 is likely to work well in most situations.
  private int bucketSize;

  public BucketSortDouble(int bucketSize) {
    this.bucketSize = bucketSize;
  }

  // for all x in arr, we have 0 <= x < 1
  public void sort(double[] arr) {
    if (arr == null || arr.length == 0) return;

    int bucketCount = arr.length / bucketSize;
    List<List<Double>> buckets = new ArrayList<>(bucketCount);
    for (int i = 0; i < bucketCount; ++i)
      buckets.add(new ArrayList<>());

    for (double num: arr)
      buckets.get((int)(num * bucketCount)).add(num);

    int idx = 0;
    for (List<Double> bucket: buckets) {
      insertionSort(bucket);
      for (double num: bucket)
        arr[idx++] = num;
    }
  }

}
