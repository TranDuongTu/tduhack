package com.tduhack.dsa.exercises;

public class SubArrayWithGivenSum {

  public static int[] findSubArray(final int[] array, final int sum) {
    int start = 0, end = 0, currentSum = array[0];
    while (end < array.length) {
      if (currentSum == sum) {
        return new int[]{start, end};
      } else if (currentSum > sum && start < end) {
        currentSum -= array[start++];
      } else {
        currentSum += array[++end];
      }
    }
    return null;
  }
}
