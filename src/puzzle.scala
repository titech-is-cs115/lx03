// パス: lx03/src/puzzle.scala
// エンコーディング: UTF8

package puzzle

// おまけの解答例です．
// Scala の Iterator という仕掛け(for (x <- range if cond(x)) yield z)を使うと，このパズルの問題をとてもすっきりと書くことができます．テストはさぼりました．

object Main {
  def c(n: Int)(x1: Int, x2: Int, x3: Int, x4: Int): Int = {
    (if (n == x1) 1 else 0) +
    (if (n == x2) 1 else 0) +
    (if (n == x3) 1 else 0) +
    (if (n == x4) 1 else 0)
  }

  def satisfy(x1: Int, x2: Int, x3: Int, x4: Int): Boolean = {
    val c1 = c(1)(x1, x2, x3, x4)
    val c2 = c(2)(x1, x2, x3, x4)
    val c3 = c(3)(x1, x2, x3, x4)
    val c4 = 4 - (c1 + c2 + c3)
    x1 == 2 + c1 && x2 == 1 + c2 && x3 == 2 + c3 && x4 == c4
  }

  def puzzle(): List[List[Int]] = {
    val range = List.range(0, 9)
    for (x1 <- range;
         x2 <- range;
         x3 <- range;
         x4 <- range
         if satisfy(x1, x2, x3, x4))
      yield List(x1, x2, x3, x4)
  }

  def main() {
    println("Answer = " + puzzle())
  }
}
