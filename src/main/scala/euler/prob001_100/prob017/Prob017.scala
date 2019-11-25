package euler.prob001_100.prob017

import euler.traits.UtilResult

/**
  * Created by Ricardo
  */
object Prob017 extends UtilResult {
  def calc: Long = {
    val n1_19 = Vector(
      "",
      "one",
      "two",
      "three",
      "four",
      "five",
      "six",
      "seven",
      "eight",
      "nine",
      "ten",
      "eleven",
      "twelve",
      "thirteen",
      "fourteen",
      "fifteen",
      "sixteen",
      "seventeen",
      "eighteen",
      "nineteen"
    )
    val n20_90 =
      Vector("", "", "twenty", "thirty", "forty", "fifty", "sixty", "seventy", "eighty", "ninety")

    val n100 = "hundred"
    val n1000 = "thousand"

    var total = 3 + n1000.length

    def add(n: Int): String = {
      if (n >= 100) {
        n1_19(n / 100) + n100 + (if (n % 100 == 0) "" else "and" + add(n % 100))
      } else if (n >= 20) {
        n20_90(n / 10) + (if (n % 10 == 0) "" else add(n % 10))
      } else {
        n1_19(n)
      }
    }

    for (n <- 1 to 999) {
      val v = add(n)
      total += v.length
    }

    total
  }
}
