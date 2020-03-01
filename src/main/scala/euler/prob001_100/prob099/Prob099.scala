package euler.prob001_100.prob099

import java.io.{BufferedReader, FileInputStream, InputStream, InputStreamReader}

import euler.traits.UtilResult

/**
  * Created by Ricardo
  */
object Prob099 extends UtilResult {
  def calc: Long = {
    val inputStream: InputStream = new FileInputStream("data\\p099_base_exp.txt")
    val br = new BufferedReader(new InputStreamReader(inputStream))

    def readAll(): Stream[List[Int]] = {
      readLine(br) match {
        case Some(text) =>
          if (text != null) {
            val list = text.split(',').toList.map(_.toInt)
            list #:: readAll()
          } else
            Stream()
        case None =>
          Stream()
      }
    }

    var biggestSoFarLine = 171

    val all = readAll().drop(biggestSoFarLine).map { case List(base, exp) => BigInt(base).pow(exp) }

    var biggestSoFar = all.head

    for {
      (number, index) <- all.zip(Stream.from(biggestSoFarLine))
    } {
      println(index)
      if (number > biggestSoFar) {
        biggestSoFar = number
        biggestSoFarLine = index
        println(s"var biggestSoFarLine = $biggestSoFarLine\n")
      }
    }

    biggestSoFarLine + 1

    //709
  }
}
