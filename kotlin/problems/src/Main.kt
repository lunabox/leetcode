import case.NumberCase
import solution.NumberProblems
import solution.SimulatedProgram
import solution.StringProblems

fun main() {

//    val t = solution.TreeProblems()
//    val tree = t.createTree(arrayListOf(1, 2, 3, null, 4, null, 5))
//    t.levelOrder(tree).forEach {
//        printArray(it.toIntArray())
//    }
//    println(t.isCousins(tree, 4, 5))

    val sp = StringProblems()
//    println(sp.toLowerCase("HHLKK1211ss ss"))
//    println(sp.validPalindrome("abcaa"))
//    sp.largeGroupPositions("abc").forEach {
//        printArray(it.toIntArray())
//    }
//    println(sp.validIPAddress("1081:db8:85a3:01:-0:8A2E:0370:7334"))
//    println(sp.reverseWords(""))
//    println(sp.validIPAddress("00.0.0.0"))

    val np = NumberProblems()
    NumberCase.loadIntArray().forEach {
        print("${it.toList()} -> ")
        println(np.checkPossibility(it))
    }
//    println(np.findMaxAverage(intArrayOf(1, 12, -5, -6, 50, 3), 4))
//    println(np.getHint("1123", "0111"))
//    repeat(100) {
//        print("$it ${Integer.toBinaryString(it)} ")
//        println(np.hasAlternatingBits(it))
//    }

//    val arr = intArrayOf(1, 5, 1)
//    val arr = intArrayOf(1, 5, 8, 4, 7, 6, 5, 3, 1)
//    np.nextPermutation(arr)
//    println(arr.toList())

//    var obj = SnapshotArray(5)
//    obj.set(2, 15)
//    var snap1 = obj.snap()
//    var param_3 = obj.get(2, snap1)
//    println(param_3)
//
//    obj.set(2, 10)
//    val snap2 = obj.snap()
//    println(obj.get(2, snap2))

//    var obj = RangeModule()
//    obj.addRange(left, right)
//    var param_2 = obj.queryRange(left, right)
//    obj.removeRange(left, right)
//    val simulatedProgram = solution.SimulatedProgram()
//    println(simulatedProgram.judgeCircle("UDRL"))

    val sim = SimulatedProgram()
//    println(sim.coinChange(intArrayOf(2,6), 7))
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