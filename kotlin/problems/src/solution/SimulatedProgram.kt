package solution

import kotlin.math.abs
import kotlin.math.max
import kotlin.math.min

class SimulatedProgram {

    /**
     * https://leetcode-cn.com/problems/robot-return-to-origin/
     */
    fun judgeCircle(moves: String): Boolean {
        var x = 0
        var y = 0
        moves.forEach {
            when (it) {
                'R' -> x++
                'L' -> x--
                'D' -> y--
                'U' -> y++
            }
        }
        return x == 0 && y == 0
    }

    fun coinChange(coins: IntArray, amount: Int): Int {
        // 缓存钱数对应的最小硬币数
        val coinsMin = IntArray(amount + 1) { 0 }

        for (i in 1..amount) {
            var minCount = Int.MAX_VALUE
            coins.filter {
                it <= i
            }.forEach {
                val curUsed = coinsMin[i - it] + 1
                if (coinsMin[i - it] >= 0 && curUsed < minCount) {
                    minCount = curUsed
                }
            }
            coinsMin[i] = if (minCount != Int.MAX_VALUE) {
                minCount
            } else {
                -1
            }
        }
        return coinsMin[amount]
    }

    /**
     * https://leetcode-cn.com/problems/minimum-index-sum-of-two-lists/
     */
    fun findRestaurant(list1: Array<String>, list2: Array<String>): Array<String> {
        var minStep = Int.MAX_VALUE
        val hash = HashMap<String, Int>(list1.size)
        val result = HashMap<String, Int>()
        list1.forEachIndexed { index, s ->
            hash[s] = index
        }
        list2.forEachIndexed { index, s ->
            if (s in hash) {
                if (index + hash[s]!! < minStep) {
                    minStep = index + hash[s]!!
                }
                result[s] = hash[s]!! + index
            }
        }

        return result.filter { it.value == minStep }.keys.toTypedArray()
    }

    /**
     * https://leetcode-cn.com/problems/binary-search/
     */
    fun search(nums: IntArray, target: Int): Int {
        var left = 0
        var right = nums.lastIndex
        while (left <= right) {
            val middle = (left + right) / 2
            when {
                target < nums[middle] -> right = middle - 1
                target > nums[middle] -> left = middle + 1
                else -> return middle
            }
        }
        return -1
    }

    /**
     * https://leetcode-cn.com/problems/rectangle-overlap/
     */
    fun isRectangleOverlap(rec1: IntArray, rec2: IntArray): Boolean {
        val w1 = (rec1[2] - rec1[0]).toLong()
        val h1 = (rec1[3] - rec1[1]).toLong()
        val xm1 = (rec1[2] + rec1[0]).toLong()
        val ym1 = (rec1[3] + rec1[1]).toLong()

        val w2 = (rec2[2] - rec2[0]).toLong()
        val h2 = (rec2[3] - rec2[1]).toLong()
        val xm2 = (rec2[2] + rec2[0]).toLong()
        val ym2 = (rec2[3] + rec2[1]).toLong()

        return (abs(xm1 - xm2) < (w1 + w2)) && (abs(ym1 - ym2) < (h1 + h2))
    }

    /**
     * https://leetcode-cn.com/problems/rectangle-area/
     * 在二维平面上计算出两个由直线构成的矩形重叠后形成的总面积。
     */
    fun computeArea(A: Int, B: Int, C: Int, D: Int, E: Int, F: Int, G: Int, H: Int): Int {
        val lx = max(A, E).toLong()
        val ly = max(B, F).toLong()
        val rx = min(C, G).toLong()
        val ry = min(D, H).toLong()
        val area1 = (C - A).toLong() * (D - B)
        val area2 = (G - E).toLong() * (H - F)

        // 未相交
        if (E > C || F > D || G < A || H < B) {
            return (area1 + area2).toInt()
        }
        return (area1 + area2 - (rx - lx) * (ry - ly)).toInt()
    }

    /**
     * https://leetcode-cn.com/problems/available-captures-for-rook/
     * 车的可用捕获量
     */
    fun numRookCaptures(board: Array<CharArray>): Int {
        var rx = 0
        var ry = 0
        var result = 0
        board.forEachIndexed { indexArray, chars ->
            chars.forEachIndexed { indexChar, c ->
                if (c == 'R') {
                    rx = indexChar
                    ry = indexArray
                }
            }
        }
        right@ for (i in rx + 1 until board.size) {
            when (board[ry][i]) {
                'p' -> {
                    result++
                    break@right
                }
                '.' -> continue@right
                'B', 'b', 'P' -> break@right
            }
        }
        left@ for (i in rx - 1 downTo 0) {
            when (board[ry][i]) {
                'p' -> {
                    result++
                    break@left
                }
                '.' -> continue@left
                'B', 'b', 'P' -> break@left
            }
        }
        up@ for (j in ry - 1 downTo 0) {
            when (board[j][rx]) {
                'p' -> {
                    result++
                    break@up
                }
                '.' -> continue@up
                'B', 'b', 'P' -> break@up
            }
        }

        down@ for (j in ry + 1 until board.size) {
            when (board[j][rx]) {
                'p' -> {
                    result++
                    break@down
                }
                '.' -> continue@down
                'B', 'b', 'P' -> break@down
            }
        }
        return result
    }

    /**
     * https://leetcode-cn.com/problems/unique-morse-code-words/
     */
    fun uniqueMorseRepresentations(words: Array<String>): Int {
        val morse = arrayOf(
            ".-",
            "-...",
            "-.-.",
            "-..",
            ".",
            "..-.",
            "--.",
            "....",
            "..",
            ".---",
            "-.-",
            ".-..",
            "--",
            "-.",
            "---",
            ".--.",
            "--.-",
            ".-.",
            "...",
            "-",
            "..-",
            "...-",
            ".--",
            "-..-",
            "-.--",
            "--.."
        )
        val set = HashSet<String>()
        words.forEach words@{ word ->
            val code = StringBuffer()
            word.forEach {
                code.append(morse[it - 'a'])
            }
            set.add(code.toString())
        }
        return set.size
    }
}