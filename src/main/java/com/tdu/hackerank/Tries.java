package com.tdu.hackerank;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

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
    final Node node = get(root, key, 0);
    return node == null ? null : node.value;
  }

  private Node get(Node node, String key, int d) {
    if (node == null) return null;
    if (d == key.length()) return node;
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
    final Node node = get(root, key, 0);
    return node != null && node.value != null;
  }
  
  public boolean isEmpty() {
    return root == null;
  }
  
  public String longestPrefixOf(final String s) {
    final int length = search(root, s, 0, 0);
    return s.substring(0, length);
  }

  private int search(Node node, String key, int d, int length) {
    if (node == null) return length;
    if (node.value != null) length = d;
    if (key.length() == d) return length;
    final char nextChar = key.charAt(d);
    final int child = alphabet.index(nextChar);
    return search(node.children[child], key, d + 1, length);
  }

  public Iterable<String> keysWithPrefix(final String key) {
    final List<String> result = new ArrayList<>();
    collect(get(root, key, 0), key, result);
    return result;
  }

  private void collect(Node node, String key, final List<String> list) {
    if (node == null) return;
    if (node.value != null) list.add(key);
    for (int i = 0; i < alphabet.R(); i++) {
      collect(node.children[i], key + alphabet.characters()[i], list);
    }
  }

  public int size() {
    return 0;
  }
  
  public Iterable<String> keys() {
    return keysWithPrefix("");
  }
  
  private class Node {
    V value;
    Node[] children = (Node[]) Array.newInstance(Node.class, alphabet.R());
  }
  
  public interface Alphabet {
    int R();
    int index(final char character);
    Character[] characters();
  }
}
