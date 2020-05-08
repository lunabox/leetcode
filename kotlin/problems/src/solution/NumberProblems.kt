package solution

import java.math.BigInteger
import kotlin.math.abs
import kotlin.math.max
import kotlin.math.min
import kotlin.math.sqrt

class NumberProblems {

    /**
     * https://leetcode-cn.com/problems/k-diff-pairs-in-an-array/
     * 给定一个整数数组和一个整数 k, 你需要在数组里找到不同的 k-diff 数对。这里将 k-diff 数对定义为一个整数对 (i, j), 其中 i 和 j 都是数组中的数字，且两数之差的绝对值是 k
     */
    fun findPairs(nums: IntArray, k: Int): Int {
        if (k < 0) {
            return 0
        }
        val view = HashSet<Int>()
        val diff = HashSet<Int>()
        nums.forEach {
            if (view.contains(it + k)) {
                diff.add(it + k)
            }
            if (view.contains(it - k)) {
                diff.add(it)
            }
            view.add(it)
        }
        return diff.size
    }

    /**
     * https://leetcode-cn.com/problems/diet-plan-performance/
     */
    fun dietPlanPerformance(calories: IntArray, k: Int, lower: Int, upper: Int): Int {
        var s = 0
        var score = 0
        repeat(k) {
            s += calories[it]
        }

        repeat(calories.size - k + 1) {
            when {
                s < lower -> score--
                s > upper -> score++
            }
            if (it + k < calories.size) {
                s = s - calories[it] + calories[it + k]
            }
        }
        return score
    }

    fun maxCount(m: Int, n: Int, ops: Array<IntArray>): Int {
        var mm = m
        var nn = n
        ops.forEach {
            mm = min(mm, it[0])
            nn = min(nn, it[1])
        }
        return mm * nn
    }

    /**
     * https://leetcode-cn.com/problems/can-place-flowers/
     */
    fun canPlaceFlowers(flowerbed: IntArray, n: Int): Boolean {
        var count = 0
        repeat(flowerbed.size) {
            if (it == 0) {
                if ((flowerbed.size > 1 && flowerbed[0] == 0 && flowerbed[1] == 0) || (flowerbed.size == 1 && flowerbed[0] == 0)) {
                    count++
                    flowerbed[it] = 1
                }
            } else if (it == flowerbed.size - 1) {
                if (flowerbed[it] == 0 && flowerbed[it - 1] == 0) {
                    count++
                    flowerbed[it] = 1
                }
            } else if (flowerbed[it] == 0 && flowerbed[it - 1] == 0 && flowerbed[it + 1] == 0) {
                count++
                flowerbed[it] = 1
            }
            if (count >= n) {
                return true
            }
        }
        return count >= n
    }

    /**
     * https://leetcode-cn.com/problems/asteroid-collision/
     * 对于数组中的每一个元素，其绝对值表示行星的大小，正负表示行星的移动方向（正表示向右移动，负表示向左移动）。每一颗行星以相同的速度移动。
     * 找出碰撞后剩下的所有行星。碰撞规则：两个行星相互碰撞，较小的行星会爆炸。如果两颗行星大小相同，则两颗行星都会爆炸。两颗移动方向相同的行星，永远不会发生碰撞。
     */
    fun asteroidCollision(asteroids: IntArray): IntArray {
        val list = asteroids.toMutableList()
        var i = 0
        while (i < list.size - 1) {
            if (list[i] > 0 && list[i + 1] < 0) {
                when {
                    list[i] + list[i + 1] > 0 -> {
                        list.removeAt(i + 1)
                    }
                    list[i] + list[i + 1] == 0 -> {
                        list.removeAt(i)
                        list.removeAt(i)
                        i = if (i == 0) 0 else i - 1
                    }
                    else -> {
                        list.removeAt(i)
                        i = if (i == 0) 0 else i - 1
                    }
                }
            } else {
                i++
            }
        }
        return list.toIntArray()
    }

    /**
     * https://leetcode-cn.com/problems/find-minimum-in-rotated-sorted-array/
     */
    fun findMin(nums: IntArray): Int {
        return nums.min()!!
    }

