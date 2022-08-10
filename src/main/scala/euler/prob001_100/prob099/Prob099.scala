package euler.prob001_100.prob099

import java.io.{BufferedReader, FileInputStream, InputStream, InputStreamReader}

import euler.traits.UtilResult

/** Created by Ricardo
  */
object Prob099 extends UtilResult {
  def calc: Long = {
    val inputStream: InputStream = new FileInputStream("data\\p099_base_exp.txt")
    val br = new BufferedReader(new InputStreamReader(inputStream))

    def readAll(): LazyList[List[Int]] = {
      readLine(br) match {
        case Some(text) =>
          if (text != null) {
            val list = text.split(',').toList.map(_.toInt)
            list #:: readAll()
          } else
            LazyList()
        case None =>
          LazyList()
      }
    }

    var biggestSoFarLine = 171

    val all: LazyList[BigInt] =
      readAll().drop(biggestSoFarLine).collect { case List(base, exp) => BigInt(base).pow(exp) }

    var biggestSoFar = all.head

    for {
      (number, index) <- all.zip(LazyList.from(biggestSoFarLine))
    } {
      println(index)
      if (number > biggestSoFar) {
        biggestSoFar = number
        biggestSoFarLine = index
        println(s"biggestSoFarLine = $biggestSoFarLine\n")
      }
    }

    biggestSoFarLine + 1

    // answer: 709
  }
}
