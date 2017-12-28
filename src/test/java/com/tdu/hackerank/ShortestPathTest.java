package com.tdu.hackerank;

import org.assertj.core.api.Assertions;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

public class ShortestPathTest {
  
  @Test
  public void testDistancesFromSourceToAll() throws Exception {
    final int SIZE = 1;
    for (int i = 1; i <= SIZE; i++) {
      final String inputFile = "/ShortestPath/input" + i + ".txt";
      final String outputFile = "/ShortestPath/output" + i + ".txt";
      final InputStream inputStream = ShortestPathTest.class.getResourceAsStream(inputFile);
      InputStream outputStream = ShortestPathTest.class.getResourceAsStream(outputFile);
      final int[][] input = readInput(inputStream);
      final int[] expectedOutput = readOutPut(outputStream);
      final int[] result = Dijkstra.shortestDistancesFromSource(input);
      Assertions.assertThat(result).hasSameSizeAs(expectedOutput).isEqualTo(expectedOutput);
    }
  }

  private int[][] readInput(final InputStream stream) throws Exception {
    final BufferedReader br = new BufferedReader(new InputStreamReader(stream));
    try {
      String[] buff = br.readLine().split(" ");
      final int n = Integer.valueOf(buff[0]);
      final int m = Integer.valueOf(buff[1]);
      final int[][] result = new int[m + 2][];
      result[0] = new int[]{n, m};
      for (int i = 0; i < m; i++) {
        buff = br.readLine().split(" ");
        final int u = Integer.valueOf(buff[0]);
        final int v = Integer.valueOf(buff[1]);
        final int w = Integer.valueOf(buff[2]);
        result[i + 1] = new int[]{u, v, w};
      }
      result[result.length - 1] = new int[]{Integer.valueOf(br.readLine())};
      return result;
    } finally {
      br.close();
    }
  }

  private int[] readOutPut(final InputStream stream) throws Exception {
    final BufferedReader br = new BufferedReader(new InputStreamReader(stream));
    try {
      final String[] buff = br.readLine().split(" ");
      final int[] result = new int[buff.length];
      for (int i = 0; i < result.length; i++) {
        result[i] = Integer.valueOf(buff[i]);
      }
      return result;
    } finally {
      br.close();
    }
  }
}
