import string


class Solution(object):
    def longestPalindrome(self, s):
        """
        :type s: str
        :rtype: int
        """
        hasMid = 0
        result = 0
        letters = dict.fromkeys(string.lowercase + string.uppercase, 0)
        for ch in s:
            letters[ch] += 1
        for _, v in letters.items():
            if v % 2 == 1:
                hasMid = 1
            if v > 1:
                result += (v / 2 * 2)
        result += hasMid
        return result