package euler.prob001_100

import euler.traits.UtilResult

import scala.annotation.tailrec

/** Created by Ricardo
  */
object Prob037 extends UtilResult {
  def calc: Long = {
    var all = List(2L, 3L, 5L, 7L)
    all = all.flatMap(a => all.map(b => a * 10 + b))
    var total = Set[Long]()
    total ++= all.filter(testIsPrime(_))

    @unchecked
    @tailrec
    def primeToLeft(n: List[Char]): Boolean = {
      n match {
        case List(c) => List(2, 3, 5, 7).contains(c - '0')
        case _ :: cx => testIsPrime(cx.mkString.toLong) && primeToLeft(cx)
      }
    }

    @tailrec
    def primeToRight(n: List[Char]): Boolean = {
      n match {
        case List(c) => List(2, 3, 5, 7).contains(c - '0')
        case _ =>
          val init = n.init
          testIsPrime(init.mkString.toLong) && primeToRight(init)
      }
    }

    while (total.size < 11) {
      all = all.flatMap(prime =>
        (1 to 9).flatMap(n => {
          val primeStr = prime.toString
          val init = primeStr.init + n
          if (testIsPrime(init.toLong))
            List((init + primeStr.last).toLong)
          else
            Nil
        })
      )
      total ++= all.filter(n =>
        testIsPrime(n) && primeToLeft(n.toString.toList) && primeToRight(n.toString.toList)
      )
    }

    total.sum
  }
}
