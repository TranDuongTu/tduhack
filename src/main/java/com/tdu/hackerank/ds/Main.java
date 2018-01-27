package com.tdu.hackerank.ds;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {

  private static int[][] graph;
  private static int MAX = 40000;

  public static void main(final String[] args) throws Exception {
    final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    readGraph(br);
    calculateFloyd();

    final int Q = Integer.valueOf(br.readLine());
    for (int i = 0; i < Q; i++) {
      final String[] buff = br.readLine().split(" ");
      final int u = Integer.valueOf(buff[0]) - 1;
      final int v = Integer.valueOf(buff[1]) - 1;
      System.out.println(graph[u][v] >= MAX ? -1 : graph[u][v]);
    }

    br.close();
  }

  private static void calculateFloyd() {
    final int n = graph.length;
    for (int k = 0; k < n; k++) {
      for (int i = 0; i < n; i++) {
        for (int j = 0; j < n; j++) {
          if (graph[i][j] > graph[i][k] + graph[k][j]) {
            graph[i][j] = graph[i][k] + graph[k][j];
          }
        }
      }
    }
  }

  private static void readGraph(BufferedReader br) throws Exception {
    String[] buff = br.readLine().split(" ");
    final int n = Integer.valueOf(buff[0]);
    final int m = Integer.valueOf(buff[1]);
    graph = new int[n][n];
    for (int i = 0; i < n; i++) {
      for (int j = 0; j < n; j++) {
        graph[i][j] = MAX;
      }
    }
    
    for (int i = 0; i < n; i++) {
      graph[i][i] = 0;
    }

    for (int i = 0; i < m; i++) {
      buff = br.readLine().split(" ");
      final int u = Integer.valueOf(buff[0]) - 1;
      final int v = Integer.valueOf(buff[1]) - 1;
      final int w = Integer.valueOf(buff[2]);
      graph[u][v] = w;
    }
  }
}
