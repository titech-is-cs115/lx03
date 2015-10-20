// パス: lx03/test/puzzle.scala
// エンコーディング: UTF8

package puzzle

import org.scalatest._
import Main._

class Test extends FlatSpec with Matchers {
  "counts" should "count correctly" in {
    counts(Nil) should be (List(0, 0, 0, 0))
    counts(List(1)) should be (List(1, 0, 0, 0))
    counts(List(1, 1, 1, 1, 1)) should be (List(5, 0, 0, 0))
    counts(List(2, 2, 2, 2, 2)) should be (List(0, 5, 0, 0))
    counts(List(3, 3, 3, 3, 3)) should be (List(0, 0, 5, 0))
    counts(List(0, 0, 0, 0, 0)) should be (List(0, 0, 0, 5))
    counts(List(0, 4, 5, 6, 7, 8, 9)) should be (List(0, 0, 0, 7))

    countsOnPaper should be (List(2, 1, 2, 0))
  }

  "main" should "be forced to run" in { // テストのなかで強制的に main を実行させているだけで、あまり意味はありません。
    main() should be (())
  }
}
