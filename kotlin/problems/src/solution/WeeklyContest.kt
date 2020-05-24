package solution

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
}