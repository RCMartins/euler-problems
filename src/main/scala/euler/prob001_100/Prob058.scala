package euler.prob001_100

import euler.traits.UtilResult

/**
  * Created by Ricardo
  */
object Prob058 extends UtilResult {
  override def calc: Long = {
    val DX = Vector(0, 1, 0, -1)
    val DY = Vector(-1, 0, 1, 0)

    var x, y = 0
    var dir = 1
    var size = 1
    var sizeIndex = 1
    var n = 1

    var total = 1
    var totalPrimes = 0
    while (totalPrimes == 0 || totalPrimes.toDouble / total >= 0.1) {
      x += DX(dir)
      y += DY(dir)
      sizeIndex -= 1
      n += 1
      if (math.abs(x) - math.abs(y) == 0) {
        if (testIsPrime(n))
          totalPrimes += 1
        total += 1
      }
      if (sizeIndex == 0) {
        dir = (dir + 3) % 4
        if (dir % 2 == 1)
          size += 1
        sizeIndex = size
      }
    }

    size
  }
}
