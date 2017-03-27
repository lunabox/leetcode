class Solution(object):
    def reverseStr(self, s, k):
        """
        :type s: str
        :type k: int
        :rtype: str
        """
        reverse = -1
        result = ''
        for i in range(0, len(s), k):
            if i + k < len(s):
                result += s[i : i + k][::reverse]
            else:
                result += s[i : len(s)][::reverse]
            reverse = -reverse
        return result