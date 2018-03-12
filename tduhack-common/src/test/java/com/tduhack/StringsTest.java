package com.tduhack;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class StringsTest {

  @Test
  public void testIsEmpty() {
    assertThat(Strings.isEmpty("")).isTrue();
    assertThat(Strings.isEmpty("ABC")).isFalse();
    assertThat(Strings.isEmpty("  ")).isTrue();
    assertThat(Strings.isEmpty(null)).isTrue();
  }
}