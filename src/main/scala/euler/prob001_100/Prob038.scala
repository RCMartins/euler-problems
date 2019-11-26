package euler.prob001_100

import euler.traits.UtilResult

/**
  * Created by Ricardo
  */
object Prob038 extends UtilResult {
  def calc: Long = {
    val v = for {
      number <- 2 to 10000
      n <- 2 to 9
      multiples = (1 to n).map(_ * number).mkString
      if multiples.length == 9 && multiples.sorted == "123456789"
    } yield multiples

    v.map(_.toInt).max
  }
}
