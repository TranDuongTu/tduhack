package com.tduhack.scala

import org.scalatest.FunSuite

class SortsTest extends FunSuite {
  test("Insertion sort") {
    assertResult(List(1, 2, 3, 4, 5, 6, 7, 8, 9, 10))(Sorts.insertionSort(List(5, 6, 7, 4, 3, 1, 2, 8, 10, 9)))
  }
}
