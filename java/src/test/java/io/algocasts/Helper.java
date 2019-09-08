package io.algocasts;

import org.junit.Assert;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Random;

public class Helper {

  /////////////////// List ////////////////////////
  public static <T> String list2String(List<T> list) {
    if (list == null) return "null";
    StringBuilder sb = new StringBuilder("[");
    for (T e: list) {
      if (e == null) sb.append("null, ");
      else sb.append(e).append(", ");
    }
    if (sb.length() >= 2) sb.delete(sb.length()-2, sb.length());
    sb.append("]");
    return sb.toString();
  }

  @SafeVarargs
  @SuppressWarnings("varargs")
  public static <T> List<T> buildList(T... a) {
    return Arrays.asList(a);
  }

  public static <T> List<List<T>> buildListList(T[][] a) {
    List<List<T>> result = new ArrayList<>();
    for (T[] e: a) {
      result.add(Arrays.asList(e));
    }
    return result;
  }

  /////////////////// Linked List ////////////////////////
  public static String linkedList2String(ListNode list) {
    if (list == null) return "null";
    StringBuilder sb = new StringBuilder(String.valueOf(list.val));
    for (list = list.next; list != null; list = list.next) {
      sb.append(" -> ").append(list.val);
    }
    return sb.toString();
  }

  public static void printListNode(ListNode list) {
    System.out.println(linkedList2String(list));
  }

  public static ListNode buildLinkedList(int... array) {
    if (array == null || array.length == 0) return null;
    ListNode dummy = new ListNode(0), p = dummy;
    for (int i = 0; i < array.length; ++i) {
      p.next = new ListNode(array[i]);
      p = p.next;
    }
    return dummy.next;
  }

  /*
  使用两个数组构建两条链表，并允许链表相交。
  数组尾部相同的元素会被构建成两链表相交后的部分。

  例如，输入的两个数组为：
  [1, 2, 6, 7]
  [3, 4, 5, 6, 7]

  构建后的两条链表为：

  A:     1 -> 2
                \
                 6 -> 7 -> null
                /
  B: 3 -> 4 -> 5

   */
  public static List<ListNode> buildLinkedList(int[] arr1, int[] arr2) {
    if (arr1 == null || arr1.length == 0) {
      return buildList(null, buildLinkedList(arr2));
    }
    if (arr2 == null || arr2.length == 0) {
      return buildList(buildLinkedList(arr1), null);
    }
    int n1 = arr1.length, n2 = arr2.length;
    int i = n1-1, j = n2-1;
    for (; i >= 0 && j >= 0 && arr1[i] == arr2[j]; --i, --j);
    // split to 3 arrays
    int[] a = new int[i+1], b = new int[j+1], c = new int[n1-i-1];
    System.arraycopy(arr1, 0, a, 0, i+1);
    System.arraycopy(arr1, i+1, c, 0, n1-i-1);
    System.arraycopy(arr2, 0, b, 0, j+1);
    // build 3 linked lists
    ListNode dummyA = new ListNode(0), dummyB = new ListNode(0);
    dummyA.next = buildLinkedList(a);
    dummyB.next = buildLinkedList(b);
    ListNode tail = buildLinkedList(c);
    // find the end
    ListNode endA = dummyA, endB = dummyB;
    for (; endA.next != null; endA = endA.next);
    for (; endB.next != null; endB = endB.next);
    // link to the tail
    endA.next = tail;
    endB.next = tail;
    return buildList(dummyA.next, dummyB.next);
  }

  /**
   * 根据数组和环的开始节点下标，构建一个带环链表。
   *
   * @param array 整数数组
   * @param startNodeIndex 0-based. 该下标指向的数字作为链表中环的开始节点
   * @return
   */
  public static ListNode buildLinkedListWithCycle(int[] array, int startNodeIndex) {
    if (array == null || array.length == 0) return null;
    ListNode dummy = new ListNode(0), p = dummy, start = null;
    for (int i = 0; i < array.length; ++i, --startNodeIndex) {
      p.next = new ListNode(array[i]);
      p = p.next;
      if (startNodeIndex == 0) start = p;
    }
    p.next = start;
    return dummy.next;

  }

