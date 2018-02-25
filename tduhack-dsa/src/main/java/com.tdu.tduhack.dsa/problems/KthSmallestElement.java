package com.tdu.tduhack.dsa.problems;


import com.tdu.tduhack.dsa.Utils;

public class KthSmallestElement {
  
  public static int findSmallest(final int[] array, final int k) {
    return findSmallest(array, 0, array.length - 1, k);
  }

  private static int findSmallest(int[] array, int from, int to, int k) {
    if (from < to && k >= from && k <= to) {
      final int pivot = partition(array, from, to);
      if (k - 1 == pivot) return array[pivot];
      if (k - 1 < pivot) return findSmallest(array, from, pivot, k);
      return findSmallest(array, pivot + 1, to, k);
    }
    return -1;
  }

  private static int partition(int[] array, int from, int to) {
    int i = from - 1, j = to + 1;
    int pivot = array[from];
    while (true) {
      do { i++; } while (array[i] < pivot);
      do { j--; } while (array[j] > pivot);
      
      if (i >= j) {
        return j;
      }
      Utils.swap(array, i, j);
    }
  }
}
