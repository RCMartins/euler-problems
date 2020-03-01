package euler.prob101_200.prob117

import euler.traits.Util

/**
  * Created by Ricardo
  */
object Prob117 extends Util {

  def main(args: Array[String]): Unit = {

    val memory: Array[BigInt] = Array.ofDim[BigInt](100)
    for (index <- memory.indices) memory(index) = -1

    def calc(n: Int): BigInt = {
      if (memory(n) == -1) {
        memory(n) =
          if (n == 0)
            1
          else {
            var total = BigInt(0)
            for {
              size <- 1 to 4
              if n - size >= 0
            } total += calc(n - size)
            total
          }
      }
      memory(n)
    }

    time(calc(50))
  }

}
