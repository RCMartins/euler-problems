package euler.prob001_100

import euler.traits.UtilResult

/**
  * Created by Ricardo
  */
object Prob009 extends UtilResult {
  override def calc: Long = {
    (for {
      a <- 1 to 333
      b <- a + 1 to 500
      c <- b + 1 to 997
      if a + b + c == 1000 && a * a + b * b == c * c
    } yield {
      a * b * c
    }).head
  }
}
