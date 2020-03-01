package euler.prob101_200.prob114

import euler.traits.Util

/**
  * Created by Ricardo
  */
object Prob114 extends Util {

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
              size <- 3 to n
              if n - size  >= 0
            } total += calc(math.max(0, n - size - 1))
            total += calc(n - 1)
            total
          }
      }
      memory(n)
    }

    time(calc(50))
    println(memory.toList)
    //16475640049

  }

}
