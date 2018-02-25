package com.tdu.tduhack.dsa.algorithms;

import com.tdu.tduhack.dsa.Helpers;
import com.tdu.tduhack.dsa.Problem;
import com.tdu.tduhack.dsa.algorithms.Heap;
import org.junit.Assert;
import org.junit.Test;

import java.util.Comparator;

import static org.assertj.core.api.Assertions.assertThat;

@Problem(name = "Heap and Heap sort", level = 3)
public class HeapTest {

  @Test
  public void testBuildHeap() throws Exception {
    final int[] heap = Helpers.generate(10000, 100);
    Heap.buildMaxHeap(heap);
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
