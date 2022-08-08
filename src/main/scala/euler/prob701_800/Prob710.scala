package euler.prob701_800

import euler.traits.UtilResult

/**
  * Created by Ricardo
  */
object Prob710 extends UtilResult {

  def calc: Long = {

    def calcSeqs(target: Int): Seq[Seq[Int]] = {

      def loop(total: Int, seq: Seq[Int]): Seq[Seq[Int]] = {
        if (total == target)
          Seq(seq)
        else
          (1 to target - total).flatMap { n =>
            loop(total + n, seq :+ n)
          }
      }

      loop(0, Seq.empty)
    }

//    println(calcSeqs(1).mkString("\n"))
//    println
//    println(calcSeqs(2).mkString("\n"))
//    println
//    println(calcSeqs(3).mkString("\n"))
//    println
//    println(calcSeqs(4).mkString("\n"))
//    println
//    println(calcSeqs(5).mkString("\n"))

    for (i <- 2 to 18) {
      println(
        List(
          f"$i%2d:",
          f"${calcSeqs(i).size}%6d",
          f"${calcSeqs(i).count(_.contains(2))}%6d",
          f"${calcSeqs(i).size - calcSeqs(i).count(_.contains(2))}%6d",
          f"${calcSeqs(i).count(_.contains(2)) - calcSeqs(i - 1).count(_.contains(2)) * 2}%6d",
          f"${calcSeqs(i - 2).size - calcSeqs(i).count(_.contains(2))}%6d"
        ).mkString("  ")
      )
    }

    0
  }
}
