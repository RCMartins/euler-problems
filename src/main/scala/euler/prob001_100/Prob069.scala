package euler.prob001_100

import euler.traits.UtilResult

/** Created by Ricardo
  */
object Prob069 extends UtilResult {
  override def calc: Long = {
    val MAX = 1000000

    val all: Array[Set[Int]] =
      (Set[Int]() +: (1 to MAX).map(n => uniqueFactors(n).map(_.toInt).toSet)).toArray

    println("Done calculating factors")

    var max = 5.213541666666667
    var maxNumber = 120120
    val initial = 376200

    // progress so far (340000): 5.213541666666667 - 120120

    for (number <- initial + 1 to MAX) {
      var count = 0
      for {
        rp <- 1 until number
        if !(number % 2 == 0 && rp % 2 == 0) && all(number).intersect(all(rp)).size == 1
      } count += 1
      val q = number.toDouble / count
      if (q > max) {
        max = q
        maxNumber = number
        println((max, maxNumber))
      }
      if (number % 100 == 0)
        println(s"progress so far ($number): $max - $maxNumber")
    }

    println((max, maxNumber))

    ???
  }
}
