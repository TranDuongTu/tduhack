package com.tduhack.scala

import java.util.NoSuchElementException

object ListExercises {
  
  def findLast(lst: List[Int]): Int = lst match {
    case head :: Nil => head
    case _ :: rest => findLast(rest)
    case _ => throw new NoSuchElementException
  }

  def findKth(lst: List[Int], i: Int): Int =
    if (i < 0) throw new NoSuchElementException
    else lst match {
      case head :: rest => if (i == 0) head else findKth(rest, i - 1)
      case _ => throw new NoSuchElementException
    }

  def count(list: List[Int]): Int = list match {
    case _ :: rest => 1 + count(rest)
    case _ => 0
  }

  def reverse(list: List[Int]): List[Int] = list match {
    case head :: rest => reverse(rest) ::: List(head)
    case Nil => Nil
  }

  def isPalindrome(list: List[Int]): Boolean = list == reverse(list)

  def flatten(list: List[Any]): List[Any] = list flatMap {
    case listElement: List[_] => flatten(listElement)
    case element => List(element)
  }

  def compress(list: List[Int]): List[Int] = {
    def compress(list: List[Int], d: Int): List[Int] = list match {
      case head :: rest => if (head == d) compress(rest, d) else head :: compress(rest, head)
      case _ => Nil
    }

    list match {
      case head :: rest => head :: compress(rest, head)
      case _ => Nil
    }
  }

  def packDuplicates(list: List[Int]): List[List[Int]] =
    list.foldRight[List[List[Int]]](List())((e, accu) => accu match {
      case (head :: rest) :: accuRest if head == e => (e :: head :: rest) :: accuRest
      case _ => List(e) :: accu
    })
}
