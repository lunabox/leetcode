package solution

import kotlin.math.abs
import kotlin.math.max
import kotlin.math.min


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

    /**
     * https://leetcode-cn.com/problems/group-anagrams/
     */
    fun groupAnagrams(strs: Array<String>): List<List<String>> {
        val result = mutableListOf<List<String>>()
        val map = HashMap<String, MutableList<String>>()
        strs.forEach {
            val sorted = String(it.toCharArray().sortedArray())
            if (sorted !in map) {
                map[sorted] = mutableListOf()
            }
            map[sorted]?.add(it)
        }
        result.addAll(map.values)
        return result
    }

    /**
     * https://leetcode-cn.com/problems/find-smallest-letter-greater-than-target/
     */
    fun nextGreatestLetter(letters: CharArray, target: Char): Char {
        letters.forEach {
            if (it.toInt() > target.toInt()) {
                return it
            }
        }
        return letters[0]
    }

    /**
     * https://leetcode-cn.com/problems/reverse-only-letters/
     */
    fun reverseOnlyLetters(S: String): String {
        val chars = S.toCharArray()
        val indexes = mutableListOf<Int>()
        chars.forEachIndexed { index, c ->
            if (c.isLetter()) {
                indexes.add(index)
            }
        }
        repeat(indexes.size / 2) {
            val temp = chars[indexes[it]]
            chars[indexes[it]] = chars[indexes[indexes.size - it - 1]]
            chars[indexes[indexes.size - it - 1]] = temp
        }
        return String(chars)
    }

    /**
     * https://leetcode-cn.com/problems/find-words-that-can-be-formed-by-characters/
     */
    fun countCharacters(words: Array<String>, chars: String): Int {
        val letter = IntArray(26) { 0 }
        var sum = 0
        chars.forEach {
            letter[it - 'a']++
        }
        words.forEach words@{ word ->
            val map = HashMap<Char, Int>()
            word.forEach {
                map[it] = map[it]?.plus(1) ?: 1
            }
            map.keys.forEach {
                if (map[it]!! > letter[it - 'a']) {
                    return@words
                }
            }
            sum += word.length
        }
        return sum
    }

    /**
     * 周赛
     */
    fun canConstruct(s: String, k: Int): Boolean {
        val letters = IntArray(26) { 0 }
        s.forEach {
            letters[it - 'a']++
        }
        var jishu = 0
        var oushu = 0
        letters.filter { it > 0 }.forEach {
            oushu += it / 2
            if (it % 2 == 1) {
                jishu++
            }
        }
        if (jishu > k) {
            return false
        }
        if (jishu + oushu > k || (jishu + oushu <= k && k <= jishu + 2 * oushu)) {
            return true
        }
        return false
    }

    /**
     * https://leetcode-cn.com/problems/longest-happy-string/
     */
    fun longestDiverseString(a: Int, b: Int, c: Int): String {
        val result = StringBuffer(a + b + c)
        val chars = arrayOf('a', 'b', 'c')
        val n = arrayOf(a, b, c)
        var lastIndex = -1

        while (true) {
            val temp = mutableListOf<Pair<Int, Int>>()
            for (index in n.indices) {
                if (index != lastIndex) {
                    temp.add(Pair(n[index], index))
                }
            }
            temp.sortBy { it.first }
            val last = temp.last()
            if (last.first == 0) {
                break
            }
            var take = min(last.first, 2)
            if (lastIndex != -1 && n[lastIndex] > last.first) {
                take = 1
            }
            repeat(take) {
                result.append(chars[last.second])
            }
            n[last.second] -= take
            lastIndex = last.second
        }
        return result.toString()
    }


    /**
     * https://leetcode-cn.com/problems/zigzag-conversion/
     */
    fun convert(s: String, numRows: Int): String {
        if (numRows == 0) {
            return s
        }
        val chars = Array<MutableList<Char>>(numRows) { mutableListOf() }
        var mode = 0
        var index = 0
        s.forEach { c ->
            chars[index].add(c)
            if (mode == 0) {
                index++
                if (index == numRows) {
                    mode = 1
                    index = max(index - 2, 0)
                }
            } else {
                index--
                if (index == -1) {
                    mode = 0
                    index = min(index + 2, numRows - 1)
                }
            }
        }
        val result = StringBuffer(s.length)
        chars.forEach {
            it.forEach { c ->
                result.append(c)
            }
        }
        return result.toString()
    }

    /**
     * https://leetcode-cn.com/problems/longest-substring-without-repeating-characters/
     */
    fun lengthOfLongestSubstring(s: String): Int {
        val letters = HashSet<Char>()
        var ans = 0
        var i = 0
        var j = 0
        while (i < s.length && j < s.length) {
            if (s[j] !in letters) {
                letters.add(s[j++])
                ans = kotlin.math.max(ans, j - i)
            } else {
                letters.remove(s[i++])
            }
        }
        return ans
    }

    /**
     * https://leetcode-cn.com/problems/restore-ip-addresses/
     */
    fun restoreIpAddresses(s: String): List<String> {
        val result = mutableListOf<String>()
        dfsIp(s, "", 0, 1, result)
        return result
    }

    private fun dfsIp(s: String, current: String, index: Int, level: Int, result: MutableList<String>) {
        if (index > s.lastIndex) {
            return
        }
        if (level == 4) {
            val last = s.slice(IntRange(index, s.lastIndex))
            try {
                if ((last.length == 1 || last[0] != '0') && last.toInt() in 0..255) {
                    result.add("$current$last")
                    return
                }
            } catch (e: NumberFormatException) {
                return
            }
        }

        for (i in index..min(index + 2, s.lastIndex)) {
            val item = s.slice(IntRange(index, i))
            try {
                if ((item.length == 1 || item[0] != '0') && item.toInt() in 0..255) {
                    dfsIp(s, "$current$item.", i + 1, level + 1, result)
                }
            } catch (e: NumberFormatException) {

            }
        }
    }


    fun stringMatching(words: Array<String>): List<String> {
        val ans = HashSet<String>()
        for (i in words.indices) {
            for (j in i + 1 until words.size) {
                if (words[i].length >= words[j].length && words[i].indexOf(words[j]) >= 0) {
                    ans.add(words[j])
                } else if (words[i].length < words[j].length && words[j].indexOf(words[i]) >= 0) {
                    ans.add(words[i])
                }
            }
        }
        return ans.toList()
    }

    fun entityParser(text: String): String {
        var ans = text.replace("&quot;", "\"")
        ans = ans.replace("&apos;", "'")
        ans = ans.replace("&amp;", "&")
        ans = ans.replace("&gt;", ">")
        ans = ans.replace("&lt;", "<")
        ans = ans.replace("&frasl;", "/")
        return ans
    }

    /**
     * https://leetcode-cn.com/contest/biweekly-contest-24/problems/the-k-th-lexicographical-string-of-all-happy-strings-of-length-n/
     */
    fun getHappyString(n: Int, k: Int): String {
        val happy = mutableListOf<String>()
        happyDFS("", n, happy)
        if (happy.size < k) {
            return ""
        }
        happy.sort()
        return happy[k - 1]
    }

    private val chars = arrayOf('a', 'b', 'c')
    private fun happyDFS(current: String, n: Int, happy: MutableList<String>) {
        if (current.length == n) {
            happy.add(current)
            return
        }
        if (current.isEmpty()) {
            happyDFS("a", n, happy)
            happyDFS("b", n, happy)
            happyDFS("c", n, happy)
        } else {
            val usefulChar = mutableListOf<Char>()
            chars.forEach {
                if (it != current.last()) {
                    usefulChar.add(it)
                }
            }
            usefulChar.forEach {
                happyDFS("$current$it", n, happy)
            }
        }
    }

    /**
     * https://leetcode-cn.com/contest/weekly-contest-185/problems/reformat-the-string/
     */
    fun reformat(s: String): String {
        val letters = mutableListOf<Char>()
        val nums = mutableListOf<Char>()
        s.forEach {
            if (it.isLetter()) {
                letters.add(it)
            } else {
                nums.add(it)
            }
        }
        if (abs(letters.size - nums.size) > 1) {
            return ""
        }
        val ans = StringBuffer()
        var letterIndex = 0
        var numsIndex = 0
        if (letters.size > nums.size) {
            ans.append(letters[letterIndex++])
        } else if (letters.size < nums.size) {
            ans.append(nums[numsIndex++])
        }
        while (letterIndex < letters.size && numsIndex < nums.size) {
            if (letterIndex >= numsIndex) {
                ans.append(nums[numsIndex++])
                ans.append(letters[letterIndex++])
            } else {
                ans.append(letters[letterIndex++])
                ans.append(nums[numsIndex++])
            }
        }
        return ans.toString()
    }
}