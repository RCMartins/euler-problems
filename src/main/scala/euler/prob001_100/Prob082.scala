package euler.prob001_100

import euler.traits.UtilResult

/**
  * Created by Ricardo
  */
object Prob082 extends UtilResult {
  def calc: Long = {
    val SIZE = 80

    val arr = Array.ofDim[Int](SIZE, SIZE)
    val data = readData("p082_matrix.txt").split("\n").map(_.split(",").map(_.toInt).toArray)
    for (x <- 0 until SIZE)
      for (y <- 0 until SIZE)
        arr(x)(y) = data(y)(x)

    val sum = Array.ofDim[Int](SIZE, SIZE)

    for (y <- 0 until SIZE)
      sum(0)(y) = arr(0)(y)

    for (x <- 1 until SIZE) {
      for (y <- 0 until SIZE)
        sum(x)(y) = sum(x - 1)(y) + arr(x)(y)
      for (y <- 1 until SIZE)
        sum(x)(y) = math.min(sum(x)(y), arr(x)(y) + sum(x)(y - 1))
      for (y <- SIZE - 2 to 0 by -1)
        sum(x)(y) = math.min(sum(x)(y), arr(x)(y) + sum(x)(y + 1))
    }

    sum(SIZE - 1).min
  }
}
