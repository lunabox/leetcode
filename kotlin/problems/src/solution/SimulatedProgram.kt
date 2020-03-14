package solution

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
}