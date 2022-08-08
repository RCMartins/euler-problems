package euler.prob001_100

import euler.traits.UtilResult

/**
  * Created by Ricardo
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

    println(lists.map(_.size))
    println(lists.map(_.size).sum)

    def num(a: Int, b: Int) = a * 100 + b

    def loop(i: Int, a: Int, b: Int, lists: List[Set[Int]], acc: List[Int]): Unit = {
      lists match {
        case Nil =>
          if (acc.head.toString.drop(2).toInt == i) {
            println((acc.size, acc.sum, acc.reverse))
            //return acc.sum
          }
        case _ =>
          (10 to 99).foreach(
            c =>
              lists
                .filter(_(num(b, c)))
                .foreach(list => loop(i, b, c, lists.filterNot(_ eq list), num(b, c) :: acc))
          )
      }
    }

    for {
      elem <- lists.head
      str = elem.toString
      (a, b) = str.splitAt(2)
    } loop(a.toInt, a.toInt, b.toInt, lists.tail, List(elem))

    ???
  }
}
