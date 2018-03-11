package com.tduhack;

public interface HasFields {
  <T> T get(final Field<T> field);

  <T> HasFields set(final Field<T> field, final T value);

  boolean contains(final Field field);
}
