package euler.prob001_100

import euler.traits.UtilResult

/** Created by Ricardo
  */
object Prob032 extends UtilResult {
  override def calc: Long = {
    val isPandigital = (1 to 9).permutations.map(_.mkString).toSet

    def isPandigitalProduct(number: Int): Boolean = {
      for {
        a <- 2 until math.sqrt(number).toInt + 1
        if number % a == 0
        b = number / a
        if isPandigital(number.toString + a.toString + b.toString)
      } {
        return true
      }
      false
    }

    val v = for {
      size <- 3 to 7
      comb <- (1 to 9).combinations(size)
      perm <- comb.permutations
      number = perm.mkString.toInt
      if isPandigitalProduct(number)
    } yield {
      number
    }

    v.sum
  }
}