    /**
     * https://leetcode-cn.com/problems/search-in-rotated-sorted-array/
     */
    fun search(nums: IntArray, target: Int): Boolean {
        return nums.indexOf(target) >= 0
    }

    /**
     * https://leetcode-cn.com/problems/compare-version-numbers/
     */
    fun compareVersion(version1: String, version2: String): Int {
        val v1 = version1.split(".")
        val v2 = version2.split(".")

        repeat(max(v1.size, v2.size)) {
            val subVersion1 = if (it < v1.size) v1[it].toInt() else 0
            val subVersion2 = if (it < v2.size) v2[it].toInt() else 0

            when {
                subVersion1 > subVersion2 -> return 1
                subVersion1 < subVersion2 -> return -1
                else -> return@repeat
            }
        }
        return 0
    }

    /**
     * https://leetcode-cn.com/problems/pairs-of-songs-with-total-durations-divisible-by-60/
     */
    fun numPairsDivisibleBy60(time: IntArray): Int {
        val count = IntArray(60)
        var sum = 0
        time.map {
            val value = it % 60
            count[value]++
            value
        }.forEach {
            if (it != 0 && it != 30) {
                sum += count[60 - it]
            }
        }
        // 上面除了0和30之外，都计算了两次，成对出现，需要去掉一半
        sum /= 2
        // 0
        sum += if (count[0] > 1) count[0] * (count[0] - 1) / 2 else 0
        // 30
        sum += if (count[30] > 1) count[30] * (count[30] - 1) / 2 else 0
        return sum
    }

    /**
     *  https://leetcode-cn.com/problems/product-of-array-except-self/
     *  给定长度为 n 的整数数组 nums，其中 n > 1，返回输出数组 output ，其中 output[i] 等于 nums 中除 nums[i] 之外其余各元素的乘积
     */
    fun productExceptSelf(nums: IntArray): IntArray {
        val left = IntArray(nums.size) { 1 }
        val right = IntArray(nums.size) { 1 }
        val result = IntArray(nums.size)

        for (i in 1 until nums.size) {
            left[i] = left[i - 1] * nums[i - 1]
        }
        for (i in nums.size - 2 downTo 0) {
            right[i] = right[i + 1] * nums[i + 1]
        }
        for (i in nums.indices) {
            result[i] = left[i] * right[i]
        }
        return result
    }

    /**
     * https://leetcode-cn.com/problems/array-partition-i/
     * 给定长度为 2n 的数组, 你的任务是将这些数分成 n 对, 例如 (a1, b1), (a2, b2), ..., (an, bn) ，使得从1 到 n 的 min(ai, bi) 总和最大
     */
    fun arrayPairSum(nums: IntArray): Int {
        var sum = 0
        nums.sort()
        for (i in nums.indices step 2) {
            sum += nums[i]
        }
        return sum
    }

    /**
     * https://leetcode-cn.com/problems/maximum-product-of-three-numbers/
     */
    fun maximumProduct(nums: IntArray): Int {
        nums.sort()
        if (nums[0] < 0 && nums[1] < 0) {
            val a = nums[0] * nums[1] * nums.last()
            val b = nums[nums.size - 3] * nums[nums.size - 2] * nums[nums.size - 1]
            return if (a > b) a else b
        }
        return nums[nums.size - 3] * nums[nums.size - 2] * nums[nums.size - 1]
    }

    /**
     * https://leetcode-cn.com/problems/merge-intervals/
     * 给出一个区间的集合，请合并所有重叠的区间
     */
    fun merge(intervals: Array<IntArray>): Array<IntArray> {
        val result = ArrayList<IntArray>()
        intervals.sortBy { it[0] }
        intervals.forEach {
            if (result.isNotEmpty() && result.last()[1] >= it[0]) {
                if (result.last()[1] <= it[1]) {
                    result.last()[1] = it[1]
                }
            } else {
                result.add(it)
            }
        }
        val array = Array(result.size) { intArrayOf() }
        return result.toArray(array)
    }