  /////////////////// Tree ////////////////////////
  public static String tree2String(TreeNode root) {
    if (root == null) return "null";
    StringBuilder sb = new StringBuilder();
    Queue<TreeNode> q = new LinkedList<>();
    q.add(root);
    while (!q.isEmpty()) {
      TreeNode node = q.poll();
      String str = node == null ? "null" : String.valueOf(node.val);
      sb.append(str).append(", ");
      if (node != null && (node.left != null || node.right != null)) {
        q.add(node.left); q.add(node.right);
      }
    }
    return sb.toString();
  }

  public static TreeNode buildTree(int[] preorder, int[] inorder) {
    Map<Integer, Integer> inPos = new HashMap<>();
    for (int i = 0; i < inorder.length; ++i)
      inPos.put(inorder[i], i);
    return buildTree(preorder, 0, preorder.length-1, 0, inPos);
  }

  private static class Item {
    TreeNode parent;
    Integer childVal;
    boolean isLeft;
    Item(TreeNode parent, Integer childVal, boolean isLeft) {
      this.parent = parent;
      this.childVal = childVal;
      this.isLeft = isLeft;
    }
  }

  /*
   * 根据树的层序遍历数组，构建一棵二叉树。例如：
   * 输入：[3,2,3,null,3,null,1]
   *
   * 构建的二叉树是：
   *     3
   *    / \
   *   2   3
   *    \   \
   *     3   1
   *
   * 注意：如果某个位置上的节点为 null，那么它左右子树的 null 不需要写出来。
   * 比如对于下面这棵树：
   *     1
   *      \
   *       2
   *        \
   *         4
   * 应该表示为：[1,null,2,null,4]
   * 而不是：[1,null,2,null,null,null,4]
   *
   */
  public static TreeNode buildTree(Integer... nums) {
    if (nums == null || nums.length == 0) return null;
    TreeNode dummy = new TreeNode(0);
    Queue<Item> q = new LinkedList<>();
    q.add(new Item(dummy, nums[0], true));
    int p = 1;

    while (!q.isEmpty()) {
      Item item = q.poll();
      TreeNode child = item.childVal == null ? null : new TreeNode(item.childVal);
      if (item.isLeft) item.parent.left = child;
      else item.parent.right = child;
      if (child != null && p < nums.length) {
        q.add(new Item(child, nums[p++], true));
        q.add(new Item(child, nums[p++], false));
      }
    }
    return dummy.left;
  }

  public static List<List<Integer>> levelOrderTraversal(TreeNode root) {
    if (root == null) return new ArrayList<>();
    List<List<Integer>> result = new ArrayList<>();
    Queue<TreeNode> q = new LinkedList<>();
    q.add(root);

    while (!q.isEmpty()) {
      List<Integer> elem = new ArrayList<>();
      int size = q.size();
      for (int i = 0; i < size; ++i) {
        TreeNode s = q.poll();
        elem.add(s.val);
        if (s.left != null) q.add(s.left);
        if (s.right != null) q.add(s.right);
      }
      result.add(elem);
    }

    return result;
  }

  public static void printTreeByLevel(TreeNode root){
    List<List<Integer>> result = levelOrderTraversal(root);
    for (List<Integer> level: result) {
      for (Integer num: level) {
        System.out.print(num + " ");
      }
      System.out.println();
    }
    System.out.println();
  }

  // [3,2,3,null,3,null,1]
  //     3
  //    / \
  //   2   3
  //    \   \
  //     3   1
  public static void printTreeByLevelWithNull(TreeNode root) {
    if (root == null) {
      System.out.println("null");
      return;
    }
    Queue<TreeNode> q = new LinkedList<>();
    q.add(root);
    while (!q.isEmpty()) {
      TreeNode node = q.poll();
      String str = node == null ? "null" : String.valueOf(node.val);
      System.out.print(str + ", ");
      if (node != null && (node.left != null || node.right != null)) {
        q.add(node.left); q.add(node.right);
      }
    }
    System.out.println();
  }

  public static boolean isValidBST(TreeNode root) {
    return isValidBSTBound(root, null, null);
  }

  public static boolean isBalancedTree(TreeNode root) {
    return getHeightAndCheck(root) != -1;
  }

