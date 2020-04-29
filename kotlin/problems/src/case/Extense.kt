package case


/**
 * 扩展IntArray的打印方法
 */

inline fun <T> Iterable<T>.print() {
    val list = this.toList()
    list.forEachIndexed { index, t ->
        if (index == 0) {
            print("[")
        }
        print(t)
        if (index == list.size - 1) {
            println("]")
        } else {
            print(",")
        }
    }
}

inline fun IntArray.print() {
    this.forEachIndexed { index, i ->
        if (index == 0) {
            print("[")
        }
        print(i)
        if (index == this.size - 1) {
            println("]")
        } else {
            print(",")
        }
    }
}