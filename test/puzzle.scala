// パス: lx03/test/puzzle.scala
// エンコーディング: UTF8

package puzzle

import org.scalatest._
import Main._

class Test extends FlatSpec with Matchers {
  "main" should "be forced to run" in { // テストのなかで強制的に main を実行させているだけで、あまり意味はありません。
    main() should be (())
  }
}
