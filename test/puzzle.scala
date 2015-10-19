// パス: lx03/test/puzzle.scala
// エンコーディング: UTF8

package puzzle

import org.scalatest._
import Main._

class Test extends FlatSpec with Matchers {

  val theCandidates = candidates()

  "candidates()" should "be [[0,0,0,0], [0,0,0,1], [0,0,0,2], ..., [9,9,9,9]]" in {
    theCandidates should contain (List(0, 0, 0, 0))
    theCandidates should contain (List(9, 9, 9, 9))
    theCandidates should not contain (List(0, 0, 0, -1))
    theCandidates should not contain (List(0, 0, 0, 10))
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
