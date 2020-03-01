package euler.prob201_300.prob231

import euler.traits.Util

/**
  * Created by Ricardo
  */
object Prob231 extends Util {

  def main(args: Array[String]): Unit = {

    /*
    20000000
            C
             15000000
     */

    /*
        n!
     --------
     k!(n-k)!
     */

    timeOnly {

//      (15M to 20M **)
//      --------
//      5000000!

//      def get(n: Int, k: Int): BigInt = {
//        ((BigInt(k) + 1) to BigInt(n)).product / (BigInt(1) to BigInt(n-k)).product
//      }
//
//      get(2000000,1500000)

      (1 to 5000000).map(n => factors(n))
    }
  }

}
