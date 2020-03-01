//package euler.prob001_100.prob98
//
//import euler.Util
//
//import scala.io.Source
//
///**
//  * Created by Ricardo
//  */
//object Prob98 extends Util {
//
//  def main(args: Array[String]): Unit = {
//
//    val file = Source.fromFile("data/p098_words.txt").getLines.mkString
//    val words =
//      file.split(",").map(_.tail.init)
//    val groups =
//      words.groupBy(_.toSeq.sorted.unwrap)
//          .filter(_._2.length > 1)
//          .view.mapValues(_.toSeq)
//          .toMap
//
//    val squaresSeq =
//      (1 to 100000).map(x => x * x)
//          .takeWhile(_ < 1000000000)
//          .sortBy(-_)
//    val squaresSet = squaresSeq.toSet
//    val squaresBySize = squaresSeq.map(digits).groupBy(_.size)
//
//    def find(values: List[Int]): Int = {
//???
//    }
//    println(squaresBySize.view.mapValues(_.length).mkString("\n"))
//
//   println(groups)
//
//    groups.map(_._2).collect {
//      x => x
//    }
//  }
//
//}
