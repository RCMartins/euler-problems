package euler.prob001_100.prob081

import euler.traits.UtilResult

/**
  * Created by Ricardo
  */
object Prob081 extends UtilResult {
  def calc: Long = {
    val SIZE = 80

    val arr = readData("p081_matrix.txt").split("\n").map(_.split(",").map(_.toInt).toArray)

    val sum = Array.ofDim[Int](SIZE, SIZE)

    sum(0)(0) = arr(0)(0)
    for (y <- 1 until SIZE) sum(0)(y) = arr(0)(y) + sum(0)(y - 1)
    for (x <- 1 until SIZE) sum(x)(0) = arr(x)(0) + sum(x - 1)(0)

    for {
      y <- 1 until SIZE
      x <- 1 until SIZE
    } sum(x)(y) = arr(x)(y) + math.min(sum(x - 1)(y), sum(x)(y - 1))

    sum(SIZE - 1)(SIZE - 1)
  }
}
