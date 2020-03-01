package euler.prob101_200.prob115

import euler.traits.Util

/**
  * Created by Ricardo
  */
object Prob115 extends Util {

  def main(args: Array[String]): Unit = {

    val MAX = 500
    val m = 50

    val memory: Array[BigInt] = Array.ofDim[BigInt](MAX)
    for (index <- memory.indices) memory(index) = -1

    def calc(n: Int): BigInt = {
      if (memory(n) == -1) {
        memory(n) =
          if (n == 0)
            1
          else {
            var total = BigInt(0)
            for {
              size <- m to n
              if n - size >= 0
            } total += calc(math.max(0, n - size - 1))
            total += calc(n - 1)
            total
          }
      }
      memory(n)
    }

    time {
      Stream.from(m).map(n => (n, calc(n))).dropWhile(_._2 < 1000000).head._1
    }
//    println(memory.take(MAX).toList)

  }

}
