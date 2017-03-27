class Solution:
    # @param {string} s
    # @param {string} t
    # @return {boolean}
    def isIsomorphic(self, s, t):
        if len(s) != len(t):
            return False
        m = {}
        for i in range(len(s)):
            if not m.has_key(s[i]):
                if t[i] in m.values():
                    return False
                else:
                    m[s[i]] = t[i]
            elif m[s[i]] != t[i]:
                return False
        return True