package com.tduhack;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class FieldsBaseTest {

  @Test
  public void testGetSet() {
    final HasFields hasFields = FieldsBase.create();
    final Field<String> stringField = new Field<>("string", String.class);
    final Field<Integer> integerField = new Field<>("integer", Integer.class);
    final Field<Double> doubleField = new Field<>("double", Double.class);
    hasFields.set(stringField, "ABC");
    hasFields.set(integerField, 1);

    assertThat(hasFields.contains(stringField)).isTrue();
    assertThat(hasFields.get(stringField)).isEqualTo("ABC");
    assertThat(hasFields.get(integerField)).isEqualTo(1);
    assertThat(hasFields.contains(doubleField)).isFalse();
    assertThat(hasFields.get(doubleField)).isNull();
  }
}