package euler.prob101_200.prob122

import euler.traits.Util

/**
  * Created by Ricardo
  */
object Prob122 extends Util {

  def main(args: Array[String]): Unit = {

    type PAIR = (Int, Set[Int])

    val best: Array[Option[Set[PAIR]]] = Array.fill(201)(None)

    best(1) = Some(Set((0, Set(1))))

    def maxSize(n: Int) = n.toBinaryString.size + n.toBinaryString.count(_ == '1')

    //Vector(0, 1, 2, 2, 3, 3, 4, 3, 4, 4, 5, 4, 5, 5, 5, 4, 5, 5, 6, 5, 6, 6, 6, 5, 6, 6, 6, 6, 7, 6, 7, 5, 6, 6, 7, 6, 7, 7, 7, 6, 7, 7, 7, 7, 7, 7, 8, 6, 7, 7, 7, 7, 8, 7, 8, 7, 8, 8, 8, 7, 8, 8, 8, 6, 7, 7, 8, 7, 8, 8, 9, 7, 8, 8, 8, 8, 8, 8, 9, 7, 8, 8, 8, 8, 8, 8, 9, 8, 9, 8, 9, 8, 9, 9, 9, 7, 8, 8, 8, 8, 9, 8, 9, 8, 9, 9, 9, 8, 9, 9, 9, 8, 9, 9, 9, 9, 9, 9, 9, 8, 9, 9, 9, 9, 9, 9, 10, 7, 8, 8, 9, 8, 9, 9, 9, 8, 9, 9, 10, 9, 10, 10, 10, 8, 9, 9, 9, 9, 9, 9, 10, 9, 9, 9, 10, 9, 10, 10, 10, 8, 9, 9, 9, 9, 9, 9, 10, 9, 10, 9, 10, 9, 10, 10, 10, 9, 10, 10, 10, 9, 10, 10, 10, 9, 10, 10, 10, 10, 10, 10, 11, 8, 9, 9, 9, 9, 10, 9, 10, 9)

    def find(exp: Int): Set[PAIR] = {
      if (best(exp).isDefined)
        best(exp).get
      else {
        //        if (exp % 2 == 0) {
        //          val list = find(exp / 2)
        //          best(exp) = Some(list.map { case (v, set) => (v + 1, set + exp) })
        //          best(exp).get
        //        } else {
        val res = (1 until exp).flatMap(n => {
          val (list1, list2) = (find(exp - n), find(n))

          //((v1, set1), (v2, set2))

          val all = list1.flatMap(elem => list2.map((elem, _)))

          //println(s" >> m(${exp}) = (find(${exp - n}), find($n)) = ${show(list1)} | ${show(list2)} | ${show(all)}")

          val all2 = all.map { case (pair1, pair2) => (pair1, pair2) match {
            case ((v1, set1), (v2, set2)) =>
              //println((v1, set1) + " | " + (v2, set2))

              if (set1.subsetOf(set2)) {
                pair2
              } else if (set2.subsetOf(set1)) {
                pair1
              } else {
                val v = math.max(v1, v2)
                val size = math.max(set1.size, set2.size)
                val set = set1.union(set2)
                (v + set.size - size, set)
              }
          }
          }

          //println(" >>> " + all2)

          //println()

          //            val v = v1 + v2 + 1

          //val setFinal = set2.foldLeft(set1)((acc, set) => if (acc.exists(set.subsetOf(_))) acc else acc + set)

          //            var setFinal = set1 ++ set2
          //            setFinal = setFinal.filterNot(set => (setFinal-set).exists(set.subsetOf(_)))
          //            setFinal = setFinal.map(_ + exp)
          //            println(s"m($exp) = $v, ${(v1, v2)}, ${set1.mkString(",")} ++ ${set2.mkString(",")} := ${setFinal.mkString(",")}") //${(set1 ++ set2).mkString(",")}")

          //(v, setFinal)
          all2
        }
        ).toSet

        //val res2 = res.distinct.map { case (v, set) => (v + 1, set + exp) }.filter { case (v, _) => v <= maxSizeVec(exp) }

        var res2 = res.map { case (v, set) => (v + 1, set + exp) }
        val minV = res2.map { case (v, _) => v }.min
        //val minV = maxSizeVec(exp)
        res2 = res2.filter { case (v, _) => v <= minV }


        //println("### " + res2 + " ###\n")

        best(exp) = Some(res2)
        res2
        //        }
      }
    }

    val MAX = 200

    //    for {
    //      n <- 1 to MAX
    //    } find(n)

    val v = for {
      n <- 1 to MAX
      f = find(n).toList.sortBy(_._1)
      bla = println("%6s (max: %d) = %s".format("m(" + n + ")", maxSize(n), f.mkString(" | ")))
    } yield {
      f.head._1
    }

    println(v)

    result(v.sum)

    //1582 ...
  }

}
