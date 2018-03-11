package com.tduhack;

public class Field<T> {
  private final String name;
  private final Class<T> dataType;

  public Field(final String name, final Class<T> dataType) {
    this.name = name;
    this.dataType = dataType;
  }

  public String name() {
    return name;
  }
}
