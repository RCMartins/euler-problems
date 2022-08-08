package euler.prob101_200.prob104

import euler.traits.Util

/**
  * Created by Ricardo
  */
object Prob104 extends Util {

  def main(args: Array[String]): Unit = {

    //    def fib(a: BigInt, b: BigInt): LazyList[BigInt] = {
    //      val c = a + b
    //      c #:: fib(b, c)
    //    }
    //
    //    def ALL_FIB: LazyList[BigInt] = BigInt(1) #:: BigInt(1) #:: fib(BigInt(1), BigInt(1))
    //
    //    for {
    //      (fib, n) <- ALL_FIB.zip(LazyList.from(1)).drop(240000)
    //      _ = if (n % 20000 == 0) println(n) else ()
    //      if fib.toString.take(9).sorted == "123456789"
    //      if fib.mod(1000000000).toString.sorted == "123456789"
    //    } {
    //      result(n, fib.toString.length)
    //      return
    //    }

    def testAll(n: Int, a: BigInt, b: BigInt): Unit = {
      val c = a + b
      if (n > 320000) {
        if (n % 20000 == 0)
          println(n)
        if (c.toString.take(9).sorted == "123456789" && c.mod(1000000000).toString.sorted == "123456789") {
          result((n, c)) //.toString.length)
          //329468
          System.exit(0)
        }
      }
      testAll(n + 1, b, c)
    }

    testAll(3, 1, 1)

  }

}
