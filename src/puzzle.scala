// パス: lx03/src/puzzle.scala
// エンコーディング: UTF8

package puzzle

object Main {
  /*
   * 戦略：さきほどはすべての組合せを列挙する部分と制約の記述にバグをいれてしまった．
   * もっと簡単な実装を目指す．問題が小さいから，性能に与える影響は限定的ですが，
   * 列挙によって，かなりな記憶領域を消費しています．また，一部で手抜きをしてリストの
   * 連結を使ってしまっているので，ゴミも発生しています．それらの問題を抑制することも
   * あわせて心掛けます．
   */

  def c(n: Int)(nums: List[Int]): Int = {
    nums match {
      case Nil => 0
      case x :: l => (if (x == n) 1 else 0) + c(n)(l)
    }
  }

  def counts(nums: List[Int]): List[Int] = {
    val c1 = c(1)(nums)
    val c2 = c(2)(nums)
    val c3 = c(3)(nums)
    val c_ = nums.length - (c1 + c2 + c3)
    List(c1, c2, c3, c_)
  }

  val countsOnPaper = counts(List(1, 2, 3, 1, 3))

  def satisfy(candidate: List[Int]): Boolean = {
    (candidate, counts(candidate), countsOnPaper) match {
      case (List(x1, x2, x3, x_), List(n1, n2, n3, n_), List(_n1, _n2, _n3, _n_)) =>
        // 穴の値 = 穴の数字の集計 + 紙に書かれた数字の集計
        x1 == n1 + _n1 &&
        x2 == n2 + _n2 &&
        x3 == n3 + _n3 &&
        x_ == n_ + _n_
    }
  }

  def puzzle(): List[List[Int]] = {
    def N = 10 * 10 * 10 * 10
    def genCheck(i: Int, answers: List[List[Int]]): List[List[Int]] = {
      if (i >= N) answers
      else {
        val x1 = i / 1000
        val x2 = (i / 100) % 10
        val x3 = (i / 10) % 10
        val x4 = i % 10
        val candidate = List(x1, x2, x3, x4)
        if (satisfy(candidate)) genCheck(i + 1, candidate :: answers)
        else genCheck(i + 1, answers)
      }
    }
    genCheck(0, Nil)
  }

  def main() {
    println("Answer = " + puzzle())
  }
}
