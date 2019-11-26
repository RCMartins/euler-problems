package euler.prob001_100

import euler.traits.UtilResult

/**
  * Created by Ricardo
  */
object Prob022 extends UtilResult {
  def calc: Long = {
    val data = readData("p022_names.txt").replace("\"", "").split(',').toList

    data.sorted.zipWithIndex.foldLeft(0L)(
      (sum, pair) =>
        pair match {
          case (name, index) => name.map(_ - 'A' + 1).sum * (index + 1) + sum
        }
    )
  }
}
