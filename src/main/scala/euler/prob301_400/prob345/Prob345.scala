package euler.prob301_400.prob345

import euler.traits.Util

/**
  * Created by Ricardo
  */
object Prob345 extends Util {

  def main(args: Array[String]): Unit = {

    val data = "  7  53 183 439 863 497 383 563  79 973 287  63 343 169 583\n627 343 773 959 943 767 473 103 699 303 957 703 583 639 913\n447 283 463  29  23 487 463 993 119 883 327 493 423 159 743\n217 623   3 399 853 407 103 983  89 463 290 516 212 462 350\n960 376 682 962 300 780 486 502 912 800 250 346 172 812 350\n870 456 192 162 593 473 915  45 989 873 823 965 425 329 803\n973 965 905 919 133 673 665 235 509 613 673 815 165 992 326\n322 148 972 962 286 255 941 541 265 323 925 281 601  95 973\n445 721  11 525 473  65 511 164 138 672  18 428 154 448 848\n414 456 310 312 798 104 566 520 302 248 694 976 430 392 198\n184 829 373 181 631 101 969 613 840 740 778 458 284 760 390\n821 461 843 513  17 901 711 993 293 157 274  94 192 156 574\n 34 124   4 878 450 476 712 914 838 669 875 299 823 329 699\n815 559 813 459 522 788 168 586 966 232 308 833 251 631 107\n813 883 451 509 615  77 281 613 459 205 380 274 302  35 805".
      split("\n").map(_.split(" ").filter(_.nonEmpty).map(_.toInt).toVector).toVector

    println(data)

    var best = 0

    val indices = 0 until 15
    val it = indices.permutations
    while (it.hasNext) {
      val perm = it.next()
      val value = indices.map(index => data(index)(perm(index))).sum
      if (value > best) {
        best = value
        println((best, perm))
      }
    }


    //    def loop(indices: List[Int], values: List[Int], valueSoFar: Int): Unit = {
    //      indices match {
    //        case index :: others =>
    //
    //          loop(others)
    //        case Nil => ()
    //      }
    //    }
    //
    //    loop((0 until 15).toList, Nil, 0)

    result(best)

  }

}
