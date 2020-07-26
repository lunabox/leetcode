package solution

import kotlin.math.abs
import kotlin.math.max

class WeeklyContest {

    /**
     * https://leetcode-cn.com/contest/weekly-contest-187/problems/destination-city/
     */
    fun destCity(paths: List<List<String>>): String {
        val vote = HashMap<String, Int>()
        paths.forEach {
            vote[it[0]] = vote[it[0]]?.minus(1) ?: -1
            vote[it[1]] = vote[it[1]]?.plus(1) ?: 1
        }
        for (key in vote.keys) {
            if (vote[key] == 1) {
                return key
            }
        }
        return ""
    }

    /**
     * https://leetcode-cn.com/contest/weekly-contest-187/problems/check-if-all-1s-are-at-least-length-k-places-away/
     */
    fun kLengthApart(nums: IntArray, k: Int): Boolean {
        val list = mutableListOf<Int>()
        nums.forEachIndexed { index, i ->
            if (i == 1) {
                list.add(index)
            }
        }
        for (i in 0 until list.size - 1) {
            if (list[i + 1] - list[i] - 1 < k) {
                return false
            }
        }
        return true
    }

    /**
     * https://leetcode-cn.com/contest/weekly-contest-188/problems/build-an-array-with-stack-operations/
     */
    fun buildArray(target: IntArray, n: Int): List<String> {
        val ans = mutableListOf<String>()
        var index = 0
        repeat(n) {
            if (index == target.size) {
                return@repeat
            }
            ans.add("Push")
            if (target.indexOf(it + 1) == -1) {
                ans.add("Pop")
            } else {
                index++
            }
        }
        return ans
    }

    /**
     * https://leetcode-cn.com/contest/weekly-contest-188/problems/count-triplets-that-can-form-two-arrays-of-equal-xor/
     */
    fun countTriplets(arr: IntArray): Int {
        val a = IntArray(arr.size)
        var ans = 0
        for (i in arr.indices) {
            if (i == 0) {
                a[i] = arr[0]
            } else {
                a[i] = a[i - 1].xor(arr[i])
            }
        }

        for (i in 0 until arr.size - 1) {
            for (k in i + 1 until arr.size) {
                for (j in i + 1..k) {
                    val left = if (i == 0) {
                        a[j - 1]
                    } else {
                        a[j - 1].xor(a[i - 1])
                    }
                    val right = a[k].xor(a[j - 1])
                    if (left == right) {
                        ans++
                    }
                }
            }
        }
        return ans
    }

    /**
     * https://leetcode-cn.com/contest/weekly-contest-190/problems/check-if-a-word-occurs-as-a-prefix-of-any-word-in-a-sentence/
     */
    fun isPrefixOfWord(sentence: String, searchWord: String): Int {
        var firstIndex = -1
        sentence.split(" ").forEachIndexed { index, s ->
            if (s.startsWith(searchWord)) {
                if (firstIndex >= 0) {
                    return firstIndex + 1
                } else {
                    firstIndex = index
                }
            }
        }
        return if (firstIndex == -1) -1 else firstIndex + 1
    }

    /**
     * https://leetcode-cn.com/contest/weekly-contest-190/problems/maximum-number-of-vowels-in-a-substring-of-given-length/
     */
    fun maxVowels(s: String, k: Int): Int {
        val vowels = "aeiou"
        var maxCount = 0
        repeat(k) {
            if (s[it] in vowels) {
                maxCount++
            }
        }
        var currentCount = maxCount
        for (i in 1..s.length - k) {
            if (s[i - 1] in vowels) {
                currentCount--
            }
            if (s[i + k - 1] in vowels) {
                currentCount++
            }
            maxCount = max(currentCount, maxCount)
        }
        return maxCount
    }

    /**
     * https://leetcode-cn.com/contest/weekly-contest-191/problems/maximum-product-of-two-elements-in-an-array/
     */
    fun maxProduct(nums: IntArray): Int {
        var ans = -1
        for (i in nums.indices) {
            for (j in i + 1 until nums.size) {
                ans = max(ans, (nums[i] - 1) * (nums[j] - 1))
            }
        }
        return ans
    }

