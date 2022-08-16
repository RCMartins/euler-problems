package euler.prob001_100

import euler.traits.UtilResult

/**
  * Created by Ricardo
  */
object Prob033 extends UtilResult {
  override def calc: Long = {
    val v = for {
      a <- 10 to 99
      b <- 10 to 99
      if a < b
      value = a.toDouble / b.toDouble
      if a.toString.exists(
        c => b.toString.contains(c) && (remove1Digit(a, c).toDouble / remove1Digit(b, c)) == value
      )
      if !(a.toString.endsWith("0") && b.toString.endsWith("0"))
    } yield new RacValue(a, b)

    v.fold(new RacValue(1, 1))(_ * _).get._2
  }
}
