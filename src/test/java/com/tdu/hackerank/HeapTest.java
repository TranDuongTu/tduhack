package com.tdu.hackerank;

import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.Comparator;

import static org.assertj.core.api.Assertions.assertThat;

public class HeapTest {

  @Test
  public void testBuildHeap() throws Exception {
    final int[] heap = Helpers.generate(10000, 100);
    System.out.println(Arrays.toString(heap));
    Heap.buildMaxHeap(heap);
    System.out.println(Arrays.toString(heap));
    Assert.assertEquals(Helpers.isMaxHeap(heap), true);
  }

  @Test
  public void testHeapSort() throws Exception {
    final int[] heap = Helpers.generate(100000, 100);
    Heap.heapSort(heap);
    assertThat(heap).isSortedAccordingTo(Comparator.comparingInt(x -> x));
  }

  @Test
  public void testMaxHeap() throws Exception {
    Assert.assertEquals(Helpers.isMaxHeap(new int[] {4, 3, 2, 1}), true);
    Assert.assertEquals(Helpers.isMaxHeap(new int[] {1, 2, 3, 4}), false);
  }

}