    /**
     * https://leetcode-cn.com/problems/shortest-unsorted-continuous-subarray/
     */
    fun findUnsortedSubarray(nums: IntArray): Int {
        var left = 0
        var right = nums.size - 1
        var minValue = -1
        var maxValue = -1
        for (i in nums.indices) {
            if (i == 0) {
                maxValue = nums[i]
            } else {
                if (maxValue > nums[i]) {
                    left = i
                }
                maxValue = max(maxValue, nums[i])
            }
        }
        for (i in nums.size - 1 downTo 0) {
            if (i == nums.lastIndex) {
                minValue = nums.last()
            } else {
                if (minValue < nums[i]) {
                    right = i
                }
                minValue = min(minValue, nums[i])
            }
        }
        return if (left - right + 1 > 0) left - right + 1 else 0
    }

    /**
     * https://leetcode-cn.com/problems/subtract-the-product-and-sum-of-digits-of-an-integer/
     */
    fun subtractProductAndSum(n: Int): Int {
        var num = n
        var multi = 1
        var sum = 0
        while (num != 0) {
            val last = num % 10
            multi *= last
            sum += last
            num /= 10
        }
        return multi - sum
    }

    /**
     * https://leetcode-cn.com/problems/sum-of-square-numbers/
     */
    fun judgeSquareSum(c: Int): Boolean {
        var i = 0
        var j = sqrt(c.toDouble()).toInt()
        while (i <= j) {
            val r = i * i + j * j
            when {
                r == c -> return true
                r < c -> i++
                r > c -> j--
            }
        }
        return false
    }

    /**
     * https://leetcode-cn.com/problems/non-decreasing-array/
     */
    fun checkPossibility(nums: IntArray): Boolean {
        var change = 0
        if (nums.size > 1 && nums[0] > nums[1]) {
            nums[0] = nums[1]
            change++
        }
        for (i in 1 until nums.lastIndex) {
            if (nums[i] <= nums[i + 1]) {
                continue
            }
            change++
            if (change >= 2) {
                return false
            }
            if (nums[i - 1] > nums[i + 1]) {
                nums[i + 1] = nums[i]
            } else {
                nums[i] = nums[i - 1]
            }
        }
        return true
    }

    /**
     * 你正在和你的朋友玩 猜数字（Bulls and Cows）游戏：你写下一个数字让你的朋友猜。每次他猜测后，你给他一个提示，告诉他有多少位数字和确切位置都猜对了（称为“Bulls”, 公牛），有多少位数字猜对了但是位置不对（称为“Cows”, 奶牛）。你的朋友将会根据提示继续猜，直到猜出秘密数字。
     * 请写出一个根据秘密数字和朋友的猜测数返回提示的函数，用 A 表示公牛，用 B 表示奶牛。
     * 请注意秘密数字和朋友的猜测数都可能含有重复数字。
     */
    fun getHint(secret: String, guess: String): String {
        if (secret.isBlank() || guess.isBlank()) {
            return "0A0B"
        }
        var a = 0
        var b = 0
        val nums = IntArray(10)
        val flags = IntArray(secret.length)
        secret.forEach {
            nums[it.toInt() - '0'.toInt()]++
        }
        secret.forEachIndexed { index, c ->
            if (c == guess[index]) {
                a++
                nums[c.toInt() - '0'.toInt()]--
                flags[index]++
            }
        }
        guess.forEachIndexed { index, c ->
            if (flags[index] == 0 && nums[c.toInt() - '0'.toInt()] > 0) {
                b++
                nums[c.toInt() - '0'.toInt()]--
            }
        }
        return "${a}A${b}B"
    }

    /**
     * https://leetcode-cn.com/problems/maximum-average-subarray-i/
     */
    fun findMaxAverage(nums: IntArray, k: Int): Double {
        var s = nums.filterIndexed { index, _ ->
            index < k
        }.sum()
        var m = s

        for (i in k until nums.size) {
            s += nums[i]
            s -= nums[i - k]
            m = if (s > m) s else m
        }
        return m.toDouble() / k.toDouble()
    }

    fun findLengthOfLCIS(nums: IntArray): Int {
        var cur = Int.MIN_VALUE
        var curLength = 0
        var maxLength = 0
        nums.forEach {
            if (it > cur) {
                curLength++
            } else {
                curLength = 1
            }
            cur = it
            if (curLength > maxLength) {
                maxLength = curLength
            }
        }
        return maxLength
    }

