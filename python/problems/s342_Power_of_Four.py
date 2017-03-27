class Solution(object):
    def isPowerOfFour(self, num):
        """
        :type num: int
        :rtype: bool
        """
        for i in range(0, 32, 2):
            if num == (1 << i):
                return True
        return False
        