package com.tduhack.dsa.exercises;

/**
 * https://www.hackerrank.com/challenges/string-construction/problem
 */
public class StringConstruction {

  public static int constructString(final String input) {
    final int MAX_CHAR = 255;
    final int[] explored = new int[MAX_CHAR];
    int cost = 0;

    for (int i = 0; i < input.length(); i++) {
      final char c = input.charAt(i);
      if (explored[c] == 0) {
        cost++;
      }
      explored[c] = 1;
    }

    return cost;
  }
}
