class Solution(object):
    def hammingWeight(self, n):
        """
        :type n: int
        :rtype: int
        """
        n &= 0xFFFFFFFF
        count = 0
        while n > 0:
            count += (n & 0x1)
            n >>= 1
        return count
        