    /**
     * https://leetcode-cn.com/problems/binary-number-with-alternating-bits/
     */
    fun hasAlternatingBits(n: Int): Boolean {
        val m = n.xor(n.shr(1))
        return m.and(m + 1) == 0
    }

    /**
     * https://leetcode-cn.com/problems/duplicate-zeros/
     */
    fun duplicateZeros(arr: IntArray): Unit {
        var i = 0
        while (i < arr.size) {
            if (arr[i] != 0) {
                i++
                continue
            }
            for (j in arr.size - 1 downTo i + 1) {
                arr[j] = arr[j - 1]
            }
            if (i + 1 < arr.size) {
                arr[i + 1] = 0
            }
            i += 2
        }
    }

    /**
     * https://leetcode-cn.com/problems/next-permutation/
     */
    fun nextPermutation(nums: IntArray): Unit {
        var n: Int = -1
        for (i in nums.lastIndex downTo 1) {
            if (nums[i - 1] < nums[i]) {
                n = i - 1
                break
            }
        }
        if (n == -1) {
            nums.sort()
        } else {
            var m: Int = nums.lastIndex
            for (j in n + 1 until nums.size) {
                if (nums[j] <= nums[n]) {
                    // find just larger than nums[n]
                    m = j - 1
                    break
                }
            }
            // swap m and n
            val temp = nums[n]
            nums[n] = nums[m]
            nums[m] = temp

            // reverse
            nums.slice(IntRange(n + 1, nums.lastIndex)).reversed().forEachIndexed { index, i ->
                nums[n + index + 1] = i
            }
        }
    }

    /**
     * https://leetcode-cn.com/problems/permutations/
     */
    fun permute(nums: IntArray): List<List<Int>> {
        val result = ArrayList<List<Int>>()
        permuteItem(result, nums, 0)
        return result
    }

    private fun permuteItem(result: MutableList<List<Int>>, nums: IntArray, index: Int) {
        if (index == nums.lastIndex) {
            result.add(nums.toList())
        } else {
            for (i in index until nums.size) {
                swap(nums, index, i)
                permuteItem(result, nums, index + 1)
                swap(nums, index, i)
            }
        }
    }

    private fun swap(nums: IntArray, i: Int, j: Int) {
        if (i != j) {
            val temp = nums[i]
            nums[i] = nums[j]
            nums[j] = temp
        }
    }

    /**
     * https://leetcode-cn.com/problems/degree-of-an-array/
     */
    fun findShortestSubArray(nums: IntArray): Int {
        val left = HashMap<Int, Int>()
        val right = HashMap<Int, Int>()
        val countItem = HashMap<Int, Int>()
        nums.forEachIndexed { index, i ->
            if (i !in left.keys) {
                left[i] = index
            }
            right[i] = index
            countItem[i] = countItem.getOrDefault(i, 0) + 1
        }

        var ins = nums.size
        val degree = countItem.values.max()
        countItem.filterValues { it == degree }.forEach {
            if (right[it.key]!! - left[it.key]!! + 1 < ins) {
                ins = right[it.key]!! - left[it.key]!! + 1
            }
        }
        return ins
    }

    /**
     * https://leetcode-cn.com/problems/find-pivot-index/
     */
    fun pivotIndex(nums: IntArray): Int {
        val arraySum = nums.sum()
        var leftSum = 0
        nums.forEachIndexed { index, i ->
            if (leftSum == arraySum - i - leftSum) {
                return index
            }
            leftSum += i
        }
        return -1
    }

    /**
     * https://leetcode-cn.com/problems/subsets/
     */
    fun subsets(nums: IntArray): List<List<Int>> {
        val result = mutableListOf<List<Int>>()
        dfsSubSets(nums, IntArray(nums.size), 0, result)
        return result
    }

    private fun dfsSubSets(nums: IntArray, flag: IntArray, index: Int, result: MutableList<List<Int>>) {
        if (index > nums.lastIndex) {
            result.add(nums.filterIndexed { m, _ -> flag[m] == 1 }.toList())
            return
        }
        flag[index] = 0
        dfsSubSets(nums, flag, index + 1, result)
        flag[index] = 1
        dfsSubSets(nums, flag, index + 1, result)
    }

