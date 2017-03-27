class Solution(object):
    def lengthOfLastWord(self, s):
        """
        :type s: str
        :rtype: int
        """
        s = s.rstrip()
        for i in range(len(s) - 1, -1, -1):
            if s[i] == ' ':
                return len(s) - i - 1
        return len(s)
        