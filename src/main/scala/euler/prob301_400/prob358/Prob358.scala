package euler.prob301_400.prob358

import euler.traits.Util

/** Created by Ricardo
  */
object Prob358 extends Util {

  val UNKNOWN_CHAR = '.'
  @inline final private val UNKNOWN_N = -1

  def main(args: Array[String]): Unit = {

    val initialTime = System.currentTimeMillis()

    val convert = {

      val (knownPart1, knownPart2) = ("00000000137", "56789")
//                  val (knownPart1, knownPart2) = ("1", "")
//            val (knownPart1, knownPart2) = ("0588", "")

      (unknownSize: Int) => {
        val s = knownPart1 + UNKNOWN_CHAR.toString * unknownSize + knownPart2
        s.map(d => if (d == UNKNOWN_CHAR) -1 else d.toInt - '0').reverse.toList
      }
    }

    def multSpecial(n1: List[Int], n2: Int): Either[Int, List[Int]] = {
      val hideLength = n2.toString.length + 1

      def aux(number: List[Int], n2: Int, over: Int, hide: Int): Either[Int, List[Int]] =
        //        if (n2 % 10 == 0) {
        //          0 :: aux(number, n2 / 10, over, hide)
        //        } else {
        number match {
          case n :: numberList =>
            if (n == UNKNOWN_N) {
              aux(numberList, n2, 0, hideLength).map(UNKNOWN_N :: _)
            } else {
              val result = n * n2 + over
              val divRest = result / 10
              if (hide > 0)
                aux(numberList, n2, divRest, hide - 1).map(UNKNOWN_N :: _)
              else {
                val digit = result - divRest * 10
                aux(numberList, n2, divRest, 0).map(digit :: _)
              }
            }
          case Nil =>
            if (over > 0) {
              Left(over)
            } else
              Right(Nil)
        }

      //        }

      val result = aux(n1, n2, 0, 0)
      //      val diff = result.length - n1.length
      //      if (diff > 0) {
      //        //        if (result.takeWhile(_ == 0).length >= diff)
      //        //          Some(result.dropWhile(_ == 0))
      //        //        else {
      //        println(result, n1)
      //        None
      //        //        }
      //      } else
      result
    }

//    def prettyTest(s: String, mult: Int): String = {
    ////      val l = s.map(d => if (d == UNKNOWN_CHAR) -1 else d.toInt - '0').reverse.toList
    ////      multSpecial(l, mult).fold(_.toString, _.map(d => if (d == -1) "." else d.toString).mkString("").reverse)
    ////    }

//        val MULT = 11
    //    println(prettyTest("000000000009", MULT))
    //    println(prettyTest("000000000099", MULT))
    //    println(prettyTest("000000000999", MULT))
    //    println(prettyTest("000000009999", MULT))
    //    println(prettyTest("000000099999", MULT))
    //
    //    println(prettyTest("000000000009", MULT))
    //    println(prettyTest("000000000099", MULT))
    //    println(prettyTest("000000000019", MULT))
    //    println(prettyTest("000000000999", MULT))
    //    println(prettyTest("000000000919", MULT))
    //    println(prettyTest("000000009999", MULT))
    //    println(prettyTest("000000009919", MULT))
    //    println(prettyTest("000000099999", MULT))
    //    println(prettyTest("000000099919", MULT))
//        println()
//        println(prettyTest("000000000999", MULT))
//        println(prettyTest("000000000909", MULT))
//        println(prettyTest("0000000009.9", MULT))
//        println()
//        println(prettyTest("000000000999999999", MULT))
//        println(prettyTest("000000000999990009", MULT))
//        println(prettyTest("00000000099999...9", MULT))
//    return

    //    println(multSpecial("01.4285.".map(d => if (d == '.') -1 else d.toInt - '0').reverse.toList, 1))
    //    println(multSpecial("01.4285.".map(d => if (d == '.') -1 else d.toInt - '0').reverse.toList, 2))
    //    println(multSpecial("01.4285.".map(d => if (d == '.') -1 else d.toInt - '0').reverse.toList, 28))
    //    println(multSpecial("01.4285.".map(d => if (d == '.') -1 else d.toInt - '0').reverse.toList, 29))
    //    println(multSpecial("01.4285.".map(d => if (d == '.') -1 else d.toInt - '0').reverse.toList, 30))

    def matchNumbers(number1: List[Int], number2: List[Int]): Boolean = {
      (number1, number2) match {
        case (x :: xs, y :: ys) =>
          (x == UNKNOWN_N || y == UNKNOWN_N || x == y) && matchNumbers(xs, ys)
        case (Nil, Nil) => true
        case _          => false
      }
    }

    NATURALS.slice(0, 100).foreach { unknownSize =>
      println(s"Testing unknownSize=$unknownSize...")

      val s = convert(unknownSize)

      def loopFind(initialNumber: List[Int], indexUnknown: Int, unknownCounter: Int): Unit = {
        val totalSize = initialNumber.length

        def numberRotations: LazyList[List[Int]] =
          LazyList
            .from(1 until totalSize)
            .map(index => {
              initialNumber.drop(index) ++ initialNumber.take(index)
            })

        //        println(initialNumber.reverse.mkString)
        //        println(numberRotations.map(_.reverse.mkString))
        //        println((1 to totalSize).map(multiplier => multiplier + ": " + multSpecial(initialNumber, multiplier)).mkString("\n"))

        if (
          (2 to totalSize)
            .map(multiplier => multSpecial(initialNumber, multiplier).toOption)
            .forall({
              case None             => false
              case Some(testNumber) => numberRotations.exists(r => matchNumbers(r, testNumber))
            })
        ) {
          if (unknownCounter == 0) {
            println(initialNumber.reverse.mkString)
            result(initialNumber.sum)
            println(System.currentTimeMillis() - initialTime)
            System.exit(0)
          }

          //          println(initialNumber.reverse.map(d => if (d == -1) "." else d.toString).mkString)

          for {
            digit <- 0 to 9
          } loopFind(
            initialNumber.updated(indexUnknown, digit),
            indexUnknown - 1,
            unknownCounter - 1
          )
        }
      }

      loopFind(s, s.lastIndexOf(UNKNOWN_N), s.count(_ == UNKNOWN_N))
    }

  }

