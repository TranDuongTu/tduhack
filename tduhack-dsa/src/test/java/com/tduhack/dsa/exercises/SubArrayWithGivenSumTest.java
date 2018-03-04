package com.tduhack.dsa.exercises;

import com.tduhack.dsa.Problem;
import org.assertj.core.api.Assertions;
import org.junit.Test;

@Problem(name = "Sub array with given sum", level = 2)
public class SubArrayWithGivenSumTest {

  @Test
  public void testSubArrayWithGivenSum() throws Exception {
    Assertions.assertThat(SubArrayWithGivenSum
        .findSubArray(new int[]{1, 2, 3, 7, 5}, 12))
        .isEqualTo(new int[]{1, 3});
    Assertions.assertThat(SubArrayWithGivenSum
        .findSubArray(new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10}, 15))
        .isEqualTo(new int[]{0, 4});
  }
}