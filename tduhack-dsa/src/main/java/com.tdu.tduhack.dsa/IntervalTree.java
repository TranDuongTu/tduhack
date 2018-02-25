package com.tdu.tduhack.dsa;

import java.util.ArrayList;
import java.util.List;

public class IntervalTree {
  
  private Node root;
  
  public boolean isEmpty() {
    return root == null;
  }
  
  public void put(final Interval interval) {
    root = put(root, interval);
  }

  private Node put(Node node, Interval interval) {
    if (node == null) return new Node(interval, interval.to);
    if (shouldGoLeft(node, interval)) {
      node.left = put(node.left, interval);
    } else {
      node.right = put(node.right, interval);
    }
    node.maxPoint = maxPoint(node.right);
    return node;
  }
  
  private int maxPoint(final Node node) {
    if (node == null) return 0;
    return Math.max(Math.max(maxPoint(node.left), maxPoint(node.right)), node.interval.to);
  }

  public List<Interval> findOverlaps(final Interval interval) {
    final List<Interval> list = new ArrayList<>();
    findOverlaps(root, interval, list);
    return list;
  }

  private void findOverlaps(Node node, Interval interval, final List<Interval> list) {
    if (node == null) return;
    if (node.interval.isOverlap(interval)) list.add(node.interval);
    if (shouldGoLeft(node, interval)) {
      findOverlaps(node.left, interval, list);
    } else {
      findOverlaps(node.right, interval, list);
    }
  }
  
  private boolean shouldGoLeft(final Node node, final Interval interval) {
    return node.left != null && node.left.maxPoint < interval.from;
  }

  public static class Interval implements Comparable<Interval> {
    public final int from, to;
    public Interval(final int from, final int to) {
      assert from < to;
      this.from = from;
      this.to = to;
    }
    
    public int length() {
      return to - from;
    }
    
    public boolean isOverlap(final Interval other) {
      return other.to >= this.from && other.from <= this.to;
    }
    
    public Interval intersection(final Interval other) {
      if (!isOverlap(other)) {
        return null;
      }
      return new Interval(Math.max(from, other.from), Math.min(to, other.to));
    }
    
    public Interval union(final Interval other) {
      if (!isOverlap(other)) {
        return null;
      }
      return new Interval(Math.min(from, other.from), Math.max(to, other.to));
    }

    @Override
    public int compareTo(Interval other) {
      return from == other.from ? to - other.to : from - other.from;
    }

    @Override
    public String toString() {
      return "(" + from + ", " + to + ")";
    }
  }

  private class Node {
    public Interval interval;
    public Node left, right;
    public int maxPoint;
    public Node(final Interval interval, final int maxPoint) {
      this.interval = interval;
      this.maxPoint = maxPoint;
    }
  }
}
