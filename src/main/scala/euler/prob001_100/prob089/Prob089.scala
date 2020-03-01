package euler.prob001_100.prob089

import java.io.{BufferedReader, FileInputStream, InputStream, InputStreamReader}

import euler.traits.UtilResult

/**
  * Created by Ricardo
  */
object Prob089 extends UtilResult {
  def calc: Long = {
    val ALL_RULES = List(
      ("XC", 90),
      ("XL", 40),
      ("IX", 9),
      ("IV", 4),
      ("M", 1000),
      ("D", 500),
      ("C", 100),
      ("L", 50),
      ("X", 10),
      ("V", 5),
      ("I", 1)
    )

    //    def calcRoman(str: String, max: Int = 1000): Int = {
    //      def calc(str, max, char, value)
    //
    //      if (max == 1000) { if (str.startsWith("M") 1000 * calcRoman(str, max) else calcRoman())}
    //    }

    def calcRoman(str: String, r: List[(String, Int)]): Int = r match {
      case (rule, value) :: _ if str.contains(rule) =>
        value + calcRoman(str.replaceFirst(rule, ""), r)
      case _ :: xs => calcRoman(str, xs)
      case Nil     => 0
    }

    def writeRoman(value: Int): String = {
      if (value >= 1000) "M" concat writeRoman(value - 1000)
      else if (value >= 900) "CM" concat writeRoman(value - 900)
      else if (value >= 500) "D" concat writeRoman(value - 500)
      else if (value >= 400) "CD" concat writeRoman(value - 400)
      else if (value >= 100) "C" concat writeRoman(value - 100)
      else if (value >= 90) "XC" concat writeRoman(value - 90)
      else if (value >= 50) "L" concat writeRoman(value - 50)
      else if (value >= 40) "XL" concat writeRoman(value - 40)
      else if (value >= 10) "X" concat writeRoman(value - 10)
      else if (value >= 9) "IX" concat writeRoman(value - 9)
      else if (value >= 5) "V" concat writeRoman(value - 5)
      else if (value >= 4) "IV" concat writeRoman(value - 4)
      else if (value >= 1) "I" concat writeRoman(value - 1)
      else ""
    }

    val inputStream: InputStream = new FileInputStream("data\\p089_roman.txt")
    val br = new BufferedReader(new InputStreamReader(inputStream))

    def readFile(): List[String] = readLine(br) match {
      case Some(text) if text != null && text.nonEmpty =>
        text :: readFile()
      case _ =>
        Nil
    }

    readFile().map(str => str.length - writeRoman(calcRoman(str, ALL_RULES)).length).sum
  }
}
