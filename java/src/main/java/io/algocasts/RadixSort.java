package io.algocasts;

import java.util.Arrays;

public class RadixSort {

  /**
   * @param arr  待排数组
   * @param bits 每次处理的二进制位数（可选值：1, 2, 4, 8, 16）
   * @param mask 每次移动 bits 个二进制位后，使用 mask 取出最低的 bits 位。
   */
  // b 表示每次处理的二进制位数
  // Time: O(32/b * n), Space: O(n + 2^b)
  private void sort(int[] arr, int bits, int mask) {
    if (arr == null || arr.length == 0) return;
    int n = arr.length, cnt = 32/bits;
    int[] tmp = new int[n];
    int[] indexes = new int[1<<bits];
    for (int d = 0; d < cnt; ++d) {
      for (int num: arr) {
        int idx = (num >> (bits*d)) & mask;
        ++indexes[idx];
      }

      --indexes[0];
      for (int i = 1; i < indexes.length; ++i)
        indexes[i] = indexes[i] + indexes[i-1];

      for (int i = n-1; i >= 0; --i) {
        int idx = (arr[i] >> (bits*d)) & mask;
        tmp[indexes[idx]] = arr[i];
        --indexes[idx];
      }

      Arrays.fill(indexes, 0);
      int[] t = arr;
      arr = tmp;
      tmp = t;
    }
    // handle the negative number
    // get the length of positive part
    int len = 0;
    for (; len < n; ++len)
      if (arr[len] < 0) break;

    System.arraycopy(arr, len, tmp, 0, n-len); // copy negative part to tmp
    System.arraycopy(arr, 0, tmp, n-len, len); // copy positive part to tmp
    System.arraycopy(tmp, 0, arr, 0, n); // copy back to arr
  }

  // 基数为 256，每次取 8 个二进制位作为一个部分进行处理，32 位整数需要处理 4 次。
  // 每次取出的 8 个二进制位会作为计数排序的键值，去排序原始数据。
  // 每次处理 8 个二进制位，是时间/空间上比较折衷的方法。
  // 如果一次处理 16 个二进制位，速度会稍微快一些。但需要额外的空间是 2^16 = 65536，远大于每次处理 8 个二进制位所需空间。
  // 如果一次只处理 4 个二进制位，速度则会慢很多。
  public void sort4pass(int[] arr) {
    sort(arr, 8, 0xff);
  }

  // 基数为 16，每次取 4 个二进制位作为一个部分进行处理。32 位整数需要处理 8 次。
  // 时间上比起 sort4pass 要差很多。
  public void sort8pass(int[] arr) {
    sort(arr, 4, 0x0f);
  }

  // 基数为 65536，每次取 16 个二进制位作为一个部分进行处理。32 位整数需要处理 2 次。
  // 时间上比 sort4pass 要稍微好一些，但额外要使用多得多的空间。
  public void sort2pass(int[] arr) {
    sort(arr, 16, 0xffff);
  }

  // 基数为 2，每次取 1 个二进制位作为一个部分进行处理。32 位整数需要处理 32 次。
  // 时间上比快排要差很多。
  public void sort32pass(int[] arr) {
    sort(arr, 1, 1);
  }

  // 基数为 4，每次取 2 个二进制位作为一个部分进行处理。32 位整数需要处理 16 次。
  // 我是打酱油的。
  public void sort16pass(int[] arr) {
    sort(arr, 2, 3);
  }

}
