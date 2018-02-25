package com.tdu.tduhack.dsa.exercises;

import com.tdu.tduhack.dsa.Problem;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

@Problem(name = "Running Median", level = 3)
public class RunningMedianTest {

  @Test
  public void testFindRunningMedian() throws Exception {
    assertThat(RunningMedian.findRunningMedians(new int[]{4, 1, 2, 5, 7, 8}))
        .isEqualTo(new int[]{4, 1, 2, 2, 4, 4});
  }
}