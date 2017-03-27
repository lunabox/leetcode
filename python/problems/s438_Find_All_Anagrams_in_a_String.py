import string


class Solution(object):
    def findAnagrams(self, s, p):
        """
        :type s: str
        :type p: str
        :rtype: List[int]
        """
        def isEqu(pd, sd):
            for k in pd.keys():
                if pd[k] != sd[k]:
                    return False
            return True
        result = []
        pd = dict.fromkeys(string.lowercase, 0)
        sd = dict.fromkeys(string.lowercase, 0)
        for pp in p:
            pd[pp] += 1
        width = len(p)
        for i in range(len(s)):
            sd[s[i]] += 1
            if i >= width:
                sd[s[i - width]] -= 1
            if isEqu(pd, sd):
                result.append(i - width + 1)
        return result