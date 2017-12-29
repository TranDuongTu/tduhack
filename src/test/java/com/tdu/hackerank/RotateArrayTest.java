package com.tdu.hackerank;

import org.assertj.core.api.Assertions;
import org.junit.Test;

import java.util.Random;

public class RotateArrayTest {

  @Test
  public void testRandomRotate() throws Exception {
    final int SIZE = 1000;
    final int[][] array = new int[SIZE][SIZE];
    final int[][] expected = new int[SIZE][SIZE];
    final Random random = new Random();
    for (int i = 0; i < array.length; i++) {
      for (int j = 0; j < array.length; j++) {
        array[i][j] = random.nextInt(1000);
        expected[i][j] = array[i][j];
      }
    }

    RotateArray.rotateRight(array);
    Assertions.assertThat(array).isNotEqualTo(expected);
    RotateArray.rotateRight(array);
    Assertions.assertThat(array).isNotEqualTo(expected);
    RotateArray.rotateRight(array);
    Assertions.assertThat(array).isNotEqualTo(expected);
    RotateArray.rotateRight(array);
    Assertions.assertThat(array).isEqualTo(expected);
  }

  @Test
  public void testSimple() throws Exception {
    final int[][] expected = new int[][]{
        {1, 2, 3, 4, 5},
        {6, 7, 8, 9, 10},
        {11, 12, 13, 14, 15},
        {16, 17, 18, 19, 20},
        {21, 22, 23, 24, 25}
    };
    
    final int[][] array = new int[5][5];
    for (int i = 0; i < expected.length; i++) {
      for (int j = 0; j < expected.length; j++) {
        array[i][j] = expected[i][j];
      }
    }
    RotateArray.rotateRight(array);
    Assertions.assertThat(array)
        .isEqualTo(new int[][]{
            {21, 16, 11, 6, 1},
            {22, 17, 12, 7, 2},
            {23, 18, 13, 8, 3},
            {24, 19, 14, 9, 4},
            {25, 20, 15, 10, 5}
        });
  }
}