    /**
     * https://leetcode-cn.com/problems/self-dividing-numbers/
     */
    fun selfDividingNumbers(left: Int, right: Int): List<Int> {
        return (left..right).filter { checkSelfNumber(it) }
    }

    private fun checkSelfNumber(n: Int): Boolean {
        n.toString().map { it.toInt() - '0'.toInt() }.forEach {
            if (it == 0 || n % it != 0) {
                return false
            }
        }
        return true
    }

    /**
     * https://leetcode-cn.com/problems/set-mismatch/
     */
    fun findErrorNums(nums: IntArray): IntArray {
        val result = IntArray(2)
        if (nums.isEmpty()) {
            return result
        }
        for (i in nums.indices) {
            if (nums[abs(nums[i]) - 1] < 0) {
                result[0] = abs(nums[i])
            } else {
                nums[abs(nums[i]) - 1] *= -1
            }
        }
        nums.forEachIndexed missing@{ index, i ->
            if (i > 0) {
                result[1] = index + 1
                return@missing
            }
        }
        return result
    }

    /**
     * https://leetcode-cn.com/problems/rotated-digits/
     */
    fun rotatedDigits(N: Int): Int {
        var count = 0
        repeat(N) N@{
            val n = (it + 1).toString().toCharArray()
            n.forEachIndexed check@{ index, c ->
                when (c.toInt() - '0'.toInt()) {
                    0, 1, 8 -> return@check
                    2 -> n[index] = '5'
                    5 -> n[index] = '2'
                    6 -> n[index] = '9'
                    9 -> n[index] = '6'
                    else -> return@N
                }
            }
            if (String(n).toInt() != it + 1) {
                count++
            }
        }
        return count
    }

    /**
     * https://leetcode-cn.com/problems/find-lucky-integer-in-an-array/
     */
    fun findLucky(arr: IntArray): Int {
        val n = arr.sorted().reversed()
        var current = n[0]
        var count = 1
        for (i in 1..n.lastIndex) {
            if (n[i] == current) {
                count++
            } else {
                if (count == current) {
                    return current
                }
                current = n[i]
                count = 1
            }
        }
        return if (count == current) current else -1
    }

    /**
     * https://leetcode-cn.com/problems/cells-with-odd-values-in-a-matrix/
     */
    fun oddCells(n: Int, m: Int, indices: Array<IntArray>): Int {
        val data = Array(n) { IntArray(m) { 0 } }
        indices.forEach { loc ->
            repeat(m) {
                data[loc[0]][it]++
            }
            repeat(n) {
                data[it][loc[1]]++
            }
        }
        var count = 0
        for (i in 0 until n) {
            for (j in 0 until m) {
                if (data[i][j] % 2 == 1) {
                    count++
                }
            }
        }
        return count
    }

    /**
     * https://leetcode-cn.com/problems/string-to-integer-atoi/
     */
    fun myAtoi(str: String): Int {
        var s = str.trim()
        if (s.isEmpty()) {
            return 0
        }
        if (s[0] != '+' && s[0] != '-' && !s[0].isDigit()) {
            return 0
        }
        val nav = if (s[0] == '+' || s[0].isDigit()) 1L else -1L
        s = if (s[0] == '-' || s[0] == '+') s.substring(1) else s
        if (s.isEmpty() || !s[0].isDigit()) {
            return 0
        }
        var index = s.lastIndex
        for (i in s.indices) {
            if (!s[i].isDigit()) {
                break
            }
            index = i
        }
        return try {
            val r = s.substring(0, index + 1).toBigInteger().multiply(BigInteger.valueOf(nav))
            when {
                r > BigInteger.valueOf(Int.MAX_VALUE.toLong()) -> {
                    Int.MAX_VALUE
                }
                r < BigInteger.valueOf(Int.MIN_VALUE.toLong()) -> {
                    Int.MIN_VALUE
                }
                else -> {
                    r.toInt()
                }
            }
        } catch (e: Exception) {
            0
        }
    }

    /**
     * 双周赛
     */
    fun countLargestGroup(n: Int): Int {
        val count = IntArray(36) { 0 }
        for (i in 1..n) {
            var sum = 0
            i.toString().forEach {
                sum += it - '0'
            }
            if (sum > 0) {
                count[sum - 1]++
            }
        }
        val m = count.max()
        var result = 0
        count.forEach {
            if (it == m) {
                result++
            }
        }
        return result
    }

