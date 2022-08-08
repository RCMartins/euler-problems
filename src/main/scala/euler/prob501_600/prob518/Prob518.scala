package euler.prob501_600.prob518

import euler.traits.Util

/**
  * Created by Ricardo
  */
object Prob518 extends Util {

  def main(args: Array[String]): Unit = {

    val MAX = 1e8.toInt

    val primes = SMALL_PRIMES.takeWhile(_ < MAX)
    //    val t = System.currentTimeMillis()
    //    println("size", primes.size)
    //    println(System.currentTimeMillis() - t)

    def loop(primes: LazyList[Int]): LazyList[(Int, LazyList[Int])] = {
      primes match {
        case LazyList() => LazyList()
        case prime #:: rest => (prime, rest) #:: loop(rest)
      }
    }

    //    val primesPairs =

    //    println(primesPairs.map(n => (n._1, n._2.force)).force)

    //    val v = for {
    //      (primeA, primesHigher) <- primesPairs
    //      _ = println(primeA)
    //      a = primeA + 1
    //      primeB <- primesHigher
    //      b = primeB + 1
    //      c = (b.toDouble * b) / a
    //      if c.isValidInt && c < MAX
    //      primeC = c.toInt - 1
    //      if testIsPrime(primeC)
    //    } yield primeA + primeB + primeC
    //
    //    result(v.sum)


    var total: Long = 0

    def calc(primeA: Int, primesHigher: LazyList[Int]): Unit = {
      primesHigher match {
        case primeB #:: rest =>
          val a = primeA + 1
          val b = primeB + 1
          val c = (b.toDouble * b) / a
          if (c.isValidInt) {
            if (c < MAX) {
              val primeC = c.toInt - 1
              if (testIsPrime(primeC)) {
                total += primeA + primeB + primeC
              }
              calc(primeA, rest)
            }
          } else
            calc(primeA, rest)
        case LazyList() =>
      }
    }

    var count = 0
    val totalPrimes = 5761455

    for {
      (primeA, primesHigher) <- loop(primes)
    } {
      count += 1
      if (count % 10000 == 0)
        println((((count.toDouble / totalPrimes) * 100) + "%", "prime: " + primeA, "total: " + total))
      calc(primeA, primesHigher)
    }

    result(total)
  }

}

