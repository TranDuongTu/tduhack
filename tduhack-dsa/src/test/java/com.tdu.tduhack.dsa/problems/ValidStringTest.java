package com.tdu.tduhack.dsa.problems;

import org.assertj.core.api.Assertions;
import org.junit.Test;

public class ValidStringTest {

  @Test
  public void testValidString() throws Exception {
    Assertions.assertThat(ValidString.isValid("hfchdkkbfifgbgebfaahijchgeeeiagkadjfcbekbdaifchkjfejckbiiihegacfbchdihkgbkbddgaefhkdgccjejjaajgijdkd")).isTrue();
    Assertions.assertThat(ValidString.isValid("aabbcc")).isTrue();
    Assertions.assertThat(ValidString.isValid("baacdd")).isFalse();
    Assertions.assertThat(ValidString.isValid("aabbccc")).isTrue();
    Assertions.assertThat(ValidString.isValid("aabbc")).isTrue();
    Assertions.assertThat(ValidString.isValid("aabbccddeefghi")).isFalse();
    Assertions.assertThat(ValidString.isValid("aaaabbccdd")).isFalse();
  }
}