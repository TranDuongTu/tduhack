package com.tduhack.scala

import org.scalatest.FunSuite

class ListExercisesTest extends FunSuite {
  test("Find last element") {
    assertThrows[NoSuchElementException](ListExercises.findLast(Nil))
    assertThrows[NoSuchElementException](ListExercises.findLast(List()))
    assertResult(3)(ListExercises.findLast(List(1, 2, 3)))
    assertResult(4)(ListExercises.findLast(List(1, 2, 3, 4)))
  }

  test("Find kth element") {
    assertThrows[NoSuchElementException](ListExercises.findKth(Nil, 0))
    assertThrows[NoSuchElementException](ListExercises.findKth(List(), 0))
    assertResult(2)(ListExercises.findKth(List(8, 2, 3, 1), 1))
    assertResult(8)(ListExercises.findKth(List(8, 2, 3, 1), 0))
    assertThrows[NoSuchElementException](ListExercises.findKth(List(8, 2, 3, 1), 4))

    assertResult(1)(ListExercises.findKth(List(8, 2, 3, 1, 9, 10), 3))
    assertThrows[NoSuchElementException](ListExercises.findKth(List(8, 2, 3, 1, 9, 10), -1))
  }

  test("List count") {
    assertResult(4)(ListExercises.count(List(8, 2, 3, 1)))
    assertResult(5)(ListExercises.count(List(8, 2, 3, 1, 2)))
    assertResult(6)(ListExercises.count(List(8, 2, 3, 1, 3, 4)))
    assertResult(0)(ListExercises.count(Nil))
    assertResult(0)(ListExercises.count(List()))
  }

  test("List reverse") {
    assertResult(List())(ListExercises.reverse(List()))
    assertResult(List(1))(ListExercises.reverse(List(1)))
    assertResult(List(1, 7, 6, 4))(ListExercises.reverse(List(4, 6, 7, 1)))
    assertResult(List(2, 1, 7, 6, 4))(ListExercises.reverse(List(4, 6, 7, 1, 2)))
  }

  test("Is palindrome") {
    assertResult(true)(ListExercises.isPalindrome(List(1, 2, 3, 2, 1)))
    assertResult(false)(ListExercises.isPalindrome(List(1, 2, 3, 2)))
    assertResult(true)(ListExercises.isPalindrome(List(2, 3, 2)))
    assertResult(true)(ListExercises.isPalindrome(List(2)))
    assertResult(true)(ListExercises.isPalindrome(Nil))
  }

  test("Flatten list") {
    assertResult(Nil)(ListExercises.flatten(List()))
    assertResult(Nil)(ListExercises.flatten(List(List(), Nil, List())))
    assertResult(List(1, 2, 3, 4))(ListExercises.flatten(List(List(1), Nil, List(2, 3), List(4))))
    assertResult(List(1, 2, 3, 4, 5, 6, 7, 8))(ListExercises.flatten(List(List(1), Nil, List(2, 3), List(4), List(5, List(6, 7, List(8))))))
  }

  test("Compress duplicates") {
    assertResult(Nil)(ListExercises.compress(List()))
    assertResult(List(1, 2, 3))(ListExercises.compress(List(1, 2, 2, 2, 3, 3, 3)))
    assertResult(List(1, 2, 3, 6, 7))(ListExercises.compress(List(1, 2, 2, 2, 3, 3, 3, 6, 6, 7, 7)))
    assertResult(List(1))(ListExercises.compress(List(1, 1, 1, 1, 1, 1, 1)))
  }

  test("Pack duplicates") {
    assertResult(Nil)(ListExercises.packDuplicates(List()))
    assertResult(List(List(1, 1), List(2), List(3), List(4, 4, 4), List(5, 5)))(ListExercises.packDuplicates(List(1, 1, 2, 3, 4, 4, 4, 5, 5)))
  }
}
