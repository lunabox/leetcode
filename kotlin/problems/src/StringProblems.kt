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
}