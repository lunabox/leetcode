import case.print
import data.structure.BrowserHistory
import solution.*


fun main() {
    val linkNode = LinkProblems()

//    val t = solution.TreeProblems()
//    val tree = t.createTree(arrayListOf(1, 2, 3, null, 4, null, 5))
//    t.levelOrder(tree).forEach {
//        printArray(it.toIntArray())
//    }
//    println(t.isCousins(tree, 4, 5))
    val g = GraphProblems()
//    println(g.checkOverlap(1, 1, 1, 1, -3, 2, -1))

    val sp = StringProblems()
//    println(sp.buddyStrings("ab", "ab"))
//    sp.restoreIpAddresses("0279245587303").forEach {
//        println(it)
//    }
//    println(sp.longestDiverseString(4, 42, 11))
//    println(sp.countCharacters(arrayOf("cat", "bt", "hat", "tree"), "atach"))
//    println(sp.nextGreatestLetter(charArrayOf('a', 'b'), 'z'))
//    println(sp.rotateString("abcde", "cdeab"))
//    sp.letterCombinations("22").forEach {
//        println(it)
//    }

//    sp.generateParenthesis(4).forEach {
//        println(it)
//    }
//    println(sp.letterCasePermutation("s"))
//    println(sp.validPalindrome("abcaa"))
//    sp.largeGroupPositions("abc").forEach {
//        printArray(it.toIntArray())
//    }
//    println(sp.validIPAddress("1081:db8:85a3:01:-0:8A2E:0370:7334"))
//    println(sp.reverseWords(""))
//    println(sp.validIPAddress("00.0.0.0"))

    val np = NumberProblems()
//    np.maxSlidingWindow(intArrayOf(1, 3, -1, -3, 5, 3, 6, 7), 3).print()
//    np.searchRange(intArrayOf(5, 7, 7, 7, 8, 8, 10), 7).print()
//    println(np.multiply("123", "456"))
//    println(np.numSteps("1"))
//    println(np.myAtoi("20000000000000000000"))
//    println(np.findErrorNums(intArrayOf(2, 2)).toList())
//    np.selfDividingNumbers(1, 100).forEach {
//        println(it)
//    }
//    println(np.subsets(intArrayOf(2, 4, 9)))
//    NumberCase.loadIntArray().forEachCase { _, case ->
//        println(np.findShortestSubArray(case))
//    }

//    println(np.findMaxAverage(intArrayOf(1, 12, -5, -6, 50, 3), 4))
//    println(np.getHint("1123", "0111"))
//    repeat(100) {
//        print("$it ${Integer.toBinaryString(it)} ")
//        println(np.hasAlternatingBits(it))
//    }

//    val arr = intArrayOf(1, 2, 3)
//    val arr = intArrayOf(1, 5, 8, 4, 7, 6, 5, 3, 1)
//    np.nextPermutation(arr)
//    println(arr.toList())

//    np.permute(arr).forEach {
//        println(it)
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
//    sim.findDiagonalOrder(arrayListOf(arrayListOf(1,2,3), arrayListOf(4,5,6), arrayListOf(7,8,9))).forEach { println(it) }
//    println(sim.maxDistToClosest(intArrayOf(1, 0, 0, 0, 1, 0, 1)))

//    NumberCase.randomIntArray(0, 10, 10, 5).forEachCase { i, ints ->
//        println(sim.search(ints, ints[0]))
//    }
//    println(sim.coinChange(intArrayOf(2,6), 7))
    val week = WeeklyContest()
    println(week.maxDistance(intArrayOf(1, 2, 3, 4, 7), 3))
//    println(week.getWinner(intArrayOf(2, 1, 3, 5, 4, 6, 7), 2))
//    val dfs = DFSPrograms()
//    println(dfs.canPartition(intArrayOf(1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,100)))
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