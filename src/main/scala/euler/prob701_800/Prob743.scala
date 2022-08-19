package euler.prob701_800

import euler.traits.UtilResult

import scala.annotation.tailrec

/** Created by Ricardo
  */
object Prob743 extends UtilResult {

  private def bruteForce(k: Int, n: Int): Int = {
    val k2 = k * 2
    val n2 = n * 2
    val arr: Array[Int] = Array.fill[Int](n2)(-1)

//    @tailrec
//    def checker(i: Int): Int = {
//      if (i == 0)
//        1
//      else {
//        val newI = i - 2
//        val newCount: Int =
//          k - arr(i) - arr(i - 1) + arr(i - k2) + arr(i - k2 - 1)
//        if (newCount == k)
//          checker(newI)
//        else
//          0
//      }
//    }

    def loop(index: Int, count: Int): Int = {
      @inline
      def usingCount(newCount: Int): Int = {
        arr(index) = 1
        val res1 = loop(index + 1, newCount + 1)
        arr(index) = 0
        val res2 = loop(index + 1, newCount)
        res1 + res2
      }

      if (index >= k2 && index % 2 == 0) {
        if (index == n2)
          if (count == k)
            1
          else
            0
        else if (count == k) {
          println((arr.toList.take(index).takeRight(k2), count))
          val arrList = arr.toList.take(index).mkString(",")
          val before = count - arr(index - k2)
          usingCount(before)
        } else
          0
      } else
        usingCount(count)
    }

    loop(index = 0, count = 0)
  }

  override def calc: Long = {
    // A(3,3) = 20

    println(bruteForce(k = 3, n = 3))

    0
  }
}
