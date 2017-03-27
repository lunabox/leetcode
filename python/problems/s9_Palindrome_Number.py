class Solution:
    # @return a boolean
    def isPalindrome(self, x):
        if x < 0:
            return False
        strX = str(x)
        rX = strX[::-1]
        result = int(rX)
        return result == x