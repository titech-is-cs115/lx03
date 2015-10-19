# パズルの問題の実施例

ソフトウェアの開発というものは順調に常に前進すると嬉しいものですが、残念ながら必ずしも思いどおりのように進むわけではありません。ときには、午前中に追加したコードを午後になって削除することはしばしばあります。ひどいときは、一週間にわたって開発したことが徒労に終るということもあります。さて、一週間前の状況にソフトウェアを戻そうと思ったときに、一週間分の修正があちこちに点在して混乱するというのもときどきある状況です。

開発中のソフトウェアのある時点での出来栄えのことを**版**と呼びます。版を管理するための素朴な方法は、その都度、すべてのファイルをバックアップしておくことでしょう。Zip形式で `mysoft-20151020.zip` などという名前をつけて管理します。

ただ、この方法ではすぐに版の数が膨れあがり、版と版の間の違いも記憶に頼ることになり、作業も徐々に煩雑になります。

このような版管理の上での問題を解決するソフトウェアの仕組みが版管理システムです。版管理のためのシステムとしては古くはRCS, VCSなどがあり、比較的最近はSubversionが使われてきました。近年はGitとMercurialが人気が高いです。

今日の講義では、パズルの解放について扱いますが、パズルの解法とテストの仕方についてさまざまなアプローチを示します。そのためにGitの版管理の仕掛けを駆使します。