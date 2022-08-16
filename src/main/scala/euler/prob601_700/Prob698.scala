package euler.prob601_700

import euler.traits.UtilResult

import scala.collection.mutable

/** Created by Ricardo
  */
object Prob698 extends UtilResult {

  def bruteForce1: LazyList[Long] = {
    val validChars = "[123]+".r

    def next123(n: Long): LazyList[Long] = {
      val nStr = n.toString
      if (
        validChars.matches(nStr) &&
        nStr.count(_ == '1') <= 3 &&
        nStr.count(_ == '2') <= 3 &&
        nStr.count(_ == '3') <= 3
      )
        n #:: next123(n + 1)
      else
        next123(n + 1)
    }

    1L #:: next123(2)
  }

  def bruteForce2: LazyList[String] = {
    val valid123: mutable.Set[String] = mutable.Set("0", "1", "2", "3")

    lazy val result = "1" #:: "2" #:: "3" #:: next123(2)

    def next123(length: Int): LazyList[String] = {
      def loop(c1: Int, c2: Int, c3: Int): LazyList[String] = {}

      loop()
    }

    result
  }

  override def calc: Long = {
    val bruteData = bruteForce1

    def p(n: Int): Unit =
      println(bruteData.dropWhile(_.toString.length < n).takeWhile(_.toString.length == n).size)

    p(1)
    p(2)
    p(3)
    p(4)
    p(5)
    p(6)
    p(7)
//    p(8)

    println(bruteData(1000 - 1))

    val target = 111111111111222333L

    0
  }

}
