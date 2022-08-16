package euler.prob001_100.prob093

import euler.traits.UtilResult
//import spire.math.Rational

/**
  * Created by Ricardo
  */
object Prob93c extends UtilResult {
  override def calc: Long = {
//    def num4(n1: Rational, n2: Rational, n3: Rational, n4: Rational): List[Rational] = {
//      List(
//        num2(n1, n2).flatMap(num3(_, n3, n4)),
//        num2(n1, n3).flatMap(num3(_, n2, n4)),
//        num2(n1, n4).flatMap(num3(_, n2, n3)),
//        num2(n2, n3).flatMap(num3(_, n1, n4)),
//        num2(n2, n4).flatMap(num3(_, n1, n3)),
//        num2(n3, n4).flatMap(num3(_, n1, n2))
//      ).flatten
//    }
//
//    def num3(n1: Rational, n2: Rational, n3: Rational): List[Rational] = {
//      List(num2(n1, n2).flatMap(num2(n3, _)), num2(n1, n3).flatMap(num2(n2, _)), num2(n2, n3).flatMap(num2(n1, _))).flatten
//    }
//
//    def num2(n1: Rational, n2: Rational): List[Rational] = {
//      operations.flatMap(op => List(op(n1, n2), op(n2, n1)).flatten).filter(_ > 0)
//    }
//
//    def operations: List[(Rational, Rational) => List[Rational]] =
//      List((a, b) => List(a + b), (a, b) => List(a - b), (a, b) => List(a * b), (a, b) => if (b == 0) Nil else List(a / b))
//
//    //    def testConsecutive(list: List[Int]): Int = {
//    //      if (list.head == 1)
//    //        list.zip(list.tail).takeWhile(pair => pair._2 - pair._1 == 1).size + 1
//    //      else
//    //        0
//    //    }
//
//    val v = for {
//      a <- 1 to 9
//      b <- a to 9
//      c <- b to 9
//      d <- c to 9
//      list = num4(a, b, c, d).filter(_ > 0).distinct.sorted
//      if list.contains(24)
//    } yield ("" + a + "" + b + "" + c + "" + d).toInt
//
//    //    println(num4(1, 1, 2, 7).filter(_ > 0).distinct.sorted)
//
//    println(num4(3, 3, 7, 7).filter(_ > 0).distinct.sorted)
//
//    val real24: Set[Int] = "1118 1126 1127 1128 1129 1134 1135 1136 1137 1138 1139 1144 1145 1146 1147 1148 1149 1155 1156 1157 1158 1166 1168 1169 1188 1224 1225 1226 1227 1228 1229 1233 1234 1235 1236 1237 1238 1239 1244 1245 1246 1247 1248 1249 1255 1256 1257 1258 1259 1266 1267 1268 1269 1277 1278 1279 1288 1289 1333 1334 1335 1336 1337 1338 1339 1344 1345 1346 1347 1348 1349 1356 1357 1358 1359 1366 1367 1368 1369 1377 1378 1379 1388 1389 1399 1444 1445 1446 1447 1448 1449 1455 1456 1457 1458 1459 1466 1467 1468 1469 1477 1478 1479 1488 1489 1555 1556 1559 1566 1567 1568 1569 1578 1579 1588 1589 1599 1666 1668 1669 1679 1688 1689 1699 1779 1788 1789 1799 1888 1889 2223 2224 2225 2227 2228 2229 2233 2234 2235 2236 2237 2238 2239 2244 2245 2246 2247 2248 2249 2255 2256 2257 2258 2259 2266 2267 2268 2269 2277 2278 2288 2289 2333 2335 2336 2337 2338 2339 2344 2345 2346 2347 2348 2349 2355 2356 2357 2358 2359 2366 2367 2368 2369 2377 2378 2379 2388 2389 2399 2444 2445 2446 2447 2448 2449 2455 2456 2457 2458 2459 2466 2467 2468 2469 2477 2478 2479 2488 2489 2499 2557 2558 2559 2566 2567 2568 2569 2577 2578 2579 2588 2589 2666 2667 2668 2669 2678 2679 2688 2689 2699 2778 2788 2789 2888 2889 2899 3333 3334 3335 3336 3337 3338 3339 3344 3345 3346 3347 3348 3349 3355 3356 3357 3359 3366 3367 3368 3369 3377 3378 3379 3388 3389 3399 3444 3445 3446 3447 3448 3449 3455 3456 3457 3458 3459 3466 3468 3469 3477 3478 3479 3489 3499 3556 3557 3558 3559 3566 3567 3568 3569 3578 3579 3588 3589 3599 3666 3667 3668 3669 3677 3678 3679 3688 3689 3699 3777 3778 3779 3788 3789 3799 3888 3889 3899 3999 4444 4445 4446 4447 4448 4449 4455 4456 4457 4458 4468 4469 4477 4478 4479 4488 4489 4555 4556 4557 4558 4559 4566 4567 4568 4569 4577 4578 4579 4588 4589 4599 4666 4667 4668 4669 4677 4678 4679 4688 4689 4699 4777 4778 4788 4789 4799 4888 4889 4899 5555 5556 5559 5566 5567 5568 5577 5578 5588 5589 5599 5666 5667 5668 5669 5677 5678 5679 5688 5689 5699 5779 5788 5789 5888 5889 6666 6668 6669 6679 6688 6689 6789 6799 6888 6889 6899 7889".split(" ").map(_.toInt).toSet
//
//    println(((Rational(3) / Rational(7)) + Rational(3)) * Rational(7))
//
//    println(v.toList.sorted)
//    println(real24.toList.sorted)
//    result(v.toSet -- real24)
//    result(real24 -- v.toSet)
//
//    result(v.sorted)
//    result(v.size)

    ???
  }
}
