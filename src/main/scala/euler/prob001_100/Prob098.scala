package euler.prob001_100

import euler.traits.UtilResult

import scala.annotation.tailrec

/** Created by Ricardo
  */
object Prob098 extends UtilResult {

  def calc: Long = {
    val wordsList = readData("p098_words.txt").split(",").map(_.drop(1).dropRight(1)).toList

    val wordPairs: List[(String, List[String])] =
      wordsList.groupBy(_.sorted).filter(_._2.sizeIs > 1).toList.sortBy(-_._1.length)

    val allSquares: Map[Int, List[Long]] =
      LazyList
        .from(1)
        .map(n => n.toLong * n)
        .takeWhile(_ < 1000000000)
        .toList
        .filterNot(_.toString.contains("0"))
        .groupBy(_.toString.length)
        .map { case (key, value) => key -> value.sortBy(-_) }

    def checkWords(pairsList: List[List[String]]): List[Long] = {
      pairsList match {
        case List(word1, word2) :: next =>
          allSquares.get(word1.length) match {
            case Some(perfectSquareList) =>
              val perfectSquareSet: Set[Long] = perfectSquareList.toSet

              @tailrec
              def matchesSq(word1: String, word2: String, list: List[Long]): Option[Long] =
                list match {
                  case square :: next =>
                    val matchesSet1: Map[Char, IndexedSeq[(Char, Char)]] =
                      square.toString.zip(word1).groupBy(_._1)
                    val matchesSet2: Map[Char, IndexedSeq[(Char, Char)]] =
                      word1.zip(square.toString).groupBy(_._1)
                    if (
                      matchesSet1.forall(_._2.distinct.sizeIs == 1) &&
                      matchesSet2.forall(_._2.distinct.sizeIs == 1)
                    ) {
                      if (perfectSquareSet(word2.map(matchesSet2(_).head._2).toLong))
                        Some(square)
                      else
                        matchesSq(word1, word2, next)
                    } else
                      matchesSq(word1, word2, next)
                  case Nil =>
                    None
                }

              (
                matchesSq(word1, word2, perfectSquareList),
                matchesSq(word2, word1, perfectSquareList)
              ) match {
                case (Some(square1), Some(square2)) =>
                  Math.max(square1, square2) :: checkWords(next)
                case _ =>
                  checkWords(next)
              }
            case None =>
              checkWords(next)
          }
        case List(word1, word2, word3) :: next =>
          checkWords(List(word1, word2) :: List(word1, word3) :: List(word2, word3) :: next)
        case Nil =>
          Nil
      }

    }

    checkWords(wordPairs.map(_._2)).max

    // answer: 18769
  }

}
