package com.tduhack.dsa.exercises;

import com.tduhack.dsa.Problem;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

@Problem(name = "String construction", level = 1)
public class StringConstructionTest {

  @Test
  public void testConstructingString() throws Exception {
    assertThat(StringConstruction.constructString("abcufabcfa")).isEqualTo(5);
    assertThat(StringConstruction.constructString("abab")).isEqualTo(2);
  }
}