    /**
     * https://leetcode-cn.com/problems/number-of-steps-to-reduce-a-number-in-binary-representation-to-one/
     */
    fun numSteps(s: String): Int {
        var binNum = BigInteger(s, 2)
        var count = 0
        val one = BigInteger.valueOf(1L)
        while (binNum > one) {
            if (binNum.and(one) == one) {
                binNum++
            } else {
                binNum = binNum.shr(1)
            }
            count++
        }
        return count
    }

    /**
     * https://leetcode-cn.com/problems/minimum-subsequence-in-non-increasing-order/
     */
    fun minSubsequence(nums: IntArray): List<Int> {
        val numSum = nums.sum()
        var subSum = 0
        val result = mutableListOf<Int>()
        nums.sorted().reversed().forEach {
            subSum += it
            result.add(it)
            if (subSum > numSum - subSum) {
                return result.sorted().reversed()
            }
        }
        return result
    }

    fun multiply(num1: String, num2: String): String {
        val result = StringBuffer()
        val n1 = num1.reversed()
        val n2 = num2.reversed()

        val addNum: (StringBuffer, StringBuffer) -> Unit = { result, s ->
            var addCarry = 0
            s.map { it - '0' }.forEachIndexed { index, c ->
                if (index < result.length) {
                    val m = (result[index] - '0' + c + addCarry)
                    addCarry = m / 10
                    result.setCharAt(index, ((m % 10) + '0'.toInt()).toChar())
                } else {
                    val m = c + addCarry
                    addCarry = m / 10
                    result.append(m % 10)
                }
            }
            if (addCarry > 0) {
                result.append(addCarry)
            }
        }
        n1.map { it - '0' }.forEachIndexed { i, n ->
            val lineBuffer = StringBuffer()
            var carry = 0
            repeat(i) { lineBuffer.append(0) }
            n2.map { it - '0' }.forEach { m ->
                val s = n * m + carry
                carry = s / 10
                lineBuffer.append(s % 10)
            }
            if (carry > 0) {
                lineBuffer.append(carry)
            }
            addNum(result, lineBuffer)
        }
        if (result.count { it == '0' } == result.length) {
            return "0"
        }
        return result.reverse().toString()
    }

    fun rotate(matrix: Array<IntArray>): Unit {
        var temp = 0
        val n = matrix.size
        for (i in 0 until n / 2) {
            for (j in i until n - i - 1) {
                temp = matrix[i][j]
                matrix[i][j] = matrix[n - j - 1][i]
                matrix[n - j - 1][i] = matrix[n - 1 - i][n - j - 1]
                matrix[n - 1 - i][n - j - 1] = matrix[j][n - 1 - i]
                matrix[j][n - 1 - i] = temp
            }
        }
    }

    /**
     * https://leetcode-cn.com/problems/search-a-2d-matrix/
     */
    fun searchMatrix(matrix: Array<IntArray>, target: Int): Boolean {
        matrix.forEach {
            if (it.isNotEmpty() && target <= it.last()) {
                val index = it.indexOf(target)
                if (index >= 0) {
                    return true
                }
            }
        }
        return false
    }

    /**
     * https://leetcode-cn.com/problems/single-number-ii/
     */
    fun singleNumber(nums: IntArray): Int {
        val set = HashSet<Long>()
        var sum = 0L
        nums.forEach {
            set.add(it.toLong())
            sum += it
        }
        return ((3 * set.sum() - sum) / 2).toInt()
    }

    /**
     * https://leetcode-cn.com/problems/shu-zu-zhong-chu-xian-ci-shu-chao-guo-yi-ban-de-shu-zi-lcof/
     */
    fun majorityElement(nums: IntArray): Int {
        var vote = 0
        var target = 0
        nums.forEach {
            if (vote == 0) {
                target = it
            }
            vote += if (it == target) 1 else -1
        }
        return if (nums.count { it == target } > nums.size / 2) target else 0
    }

