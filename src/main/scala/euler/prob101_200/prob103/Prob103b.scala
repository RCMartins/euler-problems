package euler.prob101_200.prob103

import euler.traits.Util

/**
  * Created by Ricardo
  */
object Prob103b extends Util {

  def main(args: Array[String]): Unit = {

    def isOptimum(set: List[Int]): Boolean = {
      {
        val vec = set.sorted.toVector
        val maxT = (vec.size + 1) / 2
        (2 to maxT).forall(size => vec.take(size).sum > vec.takeRight(size - 1).sum)
      } && {
        val allSubsets = set.toSet.subsets().map(_.sum).toList
        allSubsets.distinct.size == allSubsets.size
      }
    }

    val middle = 20
    val sol6 = List(11, 18, 19, 20, 22, 25)

    val near7 = middle :: sol6.map(_ + middle)

    val Max = 4

    def loop(initialList: List[Int]): Stream[List[Int]] = {
      def aux(list: List[Int]): Stream[List[Int]] = list match {
        case Nil => Stream(Nil)
        case x :: xs =>
          (-Max to Max).toStream.flatMap { delta =>
            aux(xs).flatMap(l => {
              val y = x + delta
              if (l.contains(y))
                Nil
              else
                List(y :: l)
            })
          }
      }

      aux(initialList)
    }

    println(near7)
    time(loop(near7).sortBy(_.sum).dropWhile(l => !isOptimum(l)).head)

  }

}
