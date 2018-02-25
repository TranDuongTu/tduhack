package com.tdu.tduhack.dsa.exercises;

public class Kadane {
  
  public static int maximumSubArraySum(final int[] array) {
    int result = Integer.MIN_VALUE, maxSoFar = 0;
    for (int i = 0; i < array.length; i++) {
      maxSoFar += array[i];
      if (result < maxSoFar) {
        result = maxSoFar;
      }
      if (maxSoFar < 0) {
        maxSoFar = 0;
      }
    }
    
    return result;
  }
}