    fun processQueries(queries: IntArray, m: Int): IntArray {
        val ans = IntArray(queries.size)
        val p = ArrayList<Int>(m)
        repeat(m) {
            p.add(it + 1)
        }
        queries.forEachIndexed { index, i ->
            val pos = p.indexOf(i)
            if (pos >= 0) {
                ans[index] = pos
                p.removeAt(pos)
                p.add(0, i)
            }
        }
        return ans
    }

    /**
     * https://leetcode-cn.com/problems/number-of-ways-to-paint-n-x-3-grid/
     */
    fun numOfWays(n: Int): Int {
        var a = 6L
        var b = 6L
        repeat(n - 1) {
            val tempA = 3 * a + 2 * b
            b = (2 * a + 2 * b) % 1000000007L
            a = tempA % 1000000007L
        }
        return ((a + b) % 1000000007L).toInt()
    }

    /**
     * https://leetcode-cn.com/problems/missing-number-lcci/
     */
    fun missingNumber(nums: IntArray): Int {
        return nums.size * (nums.size + 1) / 2 - nums.sum()
    }

    /**
     * https://leetcode-cn.com/problems/er-wei-shu-zu-zhong-de-cha-zhao-lcof/
     */
    fun findNumberIn2DArray(matrix: Array<IntArray>, target: Int): Boolean {
        if (matrix.isEmpty() || matrix[0].isEmpty()) {
            return false
        }
        var row = 0
        var col = matrix[0].lastIndex
        while (row < matrix.size && col >= 0) {
            when {
                target == matrix[row][col] -> return true
                target > matrix[row][col] -> row++
                else -> col--
            }
        }
        return false
    }

    /**
     * https://leetcode-cn.com/problems/jump-game/
     */
    fun canJump(nums: IntArray): Boolean {
        var step = 0
        nums.forEachIndexed { index, i ->
            step = if (index == 0) {
                i
            } else {
                max(step - 1, i)
            }
            if (step == 0 && index < nums.lastIndex) {
                return false
            }
        }
        return step >= 0
    }

    /**
     * https://leetcode-cn.com/contest/biweekly-contest-24/problems/minimum-value-to-get-positive-step-by-step-sum/
     */
    fun minStartValue(nums: IntArray): Int {
        var m = Int.MAX_VALUE
        var sum = 0
        nums.forEach {
            sum += it
            if (sum < m) {
                m = sum
            }
        }
        return if (m < 0) 1 - m else 1
    }

    /**
     * https://leetcode-cn.com/contest/biweekly-contest-24/problems/find-the-minimum-number-of-fibonacci-numbers-whose-sum-is-k/
     */
    fun findMinFibonacciNumbers(k: Int): Int {
        val fibonacci = mutableListOf<Int>()
        var a = 1
        var b = 1
        fibonacci.add(a)
        fibonacci.add(b)
        while (fibonacci.last() < k) {
            val n = a + b
            fibonacci.add(n)
            a = b
            b = n
        }
        var count = 0
        var sum = k
        for (i in fibonacci.lastIndex downTo 0) {
            if (fibonacci[i] <= sum) {
                sum -= fibonacci[i]
                count++
                if (sum == 0) {
                    return count
                }
            }
        }
        return count
    }

    /**
     * https://leetcode-cn.com/problems/1-bit-and-2-bit-characters/
     */
    fun isOneBitCharacter(bits: IntArray): Boolean {
        var index = 0
        while (index < bits.size) {
            if (index == bits.lastIndex && bits[index] == 0) {
                return true
            }
            when (bits[index]) {
                0 -> index++
                1 -> index += 2
            }
        }
        return false
    }

    /**
     * https://leetcode-cn.com/problems/largest-number-at-least-twice-of-others/
     */
    fun dominantIndex(nums: IntArray): Int {
        var maxNum = 0
        var maxIndex = 0
        nums.forEachIndexed { index, i ->
            if (i > maxNum) {
                maxNum = i
                maxIndex = index
            }
        }
        nums.forEachIndexed { index, i ->
            if (index != maxIndex && i * 2 > maxNum) {
                return -1
            }
        }
        return maxIndex
    }

