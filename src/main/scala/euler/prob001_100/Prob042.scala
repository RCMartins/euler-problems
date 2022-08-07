package euler.prob001_100

import euler.traits.UtilResult

/**
  * Created by Ricardo
  */
object Prob042 extends UtilResult {
  def calc: Long = {
    val list = readData("p042_words.txt").replace("\"", "").split(',').toList

    def tri(n: Int): LazyList[Int] = (n * (n + 1) / 2) #:: tri(n + 1)

    val triSet = tri(1).takeWhile(_ < 10000000).toSet

   list.count(word => triSet(word.map(_ - 'A' + 1).sum))
  }
}
