package com.tduhack;

public final class Strings {
  public static boolean isEmpty(final String s) {
    return s == null || "".equals(s.trim());
  }

  public static boolean isNotEmpty(final String s) {
    return !isEmpty(s);
  }
}
