package euler.prob101_200.prob118

import euler.traits.Util

/**
  * Created by Ricardo
  */
object Prob118 extends Util {

  def main(args: Array[String]): Unit = {

//    time {
//      val primes = (1 to 9).flatMap(size => {
//        (1 to 9).combinations(size).toList
//          .flatMap(comb => comb.permutations.map(vec => (vec.toSet, vec.mkString.toInt)))
//          .filter { case (_, n) => testIsPrime(n) }
//      })
//
//      val primesGroup = primes.groupBy(_._1).mapValues(_.unzip._2.map(Set(_)).toSet)
//
//      def emptyIntersect(s1: Set[Int], s2: Set[Int]): Boolean = {
//        s1.forall(n => !s2(n))
//      }
//
//      type MapType = Map[Set[Int], Set[Set[Int]]]
//
//      def join(set1: MapType, set2: MapType): MapType = {
//        (for {
//          key <- set1.keySet ++ set2.keySet
//        } yield {
//          if (set1.contains(key) && set2.contains(key))
//            key -> (set1(key) ++ set2(key))
//          else if (set1.contains(key))
//            key -> set1(key)
//          else
//            key -> set2(key)
//        }).toMap
//      }
//
//      def loop(groups: MapType): Set[Set[Int]] = {
//        if (groups.size == 1)
//          groups.head._2
//        else {
//          val shosenGroup = groups.keys.dropWhile(_.size == 9).head
//          val shosenElements = groups(shosenGroup)
//          val remainingGroups = groups - shosenGroup
//
//          val result =
//            for {
//              (key, elements) <- remainingGroups
//              if emptyIntersect(key, shosenGroup)
//            } yield (key ++ shosenGroup) -> elements.flatMap(elem => shosenElements.map(_ ++ elem))
//
//          val j = join(result, remainingGroups)
//          loop(j)
//        }
//      }
//
//      val finalResult = loop(primesGroup)
//      println(finalResult)
//
//      finalResult.size
//    }
  }

}