  /*def main(args: Array[String]): Unit = {

    val UNKNOWN_CHAR = '.'

    def multSpecial(n1: String, n2: Int): Option[String] = {
      def aux(number: List[Int], n2: Int, over: Int, hide: Int): String =
        if (n2 % 10 == 0) {
          "0" + aux(number, n2 / 10, over, hide)
        } else {
          number match {
            case n :: numberList =>
              if (n == -1) {
                UNKNOWN_CHAR.toString + aux(numberList, n2, 0, n2.toString.length + 1)
              } else {
                val result = n * n2 + over
                val digit = result % 10
                val divRest = result / 10
                if (hide > 0)
                  UNKNOWN_CHAR.toString + aux(numberList, n2, divRest, hide - 1)
                else
                  digit.toString + aux(numberList, n2, divRest, 0)
              }
            case Nil => ""
          }
        }

      val result = aux(n1.map(d => if (d == UNKNOWN_CHAR) -1 else d.toInt - '0').reverse.toList, n2, 0, 0).reverse
      val diff = result.length - n1.length
      if (diff > 0) {
        if (result.startsWith("0" * diff))
          Some(result.substring(diff))
        else {
          println(result, n1)
          None
        }
      } else
        Some(result)
    }

    def matchNumbers(number1: String, number2: String): Boolean = {
      //      println(number1, number2)

      (0 until number1.length).forall {
        index =>
          val c1 = number1.charAt(index)
          c1 == UNKNOWN_CHAR || {
            val c2 = number2.charAt(index)
            c2 == UNKNOWN_CHAR || c1 == c2
          }
      }
    }

    //    println(multSpecial("05882352941176.7", 8))
    //    return

    //        println(multSpecial("01.4285.", 1))
    //        println(multSpecial("01.4285.", 2))
    //        println(multSpecial("01.4285.", 28))
    //        println(multSpecial("01.4285.", 29))
    //        println(multSpecial("01.4285.", 30))
    //    return

    //    val knownPart1 = "058823"
    //    val knownPart2 = "7647"
    //    val knownPart1 = "05882352"
    //    val knownPart2 = "7"

    //    val (knownPart1, knownPart2) = ("00000000137", "56789")
    val (knownPart1, knownPart2) = ("058", "")

    NATURALS.slice(0, 30).foreach { unknownSize =>
      println(s"Testing unknownSize=$unknownSize...")

      def loopFind(initialNumber: String): Unit = {
        val totalSize = initialNumber.length

        val numberRotations = (0 until totalSize).map(index => {
          initialNumber.substring(index) + initialNumber.substring(0, index)
        })

        //        println((1 to totalSize).map(multiplier => multiplier + ": " + multSpecial(initialNumber, multiplier)).mkString("\n"))

        if ((1 to totalSize).map(multiplier => multSpecial(initialNumber, multiplier)).forall({
          case None => false
          case Some(testNumber) => numberRotations.exists(r => matchNumbers(r, testNumber))
        })) {
          if (!initialNumber.contains(UNKNOWN_CHAR)) {
            println(initialNumber)
            result(initialNumber.map(_ - '0').sum)
            System.exit(0)
          }

          //          println(initialNumber)

          val lastIndex = initialNumber.lastIndexOf(UNKNOWN_CHAR)
          for {
            digit <- '0' to '9'
          } loopFind(initialNumber.updated(lastIndex, digit))
        }
      }

      val s = knownPart1 + UNKNOWN_CHAR.toString * unknownSize + knownPart2
      loopFind(s)
    }

  }*/

}
