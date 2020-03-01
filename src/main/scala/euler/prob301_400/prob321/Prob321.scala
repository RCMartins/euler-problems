package euler.prob301_400.prob321

import euler.traits.Util

/**
  * Created by Ricardo
  */
object Prob321 extends Util {

  def main(args: Array[String]): Unit = {

    //    def a = Stream.from(1).map(_.toLong * 2 + 3)
    //
    //    def b = a.scanLeft(3L)(_ + _)
    //
    //    println(b.take(40).toList)
    //
    //    var i = 0
    //
    //    val v = b.filter(n => {
    //      i += 1
    //      if (i % 1000000 == 0)
    //        println(n)
    //      isPerfectSquare(n * 8 + 1)
    //    })
    //
    //    result(v.take(40).sum)

    var i: BigInt = 0

    var v = Vector[BigInt]()
    var n: BigInt = 3
    var sum: BigInt = 3

    while (v.size < 20) {
      i += 1
      //      if (i % 50000000 == 0)
      //        println(n)

      if (isPerfectSquare(n * 8 + 1)) {
        v = v :+ i
        println(f"value(${v.size}%2d,$i%8d): $n%15d")
      }
      sum += 2
      n += sum
    }

    //    result(v.take(4).sum)
    result(v.sum)
  }

}
