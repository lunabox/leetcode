package solution

import java.util.*
import kotlin.Comparator
import kotlin.collections.HashMap
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
        val reach = BooleanArray(edges.size) { false }
        val edgesMap = HashMap<Int, MutableList<Int>>()
        edges.forEach {
            val list0 = edgesMap.getOrDefault(it[0], mutableListOf())
            val list1 = edgesMap.getOrDefault(it[1], mutableListOf())
            list0.add(it[1])
            list1.add(it[0])
            edgesMap[it[0]] = list0
            edgesMap[it[1]] = list1
        }
        countSubTreesDfs(0, edgesMap, labels, ans, reach)
        return ans
    }

    private fun countSubTreesDfs(
        currentPoint: Int,
        edges: Map<Int, List<Int>>,
        labels: String,
        ans: IntArray,
        reach: BooleanArray
    ): IntArray {
        val counts = IntArray(26)
        reach[currentPoint] = true
        counts[labels[currentPoint] - 'a']++
        edges[currentPoint]?.forEach {
            if (!reach[it]) {
                val r = countSubTreesDfs(it, edges, labels, ans, reach)
                r.forEachIndexed { index, i ->
                    counts[index] += i
                }
            }
        }
        ans[currentPoint] = counts[labels[currentPoint] - 'a']
        return counts
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

    fun countGoodTriplets(arr: IntArray, a: Int, b: Int, c: Int): Int {
        var ans = 0
        for (i in arr.indices) {
            for (j in i + 1 until arr.size) {
                for (k in j + 1 until arr.size) {
                    if (abs(arr[i] - arr[j]) <= a &&
                        abs(arr[j] - arr[k]) <= b &&
                        abs(arr[i] - arr[k]) <= c
                    ) {
                        ans++
                    }
                }
            }
        }
        return ans
    }

    fun getWinner(arr: IntArray, k: Int): Int {
        if (k > arr.size) {
            return arr.max()!!
        }
        var maxIndex = 0
        var count = 0
        var index = 0
        while (count < k) {
            if (maxIndex != index) {
                if (arr[maxIndex] > arr[index]) {
                    count++
                } else {
                    count = 1
                    maxIndex = index
                }
            }
            index++
            if (index == arr.size) {
                index = 0
            }
        }
        return arr[maxIndex]
    }

    fun findKthPositive(arr: IntArray, k: Int): Int {
        val flag = IntArray(3000) { 0 }
        arr.forEach {
            flag[it - 1] = 1
        }
        var count = 0
        flag.forEachIndexed { index, i ->
            if (i == 0) {
                count++
            }
            if (count == k) {
                return index + 1
            }
        }
        return 1
    }

    fun canConvertString(s: String, t: String, k: Int): Boolean {
        if (s.length != t.length) {
            return false
        }
        val dis = IntArray(s.length) { 0 }
        t.forEachIndexed { index, c ->
            dis[index] = c - s[index]
        }
        val flag = BooleanArray(k + 1) { false }
        dis.forEach { i ->
            if (i == 0) {
                return@forEach
            }
            var n = i
            if (n > k) {
                return false
            }
            while (n < 0 || flag[n]) {
                n += 26
                if (n > k) {
                    return false
                }
            }
            if (n > k) {
                return false
            }
            flag[n] = true
        }
        return true
//        dis.forEachIndexed { index, i ->
//            var n = i
//            while (n < 0) {
//                n += 26
//            }
//            dis[index] = n
//        }
//        dis.sort()
//        dis.forEachIndexed { index, i ->
//            if (i > k) {
//                return false
//            }
//            var n = i
//            var has = false
//            do {
//                for (j in 0 until index) {
//                    if (dis[j] == n) {
//                        n += 26
//                        has = true
//                        break
//                    }
//                }
//            } while (has)
//            dis[index] = n
//        }
//        return true
    }

    fun minInsertions(s: String): Int {
        var left = 0
        var right = 0
        var index = 0
        val q = Stack<Char>()
        while (index < s.length) {
            if (s[index] == '(') {
                q.push(s[index])
            } else if (s[index] == ')') {
                if (index + 1 < s.length) {
                    if (q.isNotEmpty()) {
                        q.pop()
                    } else {
                        left++
                    }
                    if (s[index + 1] == ')') {
                        index++
                    } else {
                        right++
                    }
                } else if (q.isNotEmpty()) {
                    q.pop()
                    right++
                } else {
                    right++
                    left++
                }
            }
            index++
        }
        return left + right + q.size * 2
    }

    fun makeGood(s: String): String {
        val buffer = mutableListOf<Char>()
        s.forEach { buffer.add(it) }
        var done: Boolean
        do {
            done = true
            for (i in 0 until buffer.size - 1) {
                if ((buffer[i].toLowerCase() == buffer[i + 1].toLowerCase()) &&
                    ((buffer[i].isLowerCase() && buffer[i + 1].isUpperCase()) ||
                            (buffer[i + 1].isLowerCase() && buffer[i].isUpperCase()))
                ) {
                    buffer.removeAt(i)
                    buffer.removeAt(i)
                    done = false
                    break
                }
            }
        } while (!done)
        val ans = StringBuffer()
        buffer.forEach { ans.append(it) }
        return ans.toString()
    }

    fun findKthBit(n: Int, k: Int): Char {
        val ans = StringBuffer()
        ans.append('0')
        repeat(n - 1) {
            val currentLength = ans.length
            ans.append('1')
            for (i in currentLength - 1 downTo 0) {
                ans.append(if (ans[i] == '0') '1' else '0')
            }
        }
        return ans[k - 1]
    }

    /**
     *
     */
    fun maxNonOverlapping(nums: IntArray, target: Int): Int {
        val map = HashMap<Int, Int>()
        map[0] = 1
        var ans = 0
        var curSum = 0
        nums.forEach { i ->
            curSum += i
            ans += map.getOrDefault(curSum - target, 0)
            map[curSum] = map.getOrDefault(curSum, 0) + 1
        }
        return ans
    }

    fun threeConsecutiveOdds(arr: IntArray): Boolean {
        for (i in 0..arr.size - 3) {
            if (arr[i] % 2 == 1 && arr[i + 1] % 2 == 1 && arr[i + 2] % 2 == 1) {
                return true
            }
        }
        return false
    }

    fun minOperations(n: Int): Int {
        var ans = 0
        repeat(n / 2) {
            ans += n - 2 * it - 1
        }
        return ans
    }

    fun thousandSeparator(n: Int): String {
        val ans = StringBuffer()
        val m = n.toString()
        var count = 0
        for (i in m.length - 1 downTo 0) {
            if (count == 3) {
                ans.append(".")
                count = 0
            }
            ans.append(m[i])
            count++
        }
        return ans.reverse().toString()
    }

    fun containsPattern(arr: IntArray, m: Int, k: Int): Boolean {
        repeat(m) { start ->
            var current = ""
            var count = 0
            for (i in start until arr.size - m + 1 step m) {
                val key = StringBuffer()
                arr.slice(IntRange(i, i + m - 1)).forEach { key.append(it) }
                if (key.toString() != current) {
                    current = key.toString()
                    count = 1
                } else {
                    count++
                }
                if (count >= k) {
                    return true
                }
            }
        }
        return false
    }

    fun getMaxLen(nums: IntArray): Int {
        var maxLength = 0
        var currentLength = 0
        var positive = true
        nums.forEach { i ->
            if (i == 0) {
                currentLength = 0
//                positive = true
            } else if (i > 0) {
                currentLength++
//                if (!positive) {
//                    currentLength = 1
//                }
            } else {
                if (positive) {
                    positive = false
                } else {
                    currentLength += 2
                    positive = true
                }
            }
            maxLength = max(maxLength, currentLength)
        }
        return maxLength
    }
}