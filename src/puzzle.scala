// パス: lx03/src/puzzle.scala
// エンコーディング: UTF8

package puzzle

object Main {
  /*
   * 戦略：{ [a, b, c, d] in [0,9]^4 | constraint([a, b, c, d]) }
   * つまり、[0, 9]の範囲の整数 a, b, c, d のすべての組のリスト(candidates)を生成し、それらのうち制約 constraint を充足するものを見つける。
   */

  def candidates(): List[List[Int]] = {
    Nil
  }

  def constraint(candidate: List[Int]): Boolean = {
    true
  }

  def check(): List[List[Int]] = {
    Nil
  }

  def puzzle(): List[List[Int]] = {
    check()
  }

  def main() {
    println("Answer = " + puzzle())
  }
}