  ///////////////////////// Assert //////////////////////////////
  public static void assertEquals(ListNode expected, ListNode actual) {
    ListNode e = expected, a = actual;
    while (e != null && a != null) {
      if (e.val != a.val)
        throw new AssertionError("expected: " + linkedList2String(expected) + ", actual: " + linkedList2String(actual));
      e = e.next;
      a = a.next;
    }
    if (e != null || a != null)
      throw new AssertionError("expected: " + linkedList2String(expected) + ", actual: " + linkedList2String(actual));
  }

  public static void assertEquals(TreeNode expected, TreeNode actual) {
    if (!isSameTree(expected, actual)) {
      throw new AssertionError("expected: " + tree2String(expected) + ", actual: " + tree2String(actual));
    }
  }

  public static <T> void assertEquals(List<T> expecteds, List<T> actuals) {
    if (expecteds != null && actuals != null) {
      if (expecteds.size() != actuals.size())
        throw new AssertionError("expected: " + list2String(expecteds) + ", actual: " + list2String(actuals));
      for (int i = 0; i < expecteds.size(); ++i) {
        if (!expecteds.get(i).equals(actuals.get(i)))
          throw new AssertionError("expected: " + list2String(expecteds) + ", actual: " + list2String(actuals));
      }
    } else if (expecteds != null || actuals != null)
      throw new AssertionError("expected: " + list2String(expecteds) + ", actual: " + list2String(actuals));
  }

  public static <T> void assertListListEquals(List<List<T>> expecteds, List<List<T>> actuals) {
    if (expecteds != null && actuals != null) {
      Assert.assertEquals(expecteds.size(), actuals.size());
      for (int i = 0; i < expecteds.size(); ++i)
        assertEquals(expecteds.get(i), actuals.get(i));
    } else if (expecteds != null || actuals != null)
      throw new AssertionError("In expecteds and actuals, one if null but another is not null.");
  }

  private static final Random RANDOM = new Random();
  private static final int FACTOR = 10;
  private static final String ALPHANUMERIC = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";

  public static void println(String str) {
    System.out.println(str);
  }

  public static void println() {
    System.out.println();
  }

  public static void printArray(final int[] arr) {
    if (arr == null || arr.length == 0) return;
    for (int i = 0; i < arr.length; ++i)
      System.out.print(arr[i] + " ");
    System.out.println();
  }

  public static void printArray(String message, final int[] arr) {
    System.out.println(message + ": ");
    printArray(arr);
  }

  public static void printMatrix(final int[][] matrix) {
    for (int i = 0; i < matrix.length; ++i) {
      for (int j = 0; j < matrix[0].length; ++j) {
        System.out.print(matrix[i][j] + " ");
      }
      System.out.println();
    }
    System.out.println();
  }

  public static void printList(final List<Integer> list) {
    for (int e: list) {
      System.out.print(e + " ");
    }
    System.out.println();
  }
  public static <T> void printListList(final List<List<T>> lists) {
    for (List<T> list: lists) {
      for (T e: list) {
        System.out.print(e + " ");
      }
      System.out.println();
    }
    System.out.println();
  }

  public static int getRandomInt(int upperBound) {
    return RANDOM.nextInt(upperBound);
  }

  // return a random int value between lowerBound(included) and upperBound(excluded)
  public static int getRandomInt(int lowerBound, int upperBound) {
    return RANDOM.nextInt(upperBound - lowerBound) + lowerBound;
  }

  public static String getRandomString(int length) {
    if (length <= 0) return "";
    StringBuilder sb = new StringBuilder(length);
    for (int i = 0; i < length; ++i) {
      int idx = RANDOM.nextInt(ALPHANUMERIC.length());
      sb.append(ALPHANUMERIC.charAt(idx));
    }
    return sb.toString();
  }

  public static int[] getRandomIntArray(int arrayLength) {
    int minValue = -arrayLength * FACTOR;
    int maxValue = arrayLength * FACTOR;
    int flag = RANDOM.nextInt(3);
    if (flag == 0) {
      // all positive (include 0)
      return getRandomIntArray(arrayLength, 0, maxValue);
    } else if (flag == 1) {
      // all negative
      return getRandomIntArray(arrayLength, minValue, 0);
    } else {
      // mixed positive and negative
      return getRandomIntArray(arrayLength, minValue, maxValue);
    }
  }

