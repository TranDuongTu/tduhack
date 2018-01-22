package com.tdu.hackerank;

import org.junit.Test;

import java.util.List;

import static com.tdu.hackerank.IntervalTree.Interval;
import static org.assertj.core.api.Assertions.assertThat;

public class IntervalTreeTest {

  @Test
  public void testIntervals() throws Exception {
    final Interval i1 = new Interval(1, 2);
    final Interval i2 = new Interval(5, 10);
    assertThat(i2.length()).isEqualTo(5);
    assertThat(i1.union(i2)).isNull();
    assertThat(i1.intersection(i2)).isNull();
    
    final Interval i3 = new Interval(4, 6);
    final Interval i4 = new Interval(8, 15);
    final Interval i5 = new Interval(6, 9);
    assertThat(i2.isOverlap(i3)).isTrue();
    assertThat(i2.intersection(i3)).isEqualByComparingTo(new Interval(5, 6));
    assertThat(i2.union(i3)).isEqualByComparingTo(new Interval(4, 10));
    assertThat(i2.intersection(i4)).isEqualByComparingTo(new Interval(8, 10));
    assertThat(i2.union(i4)).isEqualByComparingTo(new Interval(5, 15));
    assertThat(i2.intersection(i5)).isEqualByComparingTo(i5);
    assertThat(i2.union(i5)).isEqualByComparingTo(new Interval(5, 10));
    
    final Interval r1 = new Interval(1, 10)
        .intersection(new Interval(5, 100))
        .intersection(new Interval(6, 9))
        .union(new Interval(9, 10))
        .union(new Interval(5, 6))
        .intersection(new Interval(5, 10));
    assertThat(r1).isEqualByComparingTo(new Interval(5, 10));
  }

  @Test
  public void testFindOverlaps() throws Exception {
    final Interval i1 = new Interval(1, 2);
    final Interval i2 = new Interval(2, 5);
    final Interval i3 = new Interval(10, 15);
    final Interval i4 = new Interval(12, 20);
    final Interval i5 = new Interval(14, 16);
    final Interval i6 = new Interval(8, 11);
    
    final IntervalTree intervalTree = new IntervalTree();
    assertThat(intervalTree.isEmpty()).isTrue();
    intervalTree.put(i1);
    intervalTree.put(i2);
    intervalTree.put(i3);
    intervalTree.put(i4);
    intervalTree.put(i5);
    intervalTree.put(i6);
    assertThat(intervalTree.isEmpty()).isFalse();
    List<Interval> overlaps = intervalTree.findOverlaps(new Interval(1, 6));
    assertThat(overlaps).hasSize(2).contains(i1, i2);
    overlaps = intervalTree.findOverlaps(new Interval(7, 13));
    assertThat(overlaps).hasSize(3).contains(i3, i4, i6);
    overlaps = intervalTree.findOverlaps(new Interval(12, 20));
    assertThat(overlaps).hasSize(3).contains(i3, i4, i5);
  }
}