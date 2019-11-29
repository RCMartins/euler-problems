package euler.prob001_100

import euler.traits.UtilResult

/**
  * Created by Ricardo
  */
object Prob054 extends UtilResult {
  def calc: Long = {
    def toValue(c: Char): Int = c match {
      case 'J' => 11
      case 'Q' => 12
      case 'K' => 13
      case 'A' => 14
      case 'T' => 10
      case _   => c - '0'
    }

    val data =
      readData("p054_poker.txt")
        .split("\n")
        .toList
        .map(
          _.split(' ').toList.map { str =>
            (toValue(str(0)), str(1))
          }
        )

    def maxCard(list: List[Int]): Int = list.max

    def getSpecificCount(list: List[Int], n: Int): Int = {
      list
        .groupBy(identity)
        .map { case (a, b) => (b.size, a) }
        .apply(n)
    }

    def getType(list: List[(Int, Char)]): (Int, Int) = {
      val List((a0, a1), (b0, b1), (c0, c1), (d0, d1), (e0, e1)) = list
      val values = List(a0, b0, c0, d0, e0)
      val valuesSet = values.toSet
      val naipes = List(a1, b1, c1, d1, e1)

      def isConsecutive: Boolean = {
        values.toSet.size == 5 && {
          val v = values.sorted
          v.zip(v.tail).forall { case (a, b) => b - a == 1 }
        }
      }

      def hasSpecificCount(value: Int): Boolean = {
        valuesSet.exists(n => values.count(_ == n) == value)
      }

      //Royal Flush: Ten, Jack, Queen, King, Ace, in same suit.
      if (naipes.toSet.size == 1 && isConsecutive && maxCard(values) == 14) { // not necessary for the given data set
        println("Royal Flush")
        (1100, maxCard(values))
      }
      //Straight Flush: All cards are consecutive values of same suit.
      else if (naipes.toSet.size == 1 && isConsecutive) { // not necessary for the given data set
        println("Straight Flush")
        (1000, maxCard(values))
      }
      //Four of a Kind: Four cards of the same value.
      else if (valuesSet.size == 2 && hasSpecificCount(4)) {
        println("four")
        (900, getSpecificCount(values, 4))
      }
      // Full House: Three of a kind and a pair.
      else if (valuesSet.size == 2 && hasSpecificCount(3) && hasSpecificCount(2)) {
        println("house " + list + " " + maxCard(values))
        (800, maxCard(values))
      }
      // Flush: All cards of the same suit.
      else if (naipes.toSet.size == 1) {
        println("flush " + list + " " + maxCard(values))
        (700, maxCard(values))
      }
      // Straight: All cards are consecutive values.
      else if (isConsecutive) {
        println("Straight " + list + " " + maxCard(values))
        (600, maxCard(values))
      }
      // Three of a Kind: Three cards of the same value.
      else if (hasSpecificCount(3)) {
        println("Three " + list)
        (500, getSpecificCount(values, 3))
      } else {
        // Two Pairs: Two different pairs.
        val v = list
          .groupBy(_._1)
          .toList
          .map { case (_, b) => (b.size, b.head) }
          .sortBy(_._1)
        if (valuesSet.size == 3 && v.map(_._1) == List(1, 2, 2)) {
          println("Two Pairs " + list)
          (400, maxCard(v.tail.map(_._2._1)))
        }
        // One Pair: Two cards of the same value.
        else if (hasSpecificCount(2)) {
          (300, getSpecificCount(values, 2))
        }
        // High Card: Highest value card.
        else {
          (200, maxCard(values))
        }
      }
    }

    data
      .map { list =>
        val t1 = getType(list.slice(0, 5))
        val t2 = getType(list.slice(5, 10))

        if (t1._1 > t2._1)
          true
        else if (t1._1 == t2._1) {
          if (t1._2 > t2._2)
            true
          else if (t1._2 == t2._2) {
            // TODO: There are some edge cases here that are not implemented...
            false
          } else {
            false
          }
        } else
          false
      }
      .count(identity)
  }
}
