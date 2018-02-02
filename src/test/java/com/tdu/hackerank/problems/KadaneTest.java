package com.tdu.hackerank.problems;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class KadaneTest {

  @Test
  public void testKadane() throws Exception {
    assertThat(Kadane.maximumSubArraySum(new int[]{1, 2, 3})).isEqualTo(6);
    assertThat(Kadane.maximumSubArraySum(new int[]{-1, -2, -3})).isEqualTo(-1);
    assertThat(Kadane.maximumSubArraySum(new int[]{-1, 1, 2, 3, 4, -5, -7})).isEqualTo(10);
    assertThat(Kadane.maximumSubArraySum(new int[]{-1, 1, 2, 3, 4, -5, -7, 100})).isEqualTo(100);
  }
}