package com.tdu.tduhack.dsa.exercises;

import java.util.ArrayList;
import java.util.List;

public class FlattenList {
  
  public static List<Object> flatten(final List<Object> list) {
    final List<Object> result = new ArrayList<>();
    flattenRecursive(list, result);
    return result;
  }

  private static void flattenRecursive(List<Object> list, final List<Object> result) {
    for (final Object element : list) {
      if (element instanceof List) {
        flattenRecursive((List<Object>) element, result);
      } else {
        result.add(element);
      }
    }
  }
}
