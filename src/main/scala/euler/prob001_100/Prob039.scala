package euler.prob001_100

import euler.traits.UtilResult

/**
  * Created by Ricardo
  */
object Prob039 extends UtilResult {
  override def calc: Long = {
    val v = for {
      p <- 3 to 1000
      a <- 1 to p
      b <- a + 1 to p
      ab = a * a + b * b
      c = math.sqrt(ab)
      if c == c.toInt && a + b + c == p
    } yield a + b + c.toInt

    v.groupBy(n => n)
      .toList
      .map { case (number, seq) => (number, seq.length) }
      .sortBy(_._2)
      .reverse
      .head
      ._1
  }
}
