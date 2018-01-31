package com.tdu.hackerank.problems;

public class Steps {

  public char[][] buildStairs(final int n) {
    final char[][] result = new char[n][];
    buildStairs(n, result);
    return result;
  }

  private void buildStairs(int n, char[][] result) {
    if (n > 0) {
      buildStairs(n - 1, result);
      final char[] stair = new char[n];
      for (int i = 0; i < stair.length; i++) stair[i] = '#';
      result[n - 1] = stair;
    }
  }

  public char[][] buildPyramid(final int n) {
    return new char[][]{};
  }
}
