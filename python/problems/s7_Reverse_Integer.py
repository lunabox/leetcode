class Solution:
    # @return an integer
    def reverse(self, x):
        flag = 1
        if x < 0:
            flag = -1
        strX = str(abs(x))
        rX = strX[::-1]
        result = flag * int(rX)
        if result > 2147483647 or result < -2147483648:
            return 0
        return result
