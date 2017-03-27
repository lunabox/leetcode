import string


class Solution(object):
    def firstUniqChar(self, s):
        """
        :type s: str
        :rtype: int
        """
        letters = dict.fromkeys(string.ascii_lowercase, 0)
        for ch in s:
            letters[ch] += 1
        for i in range(len(s)):
            if letters[s[i]] == 1:
                return i
        return -1