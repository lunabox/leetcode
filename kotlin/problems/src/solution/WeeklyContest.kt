package solution

import kotlin.math.abs
import kotlin.math.max

class WeeklyContest {

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
}