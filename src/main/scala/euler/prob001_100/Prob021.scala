package euler.prob001_100

import euler.traits.UtilResult

/**
  * Created by Ricardo
  */
object Prob021 extends UtilResult {
  def calc: Long = {
    val vector: Vector[(Long, Int)] =
      (1 to 10000).map(uniqueFactors(_).init.sum).toVector.zip(1 to 10000)

    vector
      .take(10000)
      .foldLeft(0L)(
        (acc, pair) =>
          pair match {
            case (sum, index) =>
              if (sum != index && sum > 0 && sum < vector.length && vector(sum.toInt - 1)._1.toInt == index)
                sum + acc
              else
                acc
          }
      )
  }
}
