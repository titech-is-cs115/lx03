// パス: lx03/test/puzzle.scala
// エンコーディング: UTF8

package puzzle

import org.scalatest._
import Main._

class Test extends FlatSpec with Matchers {

  "fromTo()" should "give number sequence in increasing order" in {
    fromTo(0, 0) should be  (List(0))
    fromTo(0, 3) should be  (List(0, 1, 2, 3))
    fromTo(0, -1) should be (Nil)
  }

  "prod()" should "compute Cartesian product" in {
    prod(List(0), Nil) should be (List(List(0)))

    val prod1 = prod(List(0, 1), Nil)
    prod1 should contain (List(0))
    prod1 should contain (List(1))

    prod(Nil, List(List(0), List(1), List(2), List(3))) should be (Nil)

    val prod3 = prod(List(0, 1), List(List(2), List(3)))
    prod3 should contain (List(0, 2))
    prod3 should contain (List(0, 3))
    prod3 should contain (List(1, 2))
    prod3 should contain (List(1, 3))
  }

  "candidates()" should "be [[0,0,0,0], [0,0,0,1], [0,0,0,2], ..., [9,9,9,9]]" in {
    val candidates2 = candidates(1)
    println("candidates2 = " + candidates2)
    candidates2.length should be (4)

    val candidates10 = candidates(9)
    candidates10 should contain     (List(0, 0, 0, 0))
    candidates10 should contain     (List(9, 9, 9, 9))
    candidates10 should not contain (List(0, 0, 0, -1))
    candidates10 should not contain (List(0, 0, 0, 10))

    candidates10.length should be (10 * 10 * 10 * 10)
  }

  "counts([...])" should "count correctly" in {
    counts(Nil) should be (List(0, 0, 0, 0))
    counts(List(1)) should be (List(1, 0, 0, 0))
    counts(List(1, 1, 1, 1, 1)) should be (List(5, 0, 0, 0))
    counts(List(2, 2, 2, 2, 2)) should be (List(0, 5, 0, 0))
    counts(List(3, 3, 3, 3, 3)) should be (List(0, 0, 5, 0))
    counts(List(0, 0, 0, 0, 0)) should be (List(0, 0, 0, 5))
    counts(List(0, 4, 5, 6, 7, 8, 9)) should be (List(0, 0, 0, 7))
  }

  // constraintの実装は仕様どおりなので、テストなしで信用することにする

  def every(p: List[Int] => Boolean, list: List[List[Int]]): Boolean = {
    list match {
      case Nil => true
      case first :: rest => p(first) && every(p, rest)
    }
  }

  "check()" should "choose all the candidates that meet the constraint" in {
    // check() の結果がすべて constraint を充足すること
    every(constraint, check()) should be (true)

    // candidates() のうち、constraint を充足するもので check() に漏れているものがないこと
    // うっ、どうやって書く？？？
  }

  "main" should "is forced to run" in { // テストのなかで強制的に main を実行させているだけで、あまり意味はありません。
    main() should be (())
  }
}
