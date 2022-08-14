package euler.prob701_800

import euler.traits.UtilResult

/** Created by Ricardo
  */
object Prob719 extends UtilResult {

  def calc: Long = {
    val target = BigInt(10).pow(12).toLong

    val nList: List[(Int, Long)] =
      LazyList.from(1).map(n => (n, n.toLong * n)).takeWhile(_._2 <= target).toList

    val filtered: List[Long] =
      nList
        .filter { case (n, nSq) =>
          val maxLength = n.toString.length

          def loop(digits: List[Int], acc: Int, num: Int, size: Int): Boolean = {
            if (acc + num > n || size > maxLength)
              false
            else
              digits match {
                case Nil =>
                  acc != 0 && acc + num == n
                case head :: next =>
                  loop(next, acc + num, head, 1) ||
                  loop(next, acc, num * 10 + head, size + 1)
              }
          }

          loop(nSq.toString.toCharArray.map(_ - '0').toList, acc = 0, num = 0, size = 0)
        }
        .map(_._2)

    filtered.sum

    // answer: 128088830547982
  }
}
