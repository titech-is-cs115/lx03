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

  def prod(l: List[Int], ll: List[List[Int]]): List[List[Int]] = {
    def prod0(l: List[Int]): List[List[Int]] = {
      l match {
        case Nil => Nil
        case x :: l => List(x) :: prod0(l)
      }
    }

    def prod1(n: Int, ll: List[List[Int]]): List[List[Int]] = {
      ll match {
        case Nil => Nil
        case l :: ll => (n :: l) :: prod1(n, ll)
      }
    }

    ll match {
      case Nil => prod0(l)
      case _ =>
        l match {
          case Nil => Nil
          case x :: l => prod1(x, ll) ++ prod(l, ll)
        }
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
        a == 2 + c1 && b == 1 + c2 && c == 2 + c3 && d == c_others
    }
  }

  def check(): List[List[Int]] = {
    def loop(candidates: List[List[Int]]): List[List[Int]] = {
      candidates match {
        case Nil => Nil
        case candidate :: candidates =>
          if (constraint(candidate)) candidate :: loop(candidates)
          else loop(candidates)
      }
    }
    loop(candidates(9))
  }

  def puzzle(): List[List[Int]] = {
    check()
  }

  def main() {
    println("Answer = " + puzzle())
  }
}
