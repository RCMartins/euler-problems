package euler.prob001_100.prob093

import euler.traits.UtilResult
//import spire.math.Rational

/**
  * Created by Ricardo
  */
object Prob093 extends UtilResult {
  def calc: Long = {
//    def num4(n1: Rational, n2: Rational, n3: Rational, n4: Rational): List[Rational] = {
//      List(
//        num2(n1, n2).flatMap(num3(_, n3, n4)),
//        num2(n1, n3).flatMap(num3(_, n2, n4)),
//        num2(n1, n4).flatMap(num3(_, n2, n3)),
//        num2(n2, n3).flatMap(num3(_, n1, n4)),
//        num2(n2, n4).flatMap(num3(_, n1, n3)),
//        num2(n3, n4).flatMap(num3(_, n1, n2))
//      ).flatten
//    }
//
//    def num3(n1: Rational, n2: Rational, n3: Rational): List[Rational] = {
//      List(num2(n1, n2).flatMap(num2(n3, _)), num2(n1, n3).flatMap(num2(n2, _)), num2(n2, n3).flatMap(num2(n1, _))).flatten
//    }
//
//    def num2(n1: Rational, n2: Rational): List[Rational] = {
//      operations.flatMap(op => List(op(n1, n2), op(n2, n1)).flatten).filter(_ > 0)
//    }
//
//    def operations: List[(Rational, Rational) => List[Rational]] =
//      List((a, b) => List(a + b), (a, b) => List(a - b), (a, b) => List(a * b), (a, b) => if (b == 0) Nil else List(a / b))
//
//    def testConsecutive(list: List[Rational]): Int = {
//      if (list.head == 1)
//        list.zip(list.tail).takeWhile(pair => pair._2 - pair._1 == 1).size + 1
//      else
//        0
//    }
//
//    val v = for {
//      a <- 1 to 6
//      b <- a + 1 to 7
//      c <- b + 1 to 8
//      d <- c + 1 to 9
//      list = num4(a, b, c, d).filter(_.isWhole).distinct.sorted
//    } yield (testConsecutive(list), "" + a + "" + b + "" + c + "" + d)
//
////    println(num4(1, 2, 5, 8).filter(_.isWhole).distinct.sorted)
//    println(v.sortBy(-_._1))
//
//    result(v.sortBy(-_._1).head._2)

    ???
  }
}
