package euler.prob001_100

import euler.traits.UtilResult

/**
  * Created by Ricardo
  */
object Prob066 extends UtilResult {
  def calc: Long = {
    val v = for {
      d <- (2 to 50).filter(n => !math.sqrt(n).isWhole)
      x <- LazyList.from(1)
      if (x * x - 1) % d == 0
      if math.sqrt((x * x - 1).toDouble / d).isWhole
    } yield x

    println(v)

    ???
  }
}
