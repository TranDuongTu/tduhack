package com.tdu.hackerank;

import java.util.ArrayList;
import java.util.List;

public class PRIM {

  // Priority queue
  private static int queueSize;
  private static int[] queue, inverseQueue, keys;

  // Graph
  private static List<List<int[]>> graph;
  private static int[] distTo;
  private static int[][] edgeTo;
  private static boolean[] marked;

  public static List<int[]> findMST(final List<int[]> input) {
    initQueue(input.get(0)[0]);
    initGraph(input);

    distTo[0] = 0;
    insertQueue(0, distTo[0]);
    final List<int[]> result = new ArrayList<>();
    while (!isQueueEmpty()) {
      final int u = pop();
      marked[u] = true;

      for (final int[] edge : graph.get(u)) {
        final int v = edge[0];
        final int w = edge[1];
        if (!marked[v] && distTo[v] > w) {
          distTo[v] = w;
          edgeTo[v][0] = u;
          edgeTo[v][1] = w;
          if (queueContains(v)) {
            decreaseKey(v, w);
          } else {
            insertQueue(v, w);
          }
        }
      }
    }

    for (int i = 0; i < edgeTo.length; i++) {
      if (edgeTo[i][0] != -1) {
        result.add(new int[]{edgeTo[i][0], i, edgeTo[i][1]});
      }
    }
    return result;
  }

  private static void initGraph(List<int[]> input) {
    final int n = input.get(0)[0];
    final int m = input.get(0)[1];
    graph = new ArrayList<>();
    for (int i = 0; i < n; i++) {
      graph.add(new ArrayList<>());
    }

    for (int i = 0; i < m; i++) {
      final int[] edge = input.get(i + 1);
      final int u = edge[0] - 1;
      final int v = edge[1] - 1;
      final int w = edge[2];
      graph.get(u).add(new int[]{v, w});
      graph.get(v).add(new int[]{u, w});
    }

    distTo = new int[n];
    marked = new boolean[n];
    for (int i = 0; i < n; i++) {
      distTo[i] = Integer.MAX_VALUE;
    }

    edgeTo = new int[n][2];
    for (int i = 0; i < n; i++) {
      edgeTo[i][0] = -1;
    }
  }

  private static void initQueue(final int maxSize) {
    queueSize = 0;
    queue = new int[maxSize];
    keys = new int[maxSize];
    inverseQueue = new int[maxSize];
    for (int i = 0; i < maxSize; i++) {
      queue[i] = -1; 
      keys[i] = -1;
      inverseQueue[i] = -1;
    }
  }

  private static void insertQueue(final int u, final int key) {
    queue[queueSize] = u;
    inverseQueue[u] = queueSize;
    keys[u] = key;
    queueSize++;
    swim(inverseQueue[u]);
  }

  private static int pop() {
    final int min = queue[0];
    swap(0, queueSize - 1);
    queueSize--;
    assert queue[queueSize] == min;
    assert inverseQueue[min] == queueSize;
    queue[queueSize] = -1;
    inverseQueue[min] = -1;
    keys[min] = -1;
    sink(0);
    return min;
  }

  private static boolean isQueueEmpty() {
    return queueSize == 0;
  }
  
  private static boolean queueContains(final int u) {
    return inverseQueue[u] != -1;
  }
  
  private static void decreaseKey(final int u, final int key) {
    keys[u] = key;
    swim(inverseQueue[u]);
  }

  private static void swim(final int i) {
    final int parent = (i - 1) / 2;
    if (i > 0 && keys[queue[i]] < keys[queue[parent]]) {
      swap(i, parent);
      swim(parent);
    }
  }

  private static void sink(final int i) {
    final int left = 2 * i + 1;
    final int right = left + 1;

    int smallest = i;
    if (left < queueSize && keys[queue[left]] < keys[queue[smallest]]) {
      smallest = left;
    }

    if (right < queueSize && keys[queue[right]] < keys[queue[smallest]]) {
      smallest = right;
    }

    if (smallest != i) {
      swap(smallest, i);
      sink(smallest);
    }
  }

  private static void swap(int i, int j) {
    int swap = queue[i];
    queue[i] = queue[j];
    queue[j] = swap;
    inverseQueue[queue[i]] = i;
    inverseQueue[queue[j]] = j;
  }
}
