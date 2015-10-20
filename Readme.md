# パズルの問題の実施例

ソフトウェアの開発というものは順調に常に前進すると嬉しいものですが、残念ながら必ずしも思いどおりのように進むわけではありません。ときには、午前中に追加したコードを午後になって削除することはしばしばあります。ひどいときは、一週間にわたって開発したことが徒労に終るということもあります。さて、一週間前の状況にソフトウェアを戻そうと思ったときに、一週間分の修正があちこちに点在して混乱するというのもときどきある状況です。

開発中のソフトウェアのある時点での出来栄えのことを**版**と呼びます。版を管理するための素朴な方法は、その都度、すべてのファイルをバックアップしておくことでしょう。Zip形式で `mysoft-20151020.zip` などという名前をつけて管理します。

ただ、この方法ではすぐに版の数が膨れあがり、版と版の間の違いも記憶に頼ることになり、作業も徐々に煩雑になります。

このような版管理の上での問題を解決するソフトウェアの仕組みが版管理システムです。版管理のためのシステムとしては古くはRCS, VCSなどがあり、比較的最近はSubversionが使われてきました。近年はGitとMercurialが人気が高いです。

今日の講義では、パズルの解法について扱いますが、パズルの解法とテストの仕方についてさまざまなアプローチを示します。そのためにGitの版管理の仕掛けを駆使します。

今回，パズルの求解にあたって，Github には3通りの方法を示しています．基本的には全解探索を行っています．全解探索とは，解の可能性のあるすべての要素について，充足性を検査し，充足するものの集合を解として提示する方法です．パズルの問題において，紙の穴を埋める4つの数字の組み合わせが解の空間にあたります．穴が1文字の数とすれば，解の候は穴を埋める4つの整数です．各整数が[0,9]の範囲の整数とするなら，解の可能性は10×10×10×10通りあります．

1. 解の候補は4つの数のリストで表し，解の可能性のあるすべての候補群はリストのリスト(List[List[Int]])として表現します．パズルのプログラムをScalaTestでテストしようと思うと，正解を知らずにテストしなくてはならないという矛盾した状況に身を置くことになります．この解法では，プログラムを意味のある（そしてテストしやすい）単位に分解することによって，綿密なテストが可能になることを示します．

    詳しくは，[最初の例: feature/plan1 (青い線)](https://github.com/titech-is-cs115/lx03/network)をご覧下さい．それぞれの青い点が，コミットです．12個の点がありますが，この各点がプログラムの作成過程における作業段階を表しています．この点をクリックすることで，該当する段階のプログラムとテストコードを見ることができます．

1. 前述の作例では，プログラムがかなり複雑になってしまいました．頭を整理しなおして，プログラムを一から作り直したものが[二番目の例: feature/plan2 (緑の線)](https://github.com/titech-is-cs115/lx03/network)です．

1. 最後の例は，みなさんの多くがご存知ないだろうSequence comprehensionと呼ばれる機能を用いて，さらに簡潔に記述できることを示しています．再帰的関数をいくつも使って記述していたのが馬鹿馬鹿しくなるほど簡単な記述になります．[三番目の例: feature/plan3 (青い線)](https://github.com/titech-is-cs115/lx03/network)をご覧下さい．
