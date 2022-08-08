package euler.prob101_200.prob105

import java.io.{BufferedReader, FileInputStream, InputStreamReader}

import euler.traits.Util

/**
  * Created by Ricardo
  */
object Prob105 extends Util {

  def main(args: Array[String]): Unit = {

    val inputStream = new FileInputStream("data\\p105_sets.txt")
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

    val v =
      for {
        (set, index) <- readAll().zipWithIndex
        allSubsets = set.toSet.subsets().map(_.sum).toList
        if allSubsets.distinct.size == allSubsets.size
        //        _ = println(allSubsets.sorted.mkString(", "))
        vec = set.sorted.toVector
        maxT = (vec.size + 1) / 2
        if (2 to maxT).forall(size => vec.take(size).sum > vec.takeRight(size - 1).sum)
      } yield (index, vec.sum)

    //    println(v.size)
    //    println(v.force)

    time(v.unzip._2.sum)

  }

}
