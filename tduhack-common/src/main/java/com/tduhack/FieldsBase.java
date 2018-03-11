package com.tduhack;

import java.util.HashMap;
import java.util.Map;

public class FieldsBase implements HasFields {
  private final Map<Field, Object> map = new HashMap<>();

  private FieldsBase() {

  }

  public static FieldsBase create() {
    return new FieldsBase();
  }

  @Override
  public <T> T get(Field<T> field) {
    return (T) map.get(field);
  }

  @Override
  public <T> HasFields set(Field<T> field, T value) {
    map.put(field, value);
    return this;
  }

  @Override
  public boolean contains(Field field) {
    return map.containsKey(field);
  }
}
