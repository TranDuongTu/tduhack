package com.tdu.tduhack.dsa.exercises;

/**
 * https://www.geeksforgeeks.org/largest-sum-contiguous-subarray/
 */
public class Kadane {
  
  public static int maximumSubArraySum(final int[] array) {
    int maxValue = Integer.MIN_VALUE, maxSoFar = 0;
    for (int i = 0; i < array.length; i++) {
      maxSoFar += array[i];
      if (maxSoFar > maxValue) {
        maxValue = maxSoFar;
      }
      
      if (maxSoFar < 0) {
        maxSoFar = 0;
      }
    }
    return maxValue;
  }
}
