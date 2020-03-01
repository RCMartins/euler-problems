package euler.prob501_600.prob598

import euler.traits.Util

/**
  * Created by Ricardo
  */
object Prob598 extends Util {

  def main(args: Array[String]): Unit = {

    val facts = factors(BigInt(100).factorial).map { case (a, b) => (a.toInt, b) }

    println(facts)
    println(facts.size)
    println(facts.map(_._2 + 1).map(_.toLong).product)

    def loop(facts: List[Int]): Stream[List[Int]] = {
      facts match {
        case x :: xs =>
          (0 to x).toStream.flatMap { n =>
            loop(xs).map(n :: _)
          }
        case Nil =>
          Stream(Nil)
      }
    }

    //    println(loop(List(1, 2, 3)).take(510).mkString("\n"))
    println(loop(facts.unzip._2).tail.tail.tail.size)

  }

}
