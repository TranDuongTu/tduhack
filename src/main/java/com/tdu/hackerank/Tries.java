package com.tdu.hackerank;

import java.lang.reflect.Array;

public class Tries<V> {
  
  private Node root;
  private final Alphabet alphabet;
  
  public Tries(final Alphabet alphabet) {
    this.alphabet = alphabet;
  }
  
  public void put(final String key, final V value) {
    root = put(root, key, value, 0);
  }
  
  private Node put(Node node, final String key, final V value, final int d) {
    if (node == null) node = new Node();
    if (key.length() == d) {
      node.value = value;
      return node;
    }
    final char nextChar = key.charAt(d);
    final int child = alphabet.index(nextChar);
    node.children[child] = put(node.children[child], key, value, d + 1);
    return node;
  }
  
  public V get(final String key) {
    return get(root, key, 0);
  }

  private V get(Node node, String key, int d) {
    if (node == null) return null;
    if (d == key.length()) return node.value;
    final char nextChar = key.charAt(d);
    final int child = alphabet.index(nextChar);
    return get(node.children[child], key, d + 1);
  }

  public void delete(final String key) {
    root = delete(root, key, 0);
  }

  private Node delete(Node node, String key, int d) {
    if (node == null) return null;
    if (key.length() == d) {
      node.value = null;
    } else {
      final char nextChar = key.charAt(d);
      final int child = alphabet.index(nextChar);
      node.children[child] = delete(node.children[child], key, d + 1);
    }
    
    if (node.value != null) return node;
    for (int child = 0; child < alphabet.R(); child++) {
      if (node.children[child] != null) {
        return node;
      }
    }
    return null;
  }

  public boolean contains(final String key) {
    return get(root, key, 0) != null;
  }
  
  public boolean isEmpty() {
    return root == null;
  }
  
  public String longestPrefixOf(final String s) {
    return null;
  }
  
  public Iterable<String> keysWithPrefix(final String s) {
    return null;
  }
  
  public int size() {
    return 0;
  }
  
  public Iterable<String> keys() {
    return null;
  }
  
  private class Node {
    V value;
    Node[] children = (Node[]) Array.newInstance(Node.class, alphabet.R());
  }
  
  public interface Alphabet {
    int R();
    int index(final char character);
  }
}