    /**
     * https://leetcode-cn.com/contest/weekly-contest-191/problems/maximum-area-of-a-piece-of-cake-after-horizontal-and-vertical-cuts/
     */
    fun maxArea(h: Int, w: Int, horizontalCuts: IntArray, verticalCuts: IntArray): Int {
        horizontalCuts.sort()
        var maxH = horizontalCuts.first()
        for (i in 1 until horizontalCuts.size) {
            maxH = max(maxH, horizontalCuts[i] - horizontalCuts[i - 1])
        }
        maxH = max(maxH, h - horizontalCuts.last())

        verticalCuts.sort()
        var maxV = verticalCuts.first()
        for (i in 1 until verticalCuts.size) {
            maxV = max(maxV, verticalCuts[i] - verticalCuts[i - 1])
        }
        maxV = max(maxV, w - verticalCuts.last())
        return ((maxH.toLong() * maxV.toLong()) % 1000000007L).toInt()
    }

    /**
     * https://leetcode-cn.com/contest/weekly-contest-192/problems/shuffle-the-array/
     */
    fun shuffle(nums: IntArray, n: Int): IntArray {
        val ans = IntArray(nums.size)
        var index = 0
        repeat(n) {
            ans[index++] = nums[it]
            ans[index++] = nums[it + n]
        }
        return ans
    }

    /**
     * https://leetcode-cn.com/contest/weekly-contest-192/problems/the-k-strongest-values-in-an-array/
     */
    fun getStrongest(arr: IntArray, k: Int): IntArray {
        arr.sort()
        val m = arr[(arr.size - 1) / 2]
        return arr.sortedWith(Comparator { o1, o2 ->
            val s1 = abs(o1 - m)
            val s2 = abs(o2 - m)
            if (s1 == s2) {
                return@Comparator if (o1 > o2) {
                    -1
                } else {
                    1
                }
            } else {
                return@Comparator if (s1 > s2) {
                    -1
                } else {
                    1
                }
            }
        }).subList(0, k).toIntArray()
    }

    /**
     *
     */
    fun canMakeArithmeticProgression(arr: IntArray): Boolean {
        arr.sort()
        val step = arr[1] - arr[0]
        for (i in 1 until arr.size) {
            if (arr[i] - arr[i - 1] != step) {
                return false
            }
        }
        return true
    }

    /**
     * https://leetcode-cn.com/contest/weekly-contest-196/problems/last-moment-before-all-ants-fall-out-of-a-plank/
     */
    fun getLastMoment(n: Int, left: IntArray, right: IntArray): Int {
        var t = 0
        val leftAnt = left.toMutableList()
        val rightAnt = right.toMutableList()
        while (leftAnt.isNotEmpty() || rightAnt.isNotEmpty()) {
            t++
            var removeIndex = -1
            leftAnt.forEachIndexed { index, i ->
                if (i == 0) {
                    removeIndex = index
                } else {
                    leftAnt[index] = i - 1
                }
            }
            if (removeIndex >= 0) {
                leftAnt.removeAt(removeIndex)
            }
            removeIndex = -1
            rightAnt.forEachIndexed { index, i ->
                if (i == n) {
                    removeIndex = index
                } else {
                    rightAnt[index] = i + 1
                }
            }
            if (removeIndex >= 0) {
                rightAnt.removeAt(removeIndex)
            }
        }
        return t - 1
    }

    /**
     * https://leetcode-cn.com/contest/weekly-contest-196/problems/count-submatrices-with-all-ones/
     */
    fun numSubmat(mat: Array<IntArray>): Int {
        val n = Array(mat.size + 1) { IntArray(mat[0].size + 1) { 0 } }
        //预处理
        for (i in 1 until mat.size) {
            for (j in 1 until mat.size) {
                if (mat[i][j] == 1) {
                    n[i][j] = n[i - 1][j] + 1
                } else {
                    n[i][j] = 0
                }
            }
        }
        var ans = 0
        var cnt = 0
        val stack = IntArray(100000) { 0 }
        var top = 0
        for (i in 1..mat.size) {
            cnt = 0
            top = 0
            for (j in 1..mat[0].size) {
                cnt += n[i][j]
                while (top != 0 && n[i][j] <= n[i][stack[top]]) {
                    cnt -= (stack[top] - stack[top - 1]) * (n[i][stack[top]] - n[i][j])
                    //(栈顶元素) - (第二大的元素)   =  距离
                    //(栈顶元素) - (当前元素)  = 差值
                    // 距离 × 差值 = 不作贡献的个数
                    top--
                }
                ans += cnt
                stack[++top] = j
            }
        }
        return ans
    }

