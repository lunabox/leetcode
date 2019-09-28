fun main() {
//    val lc = LeetCodeKt()
//    val s = "2-5g-3-J"
//    println(leetCodeKt.licenseKeyFormatting(s, 2))
//    (0..30).forEach {
//        println("$it: ${lc.fib(it)}")
//    }
//    val r = lc.threeSumClosest(intArrayOf(1, 1, -1, -1, 3), -1)
//    println(r)

    val t = TreeProblems()
    val tree = t.createTree(arrayListOf(1, 2, 3, null, 4, null, 5))
//    t.levelOrder(tree).forEach {
//        printArray(it.toIntArray())
//    }
    println(t.isCousins(tree, 4, 5))
}


fun printArray(array: IntArray) {
    (array.indices).forEach {
        print(array[it])
        if (it == array.size - 1) {
            println()
        } else {
            print(", ")
        }
    }
}