package com.tduhack;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class JSON implements HasFields<JSON> {

  private final Map<Field, Object> map = new HashMap<>();

  public static JSON create() {
    return new JSON();
  }

  @Override
  public <T> T get(Field<T> field) {
    return (T) map.get(field);
  }

  @Override
  public <T> JSON set(Field<T> field, T value) {
    map.put(field, value);
    return this;
  }

  @Override
  public boolean contains(Field field) {
    return map.containsKey(field);
  }

  @Override
  public Iterator<Field> iterator() {
    return map.keySet().iterator();
  }
}
