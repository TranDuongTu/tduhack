package com.tdu.hackerank;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class Kruskal {

  public static List<int[]> findMST(final List<int[]> input) {
    final List<int[]> mst = new ArrayList<>();
    final int n = input.get(0)[0];
    final int[] uf = new int[n];
    for (int i = 0; i < uf.length; i++) {
      uf[i] = i;
    }

    int connectedEdges = 0;
    for (final int[] edge : sortEdgeList(input)) {
      if (connectedEdges < n - 1) {
        final int u = edge[0] - 1;
        final int v = edge[1] - 1;
        final int w = edge[2];
        if (!find(uf, u, v)) {
          unite(uf, u, v);
          mst.add(new int[]{u, v, w});
          connectedEdges++;
        }
      }
    }
    
    return mst;
  }

  private static List<int[]> sortEdgeList(final List<int[]> input) {
    return input.stream().filter(row -> row.length == 3)
        .sorted(Comparator.comparingInt(e -> e[2]))
        .collect(Collectors.toList());
  }

  private static boolean find(final int[] uf, final int u, final int v) {
    return root(uf, u) == root(uf, v);
  }

  private static int root(final int[] uf, int u) {
    while (u != uf[u]) {
      uf[u] = uf[uf[u]];
      u = uf[u];
    }
    return u;
  }

  private static void unite(final int[] uf, final int u, final int v) {
    final int rootU = root(uf, u);
    final int rootV = root(uf, v);
    uf[rootU] = rootV;
  }
}
