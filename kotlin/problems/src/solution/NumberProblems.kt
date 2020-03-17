package solution

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
        intervals.sortBy {
            it[0]
        }
        intervals.forEach {
            if (result.isEmpty()) {
                result.add(it)
            } else {
                if (result.last()[1] >= it[0]) {
                    if (result.last()[1] <= it[1]) {
                        result.last()[1] = it[1]
                    }
                } else {
                    result.add(it)
                }
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
}