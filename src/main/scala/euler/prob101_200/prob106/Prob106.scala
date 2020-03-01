package euler.prob101_200.prob106

import euler.traits.Util

/**
  * Created by Ricardo
  */
object Prob106 extends Util {

  def main(args: Array[String]): Unit = {

    val SIZE = 4

    println {
      val group = (0 until SIZE).toSet.subsets().toList /*.map(_.mkString)*/ .groupBy(_.size)

//      val v =
//        for {
//        list <- group
//        e <- list._2
//        (a, b) <- group(SIZE - list._1).map(set => (e, set))
//        if !a.exists(b)
//      } yield (a, b)
//      (v.size, v.mkString("\n"))

      val v =
        for {
          list <- group
          e <- list._2
          (a, b) <- group.values.flatten.map(set => (e, set))
//          if !a.exists(b)
        } yield (a, b)
      (v.size, v.mkString("\n"))
    }

    //    val MAX = 20
    //    val v =
    //      for {
    //        a <- 1 to MAX
    //        b <- a + 1 to MAX
    //        c <- b + 1 to MAX
    //        d <- c + 1 to MAX
    //        e <- d + 1 to MAX
    //        f <- e + 1 to MAX
    //        vec = Vector(a, b, c, d, e, f)
    //        if {
    //          val maxT = (vec.size + 1) / 2
    //          (2 to maxT).forall(size => vec.take(size).sum > vec.takeRight(size - 1).sum)
    //        }
    //
    //        set = vec.toSet
    //        allSubsets = set.subsets().map(_.sum).toList
    //        if allSubsets.distinct.size != allSubsets.size
    //      } yield (vec.mkString(","), {
    //        val map = set.subsets().map(l => (l.sum, l.map(e => vec.indexOf(e)))).toList.groupBy(x => x._1).mapValues(_.map(_._2)).filter(_._2.size > 1)
    //
    //        map.values.toList.map {
    //          case List(la, lb) =>
    //            val inter = la.intersect(lb)
    //            List(la -- inter, lb -- inter).sortBy(_.min)
    //          case list =>
    //            list
    //        }
    //      })
    //
    //    println(v.flatMap(_._2).toSet.mkString("\n"))
  }

}
