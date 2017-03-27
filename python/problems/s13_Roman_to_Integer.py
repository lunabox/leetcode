class Solution:
    # @return an integer
    def romanToInt(self, s):
        num = {'I':1, 'V':5, 'X':10, 'L':50, 'C':100, 'D':500, 'M':1000}
        length = len(s)
        result = num[s[0]]
        for i in range(length - 1):
            cur = num[s[i]]
            nextNum = num[s[i + 1]]
            if cur >= nextNum:
                result += nextNum
            else:
                result = result + nextNum - 2 * cur
        return result