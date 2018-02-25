package com.tdu.tduhack.scala

object Sorts {
  def insertionSort(lst: List[Int]): List[Int] = {
    def insert(x: Int, sortedLst: List[Int]): List[Int] = sortedLst match {
      case e :: tail if e < x => e :: insert(x, tail)
      case _ => x :: sortedLst
    }

    if (lst.isEmpty) Nil else insert(lst.head, insertionSort(lst.tail))
  }
}
