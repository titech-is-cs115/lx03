// パス: lx03/src/puzzle.scala
// エンコーディング: UTF8

package puzzle

object Main {
  /*
   * 戦略：{ [a, b, c, d] in [0,9]^4 | constraint([a, b, c, d]) }
   * つまり、[0, 9]の範囲の整数 a, b, c, d のすべての組のリスト(candidates)を生成し、それらのうち制約 constraint を充足するものを見つける。
   *
   * constraintがテストし難いので、制約の書き方を変えてみる。升目に含まれる数に対する制約ではなく、紙面のones = 1の数、twos = 2の数、threes = 3の数、others = それら以外の数についての情報が与えられた制約とすればテストが書き易くなるかな？
   */

  // candidates = [0,9] x ([0,9] x ([0,9] x [0,9])) だから
  // 二つのリストからデカルト積を与える x 演算子と[0,9]の範囲を与えるfromToを定義することにする。

  def fromTo(n: Int, m: Int): List[Int] = {
    if (n > m) Nil else n :: fromTo(n+1, m)
  }

  def prod(l1: List[Int], ll2: List[List[Int]]): List[List[Int]] = {
    def prod1(n: Int, ll: List[List[Int]]): List[List[Int]] = {
      ll match {
        case Nil => List(List(n))
        case l :: ll => (n :: l) :: prod1(n, ll)
      }
    }
    l1 match {
      case Nil => Nil
      case x :: l1 => prod1(x, ll2) ++ prod(l1, ll2)
    }
  }

  def candidates(n: Int): List[List[Int]] = {
    val nums = fromTo(0, n)
    prod(nums, (prod(nums, (prod(nums, prod(nums, Nil))))))
  }

  def counts(candidate: List[Int]): List[Int] = {
    def c(n: Int)(l: List[Int]): Int = {
      l match {
        case Nil => 0 
        case x :: l => (if (x == n) 1 else 0) + c(n)(l)
      }
    }
    val c1 = c(1)(candidate)
    val c2 = c(2)(candidate)
    val c3 = c(3)(candidate)
    val c_others = candidate.length - (c1 + c2 + c3)
    List(c1, c2, c3, c_others)
  }

  /**
   * [a, b, c, d], [ones, twos, threes, others] => true/false
   */
  def constraint(candidate: List[Int]): Boolean = {
    (candidate, counts(candidate)) match {
      case (List(a, b, c, d), List(c1, c2, c3, c_others)) =>
        a == c1 && b == c2 && c == c3 && d == c_others
    }
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
