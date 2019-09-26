fun main() {
    val lc = LeetCodeKt()
    val s = "2-5g-3-J"
//    println(leetCodeKt.licenseKeyFormatting(s, 2))
//    (0..30).forEach {
//        println("$it: ${lc.fib(it)}")
//    }
    lc.threeSum(intArrayOf(-1, 0, 1, 2, -1, -4)).forEach {
        printArray(it.toIntArray())
    }
}


fun printArray(array: IntArray) {
    (0 until array.size).forEach {
        print(array[it])
        if (it == array.size - 1) {
            println()
        } else {
            print(", ")
        }
    }
}