    /**
     * https://leetcode-cn.com/contest/weekly-contest-197/problems/number-of-good-pairs/
     */
    fun numIdenticalPairs(nums: IntArray): Int {
        var ans = 0
        for (i in nums.indices) {
            for (j in i + 1 until nums.size) {
                if (nums[i] == nums[j]) {
                    ans++
                }
            }
        }
        return ans
    }

    /**
     * https://leetcode-cn.com/contest/weekly-contest-197/problems/number-of-substrings-with-only-1s/
     */
    fun numSub(s: String): Int {
        var ans = 0L
        s.split("0").filter { it.isNotEmpty() }.forEach {
            for (i in 1..it.length) {
                ans += (it.length - i + 1)
            }
        }
        return (ans % 1000000007).toInt()
    }

    private var probabilityAns = 0.0

    /**
     *
     */
    fun maxProbability(n: Int, edges: Array<IntArray>, succProb: DoubleArray, start: Int, end: Int): Double {
        val matrix = Array(n) {
            DoubleArray(it + 1) { 0.0 }
        }
        edges.forEachIndexed { index, ints ->
            if (ints[0] > ints[1]) {
                matrix[ints[0]][ints[1]] = succProb[index]
            } else {
                matrix[ints[1]][ints[0]] = succProb[index]
            }
        }
        probabilityDfs(matrix, start, end, 1.0)
        return probabilityAns
    }

    private fun probabilityDfs(matrix: Array<DoubleArray>, start: Int, end: Int, maxPath: Double) {
        if (start == end) {
            probabilityAns = max(probabilityAns, maxPath)
            return
        }
        for (i in matrix.indices) {
            if (i > start && matrix[i][start] > 0) {
                probabilityDfs(matrix, i, end, matrix[i][start] * maxPath)
            } else if (i < start && matrix[start][i] > 0) {
                probabilityDfs(matrix, i, end, matrix[start][i] * maxPath)
            }
        }
    }

    fun numWaterBottles(numBottles: Int, numExchange: Int): Int {
        var ans = numBottles
        var empty = numBottles
        while (empty / numExchange >= 1) {
            ans += empty / numExchange
            empty = empty / numExchange + empty % numExchange
        }
        return ans
    }

    fun countSubTrees(n: Int, edges: Array<IntArray>, labels: String): IntArray {
        val ans = IntArray(n) { 0 }
        val reach = IntArray(edges.size) { 0 }
        countSubTreesDfs(0, edges, labels, ans, reach)
        return ans
    }

    private fun countSubTreesDfs(
        currentPoint: Int,
        edges: Array<IntArray>,
        labels: String,
        ans: IntArray,
        reach: IntArray
    ): Map<Char, Int> {
        ans[currentPoint] = 1
        var hasSub = false
        val currentMap = mutableMapOf(Pair(labels[currentPoint], 1))
        for (i in edges.indices) {
            if ((edges[i][0] == currentPoint || edges[i][1] == currentPoint) && reach[i] == 0) {
                reach[i] = 1
                val map = if (edges[i][0] == currentPoint) {
                    countSubTreesDfs(edges[i][1], edges, labels, ans, reach)
                } else {
                    countSubTreesDfs(edges[i][0], edges, labels, ans, reach)
                }
                ans[currentPoint] += map[labels[currentPoint]] ?: 0
                hasSub = true

                map.keys.forEach {
                    currentMap[it] = currentMap[it]?.plus(map[it]!!) ?: map[it]!!
                }
            }
        }
        if (!hasSub) {
            return mapOf(Pair(labels[currentPoint], 1))
        }
        return currentMap
    }

    /**
     * https://leetcode-cn.com/contest/weekly-contest-199/problems/shuffle-string/
     */
    fun restoreString(s: String, indices: IntArray): String {
        val ans = CharArray(s.length)
        indices.forEachIndexed { index, i ->
            ans[i] = s[index]
        }
        return String(ans)
    }

    /**
     *
     */
    fun minFlips(target: String): Int {
        var ans = 0
        var flag = 0
        target.map { it - '0' }.forEach {
            if (it != flag) {
                ans++
                flag = (flag + 1) % 2
            }
        }
        return ans
    }
}