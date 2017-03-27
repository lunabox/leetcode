class Solution(object):
    def findNthDigit(self, n):
        """
        :type n: int
        :rtype: int
        """
        digits = 1
        ith = 1
        base = 9
        while n > digits * base:
            n -= digits * base
            digits += 1
            ith += base
            base *= 10
        nn = str(ith + (n - 1) / digits)
        return int(nn[(n - 1) % digits])