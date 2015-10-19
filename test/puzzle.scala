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

  "constraint(a, b, c, d)" should "reflect the specification" in {
    constraint(List(0, 0, 0, 0)) should be (false)
    constraint(List(1, 0, 0, 0)) should be (false)
    constraint(List(0, 1, 0, 0)) should be (false)
    constraint(List(0, 0, 1, 0)) should be (false)
    constraint(List(0, 0, 0, 1)) should be (false)
    // と、結果が false になるテストは書いてみた
    // でも、結果が true になるテストはどうする？
    // もし書いたとしたら、それって、すでに結果が求まっているってこと？
  }

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
