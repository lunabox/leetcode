class Solution:
    # @param {string} s
    # @param {string} t
    # @return {boolean}
    def isAnagram(self, s, t):
        ls = list(s)
        ls.sort()
        lt = list(t)
        lt.sort()
        if len(ls) != len(lt):
            return False
        for i in range(len(ls)):
            if ls[i] != lt[i]:
                return False
        return True