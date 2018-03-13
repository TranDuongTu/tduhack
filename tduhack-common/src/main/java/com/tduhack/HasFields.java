package com.tduhack;

public interface HasFields<H extends HasFields> extends Iterable<Field> {

  <T> T get(final Field<T> field);

  <T> H set(final Field<T> field, final T value);

  default HasFields set(final String key, final Object value) {
    final Field field = new Field(key, guessType(value));
    return set(field, value);
  }

  default Class guessType(final Object value) {
    if (value instanceof Integer) {
      return Integer.class;
    } else if (value instanceof String) {
      return String.class;
    } else if (value instanceof Long) {
      return Long.class;
    }
    throw new RuntimeException("Cannot guess type of value " + value);
  }

  boolean contains(final Field field);

  default Long id() {
    return get(HasId.id);
  }
}
