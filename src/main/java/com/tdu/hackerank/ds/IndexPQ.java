package com.tdu.hackerank.ds;

public class IndexPQ {
  
  private final int[] queue, inverseQueue;
  private final int[] keys;
  private int size = 0;
  
  public IndexPQ(final int maxSize) {
    queue = new int[maxSize];
    inverseQueue = new int[maxSize];
    keys = new int[maxSize];
    for (int i = 0; i < maxSize; i++) {
      queue[i] = -1;
      inverseQueue[i] = -1;
      keys[i] = -1;
    }
  }
  
  public void insert(final int i, final int key) {
    queue[size] = i;
    inverseQueue[i] = size;
    keys[i] = key;
    size++;
    swim(inverseQueue[i]);
  }

  private void swim(int index) {
    while (index > 0) {
      final int parentIndex = (index - 1) / 2;
      if (keys[queue[index]] < keys[queue[parentIndex]]) {
        swap(index, parentIndex);
        index = parentIndex;
      } else {
        break;
      }
    }
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

  private void swap(int i, int j) {
    int temp = queue[i];
    queue[i] = queue[j];
    queue[j] = temp;
    inverseQueue[queue[i]] = i;
    inverseQueue[queue[j]] = j;
  }

  public void increaseKey(final int i, final int key) {
    keys[i] = key;
    sink(inverseQueue[i]);
  }
  
  public void decreaseKey(final int i, final int key) {
    keys[i] = key;
    swim(inverseQueue[i]);
  }
  
  public boolean contains(final int i) {
    return inverseQueue[i] != -1;
  }
  
  public int pop() {
    final int value = queue[0];
    delete(value);
    return value;
  }
  
  public void delete(final int i) {
    final int index = inverseQueue[i];
    swap(index, size - 1);
    size--;
    sink(index);
    assert inverseQueue[i] == size;
    assert queue[size] == i;
    queue[size] = -1;
    inverseQueue[i] = -1;
    keys[i] = -1;
  }

  public boolean isEmpty() {
    return size == 0;
  }
}