  // All elements are in [minValue, maxValue)
  public static int[] getRandomIntArray(int arrayLength, int minValue, int maxValue) {
    int[] arr = new int[arrayLength];
    for (int i = 0; i < arrayLength; ++i) {
      arr[i] = RANDOM.nextInt(maxValue - minValue) + minValue;
    }
    return arr;
  }

  // All elements are in [0, 1)
  public static double[] getRandomDoubleArray(int arrayLength) {
    double[] arr = new double[arrayLength];
    for (int i = 0; i < arrayLength; ++i) {
      arr[i] = RANDOM.nextDouble();
    }
    return arr;
  }

  public static int[][] getRandomMatrix(int row, int col, int minValue, int maxValue) {
    int[][] matrix = new int[row][col];
    for (int i = 0; i < row; ++i)
      for (int j = 0; j < col; ++j)
        matrix[i][j] = RANDOM.nextInt(maxValue - minValue) + minValue;

    return matrix;
  }

  public static int[] reverseArray(int[] arr) {
    if (arr == null || arr.length == 0) return arr;
    for (int i=0, j=arr.length-1; i < j; ++i, --j)
      swap(arr, i, j);
    return arr;
  }

  public static int[] getSortedArray(int arrayLength) {
    int[] arr = getRandomIntArray(arrayLength);
    Arrays.sort(arr);
    return arr;
  }

  public static int[] getReverseSortedArray(int arrayLength) {
    return reverseArray(getSortedArray(arrayLength));
  }

  public static int[] getSameElementArray(int arrayLength, int value) {
    int[] arr = getRandomIntArray(arrayLength);
    Arrays.fill(arr, value);
    return arr;
  }

  public static int[] sortByBuiltinMethod(final int[] arr) {
    if (arr == null) return null;
    int[] sorted = new int[arr.length];
    System.arraycopy(arr, 0, sorted, 0, arr.length);
    Arrays.sort(sorted);
    return sorted;
  }

  public static double[] sortByBuiltinMethod(final double[] arr) {
    if (arr == null) return null;
    double[] sorted = new double[arr.length];
    System.arraycopy(arr, 0, sorted, 0, arr.length);
    Arrays.sort(sorted);
    return sorted;
  }


  //////////////////// private method ///////////////////////
  private static void swap(int[] arr, int i, int j) {
    int tmp = arr[i];
    arr[i] = arr[j];
    arr[j] = tmp;
  }

  private static TreeNode buildTree(
    int[] preorder, int preStart, int preEnd,
    int inStart, Map<Integer, Integer> inPos) {

    if (preStart > preEnd) return null;
    TreeNode root = new TreeNode(preorder[preStart]);
    int rootIdxInorder = inPos.get(preorder[preStart]);
    int leftLen = rootIdxInorder - inStart;
    root.left = buildTree(preorder, preStart+1, preStart+leftLen, inStart, inPos);
    root.right = buildTree(preorder, preStart+leftLen+1, preEnd, rootIdxInorder+1, inPos);
    return root;
  }

  private static boolean isSameTree(TreeNode p, TreeNode q) {
    if (p == null && q == null) return true;
    if (p == null || q == null) return false;
    return p.val == q.val && isSameTree(p.left, q.left) && isSameTree(p.right, q.right);
  }

  private static boolean isValidBSTBound(TreeNode root, TreeNode lower, TreeNode upper) {
    if (root == null) return true;
    if (lower != null && lower.val >= root.val) return false;
    if (upper != null && upper.val <= root.val) return false;
    return isValidBSTBound(root.left, lower, root) && isValidBSTBound(root.right, root, upper);
  }

  private static int getHeightAndCheck(TreeNode root) {
    if (root == null) return 0;

    int left = getHeightAndCheck(root.left);
    if (left == -1) return -1;

    int right = getHeightAndCheck(root.right);
    if (right == -1) return -1;

    if (Math.abs(left - right) > 1) return -1;
    return Math.max(left, right) + 1;
  }

}
