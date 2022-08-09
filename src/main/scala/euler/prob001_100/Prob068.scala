package euler.prob001_100

import euler.traits.UtilResult

/** Created by Ricardo
  */
object Prob068 extends UtilResult {
  def calc: Long = {
    val gon5: IndexedSeq[List[Int]] =
      IndexedSeq(
        List(0, 1, 2),
        List(3, 2, 4),
        List(5, 4, 6),
        List(7, 6, 8),
        List(9, 8, 1),
      )

    val allIndexes: IndexedSeq[Int] =
      gon5.flatten

    (1 to 10).permutations.flatMap { digits =>
      val seq = gon5.map(list => list.map(digits).sum)
      val isGon5 = (0 until seq.size - 1).forall(i => seq(i) == seq(i + 1))
      if (isGon5) {
        val minIndex =
          gon5
            .map(_.head)
            .zipWithIndex
            .map { case (index, gonIndex) => (gonIndex, digits(index)) }
            .minBy(_._2)
            ._1

        val nStr =
          (allIndexes.drop(minIndex * 3) ++ allIndexes.take(minIndex * 3)).map(digits).mkString
        if (nStr.length > 16)
          None
        else
          Some(nStr.toLong)
      } else
        None
    }.max

    // answer: 6531031914842725
  }
}