    /**
     * https://leetcode-cn.com/problems/lucky-numbers-in-a-matrix/
     */
    fun luckyNumbers(matrix: Array<IntArray>): List<Int> {
        val ans = mutableListOf<Int>()
        val minNumberIndex = IntArray(matrix.size)
        matrix.forEachIndexed { index, ints ->
            var m = Int.MAX_VALUE
            ints.forEachIndexed { j, n ->
                if (n < m) {
                    m = n
                    minNumberIndex[index] = j
                }
            }
        }
        minNumberIndex.forEachIndexed { index, i ->
            for (j in matrix.indices) {
                if (matrix[j][i] > matrix[index][i]) {
                    return@forEachIndexed
                }
            }
            ans.add(matrix[index][i])
        }
        return ans
    }

    /**
     * https://leetcode-cn.com/problems/sort-array-by-parity-ii/
     */
    fun sortArrayByParityII(A: IntArray): IntArray {
        var even = 0
        var odd = 1
        while (odd < A.size || even < A.size) {
            while (even < A.size && A[even] % 2 == 0) {
                even += 2
            }
            while (odd < A.size && A[odd] % 2 == 1) {
                odd += 2
            }
            if (odd < A.size && even < A.size) {
                val temp = A[odd]
                A[odd] = A[even]
                A[even] = temp
            }
        }
        return A
    }

    /**
     * https://leetcode-cn.com/problems/relative-sort-array/
     */
    fun relativeSortArray(arr1: IntArray, arr2: IntArray): IntArray {
        val ans = mutableListOf<Int>()
        val nums = IntArray(1001) { 0 }
        arr1.forEach { nums[it]++ }
        arr2.forEach {
            while (nums[it] != 0) {
                nums[it]--
                ans.add(it)
            }
        }
        for (i in 1..1000) {
            while (nums[i] != 0) {
                nums[i]--
                ans.add(i)
            }
        }
        return ans.toIntArray()
    }

    /**
     * https://leetcode-cn.com/problems/squares-of-a-sorted-array/
     */
    fun sortedSquares(A: IntArray): IntArray {
        val ans = IntArray(A.size)
        val positive = A.filter { it >= 0 }.map { it * it }
        var right = 0
        val negative = A.filter { it < 0 }.map { it * it }
        var left = negative.size - 1
        for (i in ans.indices) {
            ans[i] = when {
                right == positive.size -> negative[left--]
                left == -1 -> positive[right++]
                positive[right] >= negative[left] -> negative[left--]
                else -> positive[right++]
            }
        }
        return ans
    }

    /**
     * https://leetcode-cn.com/problems/sum-of-even-numbers-after-queries/
     * 暴力会超时
     */
    fun sumEvenAfterQueries(A: IntArray, queries: Array<IntArray>): IntArray {
        val ans = IntArray(queries.size)
        var base = A.filter { it % 2 == 0 }.sum()
        queries.forEachIndexed { index, ints ->
            val before = A[ints[1]]
            A[ints[1]] += ints[0]
            val after = A[ints[1]]
            if (before % 2 == 0) {
                // 偶数
                if (after % 2 == 0) {
                    ans[index] = base - before + after
                } else {
                    ans[index] = base - before
                }
            } else if (after % 2 == 0) {
                ans[index] = base + after
            } else {
                ans[index] = base
            }
            base = ans[index]
        }
        return ans
    }

    /**
     * https://leetcode-cn.com/problems/find-first-and-last-position-of-element-in-sorted-array/
     */
    fun searchRange(nums: IntArray, target: Int): IntArray {
        var left = 0
        var right = nums.lastIndex
        var mid: Int
        var index = -1
        while (left <= right) {
            mid = (left + right) / 2
            if (nums[mid] > target) {
                right = mid - 1
            } else if (nums[mid] < target) {
                left = mid + 1
            } else {
                index = mid
                break
            }
        }
        if (index > -1) {
            left = index
            while (left >= 0 && nums[left] == target) {
                left--
            }
            if (left == -1) {
                left = 0
            } else {
                left++
            }
            right = index
            while (right < nums.size && nums[right] == target) {
                right++
            }
            if (right == nums.size) {
                right = nums.lastIndex
            } else {
                right--
            }
            return intArrayOf(left, right)
        }
        return intArrayOf(-1, -1)
    }
}