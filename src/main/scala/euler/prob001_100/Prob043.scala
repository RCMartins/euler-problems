package euler.prob001_100

import euler.traits.UtilResult

/**
  * Created by Ricardo
  */
object Prob043 extends UtilResult {
  def calc: Long = {
    val v = for {
      n1 <- 0 to 9
      n2 <- 0 to 9
      n3 <- 0 to 9
      n4 <- 0 to 8 by 2
      if (n2 * 100 + n3 * 10 + n4) % 2 == 0
      n5 <- 0 to 9
      if (n3 * 100 + n4 * 10 + n5) % 3 == 0
      n6 <- 0 to 9
      if (n4 * 100 + n5 * 10 + n6) % 5 == 0
      n7 <- 0 to 9
      if (n5 * 100 + n6 * 10 + n7) % 7 == 0
      n8 <- 0 to 9
      if (n6 * 100 + n7 * 10 + n8) % 11 == 0
      n9 <- 0 to 9
      if (n7 * 100 + n8 * 10 + n9) % 13 == 0
      n10 <- 0 to 9
      if (n8 * 100 + n9 * 10 + n10) % 17 == 0
      l = List(n1, n2, n3, n4, n5, n6, n7, n8, n9, n10)
      if l.toSet.size == 10
    } yield l.map(_.toString).mkString.toLong

    v.sum
  }
}
