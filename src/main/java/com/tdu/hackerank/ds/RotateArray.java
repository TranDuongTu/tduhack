package com.tdu.hackerank.ds;

public class RotateArray {
  public static void rotateRight(final int[][] array) {
    final int n = array.length;
    for (int layer = 0; layer < n / 2; layer++) {
      for (int i = layer; i < n - layer - 1; i++) {
        final int up = array[layer][layer + i];
        final int right = array[layer + i][n - 1 - layer];
        final int down = array[n - 1 - layer][n - 1 - layer - i];
        final int left = array[n - 1 - layer - i][layer];
        array[layer][layer + i] = left;
        array[layer + i][n - 1 - layer] = up;
        array[n - 1 - layer][n - 1 - layer - i] = right;
        array[n - 1 - layer - i][layer] = down;
      }
    }
  }
}
