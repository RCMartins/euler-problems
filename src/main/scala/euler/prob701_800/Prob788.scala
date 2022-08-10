package euler.prob701_800

import euler.traits.UtilResult

/** Created by Ricardo
  */
object Prob788 extends UtilResult {

  def calc: Long = {
    val targetN = 2022

    val module = 1000000007
    val moduleBigInt = BigInt(module)

    val cacheFactorial: IndexedSeq[BigInt] =
      (1 to targetN).scanLeft(BigInt(1)) { case (big, n) => big * n }

    val nineExp: IndexedSeq[BigInt] =
      (0 to targetN).map(BigInt(9).modPow(_, moduleBigInt))

    def comb(n: Int, r: Int): BigInt = {
      (cacheFactorial(n) / (cacheFactorial(r) * cacheFactorial(n - r)))
        .mod(moduleBigInt)
    }

    def calc(size: Int): BigInt =
      if (size <= 2)
        9
      else
        (size / 2 + 1 until size)
          .foldLeft(BigInt(1)) { case (acc, r) =>
            acc + comb(size, r) * nineExp(size - r)
          }
          .*(9)
          .mod(moduleBigInt)

    def D(n: Int): BigInt =
      if (n < 1)
        0
      else
        calc(n) + D(n - 1)

    D(targetN).mod(moduleBigInt).toLong

    // answer: 471745499
  }
}
