package com.tdu.hackerank.ds;

import java.util.ArrayList;
import java.util.List;

public class Dijkstra {

  private static List<List<int[]>> graph;
  private static int[] distTo;
  private static boolean[] marked;
  private static int source;

  public static int[] shortestDistancesFromSource(final int[][] input) {
    readGraph(input);
    runDijkstra();

    final int[] result = new int[distTo.length - 1];
    for (int i = 0, k = 0; i < distTo.length; i++) {
      if (i != source) {
        result[k++] = distTo[i] == Integer.MAX_VALUE ? -1 : distTo[i];
      }
    }
    return result;
  }

  private static void runDijkstra() {
    final Queue queue = new Queue(graph.size());
    queue.insert(source, 0);

    while (!queue.isEmpty()) {
      final int u = queue.pop();
      marked[u] = true;
      for (final int[] edge : graph.get(u)) {
        final int v = edge[0];
        final int w = edge[1];
        if (!marked[v] && distTo[v] > distTo[u] + w) {
          distTo[v] = distTo[u] + w;
          if (queue.contains(v)) {
            queue.decreaseKey(v, distTo[v]);
          } else {
            queue.insert(v, distTo[v]);
          }
        }
      }
    }
  }

  private static void readGraph(int[][] input) {
    final int n = input[0][0], m = input[0][1];
    graph = new ArrayList<>();
    for (int i = 0; i < n; i++) {
      graph.add(new ArrayList<>());
    }

    for (int i = 0; i < m; i++) {
      final int u = input[i + 1][0] - 1;
      final int v = input[i + 1][1] - 1;
      final int w = input[i + 1][2];
      graph.get(u).add(new int[]{v, w});
      graph.get(v).add(new int[]{u, w});
    }

    source = input[input.length - 1][0] - 1;
    distTo = new int[n];
    for (int i = 0; i < distTo.length; i++) distTo[i] = Integer.MAX_VALUE;
    distTo[source] = 0;

    marked = new boolean[n];
  }

  private static class Queue {

    private final int[] queue, inverseQueue, keys;
    private int size;

    public Queue(final int maxSize) {
      queue = new int[maxSize];
      inverseQueue = new int[maxSize];
      keys = new int[maxSize];
      for (int i = 0; i < maxSize; i++) {
        queue[i] = inverseQueue[i] = keys[i] = -1;
      }
    }

    public void insert(int i, int key) {
      keys[i] = key;
      queue[size] = i;
      inverseQueue[i] = size;
      size++;
      swim(size - 1);
    }

    private void swim(int i) {
      while (i > 0) {
        final int parent = (i - 1) / 2;
        if (keys[queue[i]] < keys[queue[parent]]) {
          swap(i, parent);
        }
        i = parent;
      }
    }

    private void swap(int i, int j) {
      final int tmp = queue[i];
      queue[i] = queue[j];
      queue[j] = tmp;
      inverseQueue[queue[i]] = i;
      inverseQueue[queue[j]] = j;
    }

    public boolean isEmpty() {
      return size == 0;

    }

    public int pop() {
      final int min = queue[0];
      swap(0, size - 1);
      size--;
      sink(0);
      assert queue[size] == min;
      assert inverseQueue[min] == size;
      queue[size] = -1;
      inverseQueue[min] = -1;
      return min;
    }

    private void sink(int i) {
      final int left = i * 2 + 1;
      final int right = left + 1;

      int smallest = i;
      if (left < size && keys[queue[left]] < keys[queue[smallest]]) {
        smallest = left;
      }

      if (right < size && keys[queue[right]] < keys[queue[smallest]]) {
        smallest = right;
      }

      if (smallest != i) {
        swap(smallest, i);
        sink(smallest);
      }
    }

    public boolean contains(int i) {
      return inverseQueue[i] != -1;
    }

    public void decreaseKey(int i, int key) {
      keys[i] = key;
      swim(inverseQueue[i]);
    }
  }
}
