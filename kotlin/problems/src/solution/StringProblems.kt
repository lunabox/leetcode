package solution

class StringProblems {

    /**
     * https://leetcode-cn.com/problems/is-subsequence/
     * 给定字符串 s 和 t ，判断 s 是否为 t 的子序列
     */
    fun isSubsequence(s: String, t: String): Boolean {
        if (s.isBlank()) {
            return true
        }
        var index = 0
        t.forEach {
            if (it == s[index]) {
                index++
            }
            if (index == s.length) {
                return true
            }
        }
        return false
    }

    /**
     * https://leetcode-cn.com/problems/number-of-matching-subsequences/
     * 给定字符串 S 和单词字典 words, 求 words[i] 中是 S 的子序列的单词个数
     */
    fun numMatchingSubseq(S: String, words: Array<String>): Int {
        var result = 0
        val charIndex = HashMap<Char, List<Int>>()
        repeat(S.length) {
            val char = S[it]
            val list = if (charIndex[char] == null) ArrayList() else charIndex[char] as ArrayList<Int>
            list.add(it)
            charIndex[char] = list
        }

        words.forEach {
            kotlin.run word_search@{
                var currentIndex = -1
                it.forEach char_search@{
                    val list = charIndex[it] ?: return@word_search
                    // exist char
                    list.forEach { index ->
                        if (index > currentIndex) {
                            currentIndex = index
                            return@char_search
                        }
                    }
                    if (list.last() <= currentIndex) {
                        return@word_search
                    }
                }
                result++
            }
        }
        return result
    }

    /**
     * https://leetcode-cn.com/problems/validate-ip-address/
     */
    fun validIPAddress(IP: String): String {
        if (IP.contains(".")) {
            val ips = IP.split(".")
            run ip4@{
                if (ips.size == 4) {
                    repeat(4) {
                        try {
                            val p = ips[it]
                            if (p.isBlank() || (p.toInt() > 0 && p.startsWith("0")) ||
                                (p.toInt() == 0 && p.length > 1) || (p.toInt() !in 0..255)
                            ) {
                                return@ip4
                            }
                        } catch (e: Exception) {
                            return@ip4
                        }
                    }
                    return "IPv4"
                }
            }
        } else if (IP.contains(":")) {
            val ips = IP.split(":")
            run ipv6@{
                if (ips.size == 8) {
                    repeat(8) {
                        try {
                            if (ips[it].isBlank() || ips[it].length > 4 || ips[it][0] == '-' || ips[it].toInt(16) !in 0..0xffff) {
                                return@ipv6
                            }
                        } catch (e: Exception) {
                            return@ipv6
                        }
                    }
                    return "IPv6"
                }
            }
        }
        return "Neither"
    }

    /**
     * https://leetcode-cn.com/problems/reverse-words-in-a-string/
     */
    fun reverseWords(s: String): String {
        val words = s.split(" ").filter { it.isNotBlank() }
        val result = StringBuffer()
        words.asReversed().forEach { result.append(it).append(" ") }

        return if (result.isBlank()) "" else result.deleteCharAt(result.lastIndex).toString()
    }

    /**
     * https://leetcode-cn.com/problems/positions-of-large-groups/
     */
    fun largeGroupPositions(S: String): List<List<Int>> {
        val result = ArrayList<List<Int>>()
        var begin = S[0]
        var beginIndex = 0
        for (i in 1 until S.length) {
            if (S[i] != begin) {
                if (i - beginIndex >= 3) {
                    result.add(intArrayOf(beginIndex, i - 1).toList())
                }
                begin = S[i]
                beginIndex = i
            }
        }
        if (S.length - beginIndex >= 3) {
            result.add(intArrayOf(beginIndex, S.length - 1).toList())
        }
        return result
    }

    /**
     * https://leetcode-cn.com/problems/valid-palindrome-ii/comments/
     */
    fun validPalindrome(s: String): Boolean {
        var i = 0
        var j = s.length - 1
        while (i < j) {
            if (s[i] != s[j]) {
                return isSubStringValid(s, i + 1, j) || isSubStringValid(s, i, j - 1)
            }
            i++
            j--
        }
        return true
    }

    private fun isSubStringValid(s: String, i: Int, j: Int): Boolean {
        var start = i
        var end = j
        while (start < end) {
            if (s[start] != s[end]) {
                return false
            }
            start++
            end--
        }
        return true
    }

