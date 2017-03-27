class Solution(object):
    def titleToNumber(self, s):
        """
        :type s: str
        :rtype: int
        """
        result = 0
        sq = 0
        for i in range(len(s) - 1, -1, -1):
            result += (ord(s[i]) - ord('A') + 1) * (26 ** sq)
            sq += 1
        return result
        