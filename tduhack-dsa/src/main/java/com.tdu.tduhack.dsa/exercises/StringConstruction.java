package com.tdu.tduhack.dsa.exercises;

import java.util.HashSet;
import java.util.Set;

/**
 * https://www.hackerrank.com/challenges/string-construction/problem
 */
public class StringConstruction {

  public static int constructString(final String input) {
    int cost = 0;
    final Set<Character> explored = new HashSet<>();

    for (int i = 0; i < input.length(); i++) {
      final char c = input.charAt(i);
      if (!explored.contains(c)) {
        cost++;
      }
      explored.add(c);
    }

    return cost;
  }
}
