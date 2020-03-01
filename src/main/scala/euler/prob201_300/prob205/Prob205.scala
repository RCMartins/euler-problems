package euler.prob201_300.prob205

import euler.traits.Util

import scala.util.Random

/**
  * Created by Ricardo
  */
object Prob205 extends Util {

  def main(args: Array[String]): Unit = {

    var wins1 = 0L
    var tries = 0L

    @inline
    def player1(): Int = {
      var sum = 0
      for (_ <- 1 to 9)
        sum += Random.nextInt(4) + 1
      sum
    }

    @inline
    def player2() = {
      var sum = 0
      for (_ <- 1 to 6)
        sum += Random.nextInt(6) + 1
      sum
    }

    def test(max: Int): Double = {
      while (tries < max) {
        val p1 = player1()
        val p2 = player2()

        if (p1 > p2)
          wins1 += 1

        tries += 1
      }

      wins1.toDouble / tries
    }

    time((test(Int.MaxValue), wins1, tries))

    // 1e8 =>     0.57311167
    // 1e8 =>     0.57312211
    // 1e9 =>     0.573148537
    // Int.Max => 0.5731642737859693
    //
    // (0.5731642, 0.5731643) => WRONG
  }
}
