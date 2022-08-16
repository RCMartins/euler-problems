package euler.prob701_800

import euler.traits.UtilResult

/** Created by Ricardo
  */
object Prob709 extends UtilResult {
  def calcSlowAndHeapException: Long = {

    val Mod = 1020202009
//    val Max = 24680
    val Max = 8

    val combArr: Array[Array[Int]] = Array.fill[Int](Max + 1, Max + 1)(-1)
    val arr: Array[Array[Int]] = Array.ofDim[Int](Max + 1, Max + 1)

    val factorial: Seq[BigInt] = BigInt(1) +: (2 to Max).scanLeft(BigInt(1))((b, v) => b * v)

    def comb(n: Int, r: Int): Int =
      if (n < r)
        0
      else {
        if (combArr(n)(r) == -1)
          combArr(n)(r) = ((factorial(n) / (factorial(r) * factorial(n - r))) % Mod).toInt
        combArr(n)(r)
      }

    def add(a: Int, b: Int): Int = (a + b) % Mod

    def mult(a: Int, b: Int): Int = ((a.toLong * b) % Mod).toInt

    arr(1)(1) = 1

    for (i <- 2 to Max) {
      for (j <- 1 until i if arr(i - 1)(j) > 0) {
        for (k <- 2 to j by 2)
          arr(i)(j - k + 1) = add(arr(i)(j - k + 1), mult(comb(j, k), arr(i - 1)(j)))
        arr(i)(j + 1) = arr(i - 1)(j)
      }
    }

    val min = Math.max(1, Max - 10)
    for (j <- min to Max) {
      for (i <- min to Max)
        print("%10d ".format(arr(i)(j)))
      println()
    }
    println()

    arr(Max).sum
  }

  override def calc: Long = {

    val Mod = 1020202009
//    val Max = 24680
    val Max = 1000

    val combArr: Array[Array[Int]] = Array.fill[Int](Max + 1, Max + 1)(-1)
    val arr: Array[Array[Int]] = Array.ofDim[Int](2, Max + 1)

    val factorial: Seq[BigInt] = BigInt(1) +: (2 to Max).scanLeft(BigInt(1))((b, v) => b * v)

    def comb(n: Int, r: Int): Int =
      if (n < r)
        0
      else {
        if (combArr(n)(r) == -1)
          combArr(n)(r) = ((factorial(n) / (factorial(r) * factorial(n - r))) % Mod).toInt
        combArr(n)(r)
      }

    def add(a: Int, b: Int): Int = (a + b) % Mod

    def mult(a: Int, b: Int): Int = ((a.toLong * b) % Mod).toInt

    arr(0)(1) = 1

    for (i <- 2 to Max) {
      for (j <- 1 until i if arr(0)(j) > 0) {
        for (k <- 2 to j by 2)
          arr(1)(j - k + 1) = add(arr(1)(j - k + 1), mult(comb(j, k), arr(0)(j)))
        arr(1)(j + 1) = arr(0)(j)
      }

      for (j <- 1 to i) {
        arr(0)(j) = arr(1)(j)
        arr(1)(j) = 0
      }
    }

    arr(0).foldLeft(0)((a, b) => add(a, b))
  }
}