/*
"C:\Program Files\Java\jdk1.8.0_77\bin\java" -Didea.launcher.port=7534 "-Didea.launcher.bin.path=C:\Program Files (x86)\JetBrains\IntelliJ IDEA Community Edition 2016.3\bin" -Dfile.encoding=UTF-8 -classpath "C:\Program Files\Java\jdk1.8.0_77\jre\lib\charsets.jar;C:\Program Files\Java\jdk1.8.0_77\jre\lib\deploy.jar;C:\Program Files\Java\jdk1.8.0_77\jre\lib\ext\access-bridge-64.jar;C:\Program Files\Java\jdk1.8.0_77\jre\lib\ext\cldrdata.jar;C:\Program Files\Java\jdk1.8.0_77\jre\lib\ext\dnsns.jar;C:\Program Files\Java\jdk1.8.0_77\jre\lib\ext\jaccess.jar;C:\Program Files\Java\jdk1.8.0_77\jre\lib\ext\jfxrt.jar;C:\Program Files\Java\jdk1.8.0_77\jre\lib\ext\localedata.jar;C:\Program Files\Java\jdk1.8.0_77\jre\lib\ext\nashorn.jar;C:\Program Files\Java\jdk1.8.0_77\jre\lib\ext\sunec.jar;C:\Program Files\Java\jdk1.8.0_77\jre\lib\ext\sunjce_provider.jar;C:\Program Files\Java\jdk1.8.0_77\jre\lib\ext\sunmscapi.jar;C:\Program Files\Java\jdk1.8.0_77\jre\lib\ext\sunpkcs11.jar;C:\Program Files\Java\jdk1.8.0_77\jre\lib\ext\zipfs.jar;C:\Program Files\Java\jdk1.8.0_77\jre\lib\javaws.jar;C:\Program Files\Java\jdk1.8.0_77\jre\lib\jce.jar;C:\Program Files\Java\jdk1.8.0_77\jre\lib\jfr.jar;C:\Program Files\Java\jdk1.8.0_77\jre\lib\jfxswt.jar;C:\Program Files\Java\jdk1.8.0_77\jre\lib\jsse.jar;C:\Program Files\Java\jdk1.8.0_77\jre\lib\management-agent.jar;C:\Program Files\Java\jdk1.8.0_77\jre\lib\plugin.jar;C:\Program Files\Java\jdk1.8.0_77\jre\lib\resources.jar;C:\Program Files\Java\jdk1.8.0_77\jre\lib\rt.jar;D:\Documents\Dropbox\Ricardo\Growin\ScalaProjects\EulerMathProblemsSBT\target\scala-2.12\classes;C:\Users\Ricardo\.ivy2\cache\org.scala-lang\scala-library\jars\scala-library-2.12.1.jar;C:\Users\Ricardo\.ivy2\cache\org.scala-lang\scala-reflect\jars\scala-reflect-2.12.1.jar;C:\Users\Ricardo\.ivy2\cache\org.spire-math\spire-macros_2.12\jars\spire-macros_2.12-0.13.0.jar;C:\Users\Ricardo\.ivy2\cache\org.spire-math\spire_2.12\jars\spire_2.12-0.13.0.jar;C:\Users\Ricardo\.ivy2\cache\org.typelevel\machinist_2.12\jars\machinist_2.12-0.6.1.jar;C:\Program Files (x86)\JetBrains\IntelliJ IDEA Community Edition 2016.3\lib\idea_rt.jar" com.intellij.rt.execution.application.AppMain Prob518
(0.17356726729619515%,prime: 104729,total: 3660593181532)
(0.3471345345923903%,prime: 224737,total: 5267473395209)
(0.5207018018885855%,prime: 350377,total: 6500204534241)
(0.6942690691847806%,prime: 479909,total: 7619752380969)
(0.8678363364809757%,prime: 611953,total: 8573428854124)
(1.041403603777171%,prime: 746773,total: 9430897611653)
(1.214970871073366%,prime: 882377,total: 10210555447883)
(1.3885381383695612%,prime: 1020379,total: 11000127475408)
(1.5621054056657564%,prime: 1159523,total: 11763781466726)
(1.7356726729619514%,prime: 1299709,total: 12442011804241)
(1.9092399402581468%,prime: 1441049,total: 13050817699196)
(2.082807207554342%,prime: 1583539,total: 13710903216551)
(2.2563744748505368%,prime: 1726943,total: 14302561972015)
(2.429941742146732%,prime: 1870667,total: 14920531045583)
(2.603509009442927%,prime: 2015177,total: 15519408092966)
(2.7770762767391224%,prime: 2160553,total: 16107765882941)
(2.9506435440353176%,prime: 2307229,total: 16620180914169)
(3.124210811331513%,prime: 2454587,total: 17118749842040)
(3.2977780786277076%,prime: 2601857,total: 17686453563787)
(3.4713453459239028%,prime: 2750159,total: 18253017452838)
(3.644912613220098%,prime: 2898527,total: 18743515658725)
(3.8184798805162936%,prime: 3047767,total: 19180893672296)
(3.992047147812489%,prime: 3196933,total: 19587140036345)
(4.165614415108684%,prime: 3346601,total: 20049338894520)
(4.339181682404878%,prime: 3497861,total: 20485040352950)
(4.5127489497010735%,prime: 3648923,total: 20978125085210)
(4.686316216997269%,prime: 3800201,total: 21409237509952)
(4.859883484293464%,prime: 3951161,total: 21851301687220)
(5.033450751589659%,prime: 4103629,total: 22344317572334)
(5.207018018885854%,prime: 4256233,total: 22791585801937)
(5.38058528618205%,prime: 4410317,total: 23233709742282)
(5.554152553478245%,prime: 4562693,total: 23683894017064)
(5.72771982077444%,prime: 4716053,total: 24069615996862)
(5.901287088070635%,prime: 4869863,total: 24495195746709)
(6.07485435536683%,prime: 5023307,total: 24903705854907)
(6.248421622663026%,prime: 5178049,total: 25343850127076)
(6.421988889959221%,prime: 5332519,total: 25748826327345)
(6.595556157255415%,prime: 5487701,total: 26169858976496)
(6.769123424551611%,prime: 5644031,total: 26517952999440)
(6.9426906918478055%,prime: 5800079,total: 26876888877358)
(7.116257959144001%,prime: 5955031,total: 27261457737798)
(7.289825226440196%,prime: 6111613,total: 27677186237264)
(7.463392493736391%,prime: 6268289,total: 28073852409213)
(7.636959761032587%,prime: 6424937,total: 28396302124357)
(7.8105270283287815%,prime: 6581963,total: 28795065430809)
(7.984094295624978%,prime: 6738889,total: 29159592987689)
(8.157661562921172%,prime: 6895393,total: 29513606978665)
(8.331228830217368%,prime: 7052113,total: 29868212139443)
(8.504796097513562%,prime: 7210759,total: 30204958808843)
(8.678363364809757%,prime: 7368787,total: 30556038813320)
(8.851930632105951%,prime: 7528121,total: 30898675908132)
(9.025497899402147%,prime: 7685801,total: 31235990955831)
(9.199065166698343%,prime: 7844731,total: 31620964641627)
(9.372632433994537%,prime: 8003537,total: 31945397542409)
(9.546199701290734%,prime: 8163047,total: 32310029322761)
(9.719766968586928%,prime: 8322241,total: 32655822150489)
(9.893334235883124%,prime: 8482259,total: 32955284813476)
(10.066901503179318%,prime: 8640679,total: 33332470906539)
(10.240468770475514%,prime: 8799919,total: 33708418477497)
(10.414036037771709%,prime: 8960453,total: 34074207593332)
(10.587603305067903%,prime: 9121439,total: 34376535786351)
(10.7611705723641%,prime: 9281011,total: 34706161816222)
(10.934737839660293%,prime: 9442229,total: 35081539539948)
(11.10830510695649%,prime: 9602443,total: 35395406646278)
(11.281872374252684%,prime: 9763393,total: 35707464486745)
(11.45543964154888%,prime: 9925439,total: 36039057117869)
(11.629006908845074%,prime: 10086767,total: 36329992240512)
(11.80257417614127%,prime: 10248899,total: 36702340831636)
(11.976141443437465%,prime: 10410139,total: 37022857285342)
(12.14970871073366%,prime: 10570841,total: 37352494173815)
(12.323275978029855%,prime: 10732003,total: 37685995363944)
(12.496843245326051%,prime: 10894817,total: 37976159014938)
(12.670410512622245%,prime: 11056729,total: 38271467013495)

Process finished with exit code 1


 */
