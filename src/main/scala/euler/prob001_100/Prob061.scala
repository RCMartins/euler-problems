package euler.prob001_100

import euler.traits.UtilResult

/** Created by Ricardo
  */
object Prob061 extends UtilResult {
  def calc: Long = {
    val p3 = LazyList.from(1).map(n => n * (n + 1) / 2)
    val p4 = LazyList.from(1).map(n => n * n)
    val p5 = LazyList.from(1).map(n => n * (3 * n - 1) / 2)
    val p6 = LazyList.from(1).map(n => n * (2 * n - 1))
    val p7 = LazyList.from(1).map(n => n * (5 * n - 3) / 2)
    val p8 = LazyList.from(1).map(n => n * (3 * n - 2))

    val lists = List(p3, p4, p5, p6, p7, p8).map(_.dropWhile(_ < 1000).takeWhile(_ < 10000).toSet)

    val twoDigitNumbers: LazyList[Int] =
      LazyList.from(10 to 99)

    @inline def num(a: Int, b: Int): Int = a * 100 + b

    def loop(a: Int, b: Int, lists: List[Set[Int]], acc: List[Int]): Option[Long] =
      if (lists.isEmpty)
        if (acc.head.toString.drop(2).toInt == a)
          Some(acc.sum)
        else
          None
      else
        twoDigitNumbers
          .flatMap(c =>
            lists
              .filter(_(num(b, c)))
              .flatMap(list => loop(a, c, lists.filterNot(_ eq list), num(b, c) :: acc))
          )
          .headOption

    LazyList
      .from(lists.head)
      .flatMap { elem =>
        val str = elem.toString
        val (a, b) = str.splitAt(2)
        loop(a.toInt, b.toInt, lists.tail, List(elem))
      }
      .head
  }
}