    fun toLowerCase(str: String): String {
        val ch = CharArray(str.length)
        str.forEachIndexed { index, c ->
            if (c in 'A'..'Z') {
                ch[index] = (c.toInt() - 'A'.toInt() + 'a'.toInt()).toChar()
            } else {
                ch[index] = c
            }
        }
        return String(ch)
    }

    /**
     * https://leetcode-cn.com/problems/is-unique-lcci/
     */
    fun isUnique(astr: String): Boolean {
        var cur: Char? = null
        astr.toCharArray().sorted().forEach {
            if (it == cur) {
                return false
            }
            cur = it
        }
        return true
    }

    /**
     * https://leetcode-cn.com/problems/count-binary-substrings/
     */
    fun countBinarySubstrings(s: String): Int {
        var countItem = 1
        var cur = s[0]
        val step = mutableListOf<Int>()
        s.drop(1).forEach {
            if (it == cur) {
                countItem++
            } else {
                if (countItem > 0) {
                    step.add(countItem)
                }
                countItem = 1
                cur = it
            }
        }
        if (countItem > 0) {
            step.add(countItem)
        }
        var result = 0
        for (i in 0 until step.lastIndex) {
            result += if (step[i] <= step[i + 1]) step[i] else step[i + 1]
        }
        return result
    }

    /**
     * https://leetcode-cn.com/problems/letter-case-permutation/
     */
    fun letterCasePermutation(S: String): List<String> {
        val letterCount = S.count { it.isLetter() }
        val result = ArrayList<String>((1 shl letterCount) + 1)
        letterDfs(S.toCharArray(), 0, result)
        return result
    }

    private fun letterDfs(s: CharArray, index: Int, result: MutableList<String>) {
        if (index > s.lastIndex) {
            result.add(String(s))
            return
        }
        val curLetter = s[index]
        if (curLetter.isLetter()) {
            letterDfs(s, index + 1, result)
            s[index] = if (curLetter.isLowerCase()) curLetter.toUpperCase() else curLetter.toLowerCase()
        }
        letterDfs(s, index + 1, result)
    }

    /**
     * https://leetcode-cn.com/problems/generate-parentheses/
     */
    fun generateParenthesis(n: Int): List<String> {
        val ans = mutableListOf<String>()
        backTrace(ans, "", 0, 0, n)
        return ans
    }

    /**
     * 回溯法
     * @param ans 结果
     * @param cur 当前结构
     * @param left 左括号
     * @param right 右括号
     * @param n 最大括号对数
     */
    private fun backTrace(ans: MutableList<String>, cur: String, left: Int, right: Int, n: Int) {
        if (cur.length == 2 * n) {
            ans.add(cur)
            return
        }
        if (left < n) {
            backTrace(ans, "$cur(", left + 1, right, n)
        }
        if (right < left) {
            backTrace(ans, "$cur)", left, right + 1, n)
        }
    }

    /**
     * https://leetcode-cn.com/problems/letter-combinations-of-a-phone-number/
     */
    fun letterCombinations(digits: String): List<String> {
        val ans = mutableListOf<String>()
        val map = mapOf(
            '2' to "abc",
            '3' to "def",
            '4' to "ghi",
            '5' to "jkl",
            '6' to "mno",
            '7' to "pqrs",
            '8' to "tuv",
            '9' to "wxyz"
        )
        dfsLetter(ans, map, "", digits, 0)
        return ans
    }

    private fun dfsLetter(ans: MutableList<String>, map: Map<Char, String>, cur: String, digits: String, index: Int) {
        if (index == digits.length) {
            if (cur.isNotBlank()) {
                ans.add(cur)
            }
            return
        }
        map[digits[index]]?.forEach {
            dfsLetter(ans, map, "$cur$it", digits, index + 1)
        }
    }

    /**
     * https://leetcode-cn.com/problems/longest-palindrome/
     */
    fun longestPalindrome(s: String): Int {
        val charCount = IntArray(128)
        var ans = 0
        s.forEach { charCount[it.toInt()]++ }
        charCount.forEach {
            ans += it / 2 * 2
            if (it % 2 == 1 && ans % 2 == 0) {
                ans++
            }
        }
        return ans
    }

    /**
     * https://leetcode-cn.com/problems/rotate-string/
     */
    fun rotateString(A: String, B: String): Boolean {
        if (A == B) {
            return true
        }
        for (index in 0..A.lastIndex) {
            val rotate = A.slice(IntRange(index + 1, A.lastIndex)) + A.slice(IntRange(0, index))
            if (B == rotate) {
                return true
            }
        }
        return false
    }
}