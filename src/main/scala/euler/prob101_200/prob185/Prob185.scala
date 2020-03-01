package euler.prob101_200.prob185

import euler.traits.Util

/**
  * Created by Ricardo
  */
object Prob185 extends Util {

  def main(args: Array[String]): Unit = {

//    val probEasy =
//      Vector(("34109", 1),
//        ("12531", 1),
//        ("9#342", 2),
//        ("39458", 2),
//        ("51545", 2))

//    val solEasy = "39542"

    val probHard =
      Vector(("384743964729#0#7", 1),
        ("3174248439465#58", 1),
        ("8157#5#34#118483", 1),
        ("6#75711915077050", 1),
        ("6913859#73121360", 1),
        ("4895722652190306", 1),
        ("56161#5650518293", 2),
        ("451355909#146117", 2),
        ("#61525074##86#99", 2),
        ("64428#9055042768", 2),
        ("###65094712714#8", 2),
        ("525#5#3379644322", 2),
        ("#659862637#16#67", 2),
        ("5855462940810587", 3),
        ("97428555#7068353", 3),
        ("42968496436#75#3", 3),
        ("78909715489#8067", 3),
        ("8690095851526254", 3),
        ("1748270476758276", 3),
        ("304#631#1722463#", 3),
        ("184#23#45##24589", 3))

    //    val probHard =
    //      Vector(("84743964729#0#7", 1),
    //        ("174248439465#58", 1),
    //        ("157#5#34#118483", 1),
    //        ("#75711915077050", 1),
    //        ("913859#73121360", 1),
    //        ("895722652190306", 1),
    //        ("6161#5650518293", 1),
    //        ("25#5#3379644322", 1),
    //        ("51355909#146117", 2),
    //        ("61525074##86#99", 2),
    //        ("4428#9055042768", 2),
    //        ("##65094712714#8", 2),
    //        ("659862637#16#67", 2),
    //        ("855462940810587", 2),
    //        ("7428555#7068353", 3),
    //        ("2968496436#75#3", 3),
    //        ("8909715489#8067", 3),
    //        ("690095851526254", 3),
    //        ("748270476758276", 3),
    //        ("04#631#1722463#", 3),
    //        ("84#23#45##24589", 3))

    val prob = probHard
//    val probInts: Seq[IndexedSeq[Int]] = probHard.map { case (str, _) => str.map(c => if (c == '#') -1 else c.toInt) }
    val size = prob.head._1.length

    case class SubSet(sub: Set[(Int, Int)], wrong: Set[(Int, Int)])

    def createSubSet(sub: Set[(Int, Int)], list: Vector[(Int, Int)]): SubSet = {
      val wrong = list.toSet.filterNot(x => sub.unzip._2(x._2))
      SubSet(sub, wrong)
    }

    def show(): Unit = {
      val sorted = prob.sortBy(_._2)

      val valid = Array.ofDim[Boolean](size, 10)
      for {
        pos <- 0 until size
        d <- 0 to 9
      } valid(pos)(d) = true

      for {
        pos <- 0 until 4
        d <- 1 to 9
      } valid(pos)(d) = false

      var best = 0
      var tries = 0L

//      println(digits("0123456789123456").zipWithIndex.toSet.subsets(3).size)

      def loop(list: List[(List[SubSet], Int)]): Unit = list match {
        case (subsets, listIndex) :: xs =>
          tries += 1
          if (tries % 100000 == 0) {
            println(tries)
          }
          if (listIndex > best) {
            best = listIndex
            println(s"Best: $best")
          }

          //          if (correct == 3)
          //            println("Hmm")
          //          if (correct == 0) {
          //            number.foreach {
          //              case (d, index) => valid(index)(d) = false
          //            }
          //            loop(xs)
          //          } else {

//                    val onlyValid =
//                      (for {
//                        index <- 0 until size
//                        if valid(index).count(identity) == 1
//                      } yield (valid(index).zipWithIndex.filter(x => x._1).map(_._2).mkString.toInt, index)
//                        ).toSet

          // sub => Set(digit, pos)
          for {
            SubSet(sub, wrong) <- subsets
            if sub.forall { case (d, pos) => valid(pos)(d) }
//                      if (onlyValid -- sub).forall {
//                        case (d, pos) =>
//                          val oDigit = probInts(listIndex)(pos)
//                          oDigit != d
//                      }
          } {
            val toRemove: Set[(Int, Int)] =
              (for {
                (d, pos) <- sub
                removeD <- (0 to 9).filterNot(_ == d) if valid(pos)(removeD)
              } yield (removeD, pos)
                ) ++ wrong.filter { case (d, pos) => valid(pos)(d) }

            for ((removeD, pos) <- toRemove)
              valid(pos)(removeD) = false

            loop(xs)

            for ((removeD, pos) <- toRemove)
              valid(pos)(removeD) = true
          }
        //            println(subsets.mkString("\n"))
        //          }
        case Nil =>
          //          result {
          val res = valid.toList.map(_.zipWithIndex.filter(x => x._1).map(_._2).mkString).mkString("")
          if (res.length == size)
            result(res)
        //            else
        //              "WRONG: " + res
        //          }
      }

      //      val withIndex: Seq[((String, Int), Int)] = sorted.toList .zipWithIndex
      //      println(withIndex.map { case ((n, c), _) => s"$c: ${n.mkString(" ")}" }.mkString("\n"))

      val stuff =
        sorted
          .toList
          .map { case (n, c) =>
            val l = n.toString.zipWithIndex.filterNot(_._1 == '#')
              .map { case (d, pos) => (d - '0', pos) }
            l.toSet.subsets(c).toList
              .map(sub => createSubSet(sub, l.toVector))
          }
          .zipWithIndex
      loop(stuff)
    }

    show()
  }

}
