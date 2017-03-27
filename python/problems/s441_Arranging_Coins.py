import math


class Solution(object):
    def arrangeCoins(self, n):
        """
        :type n: int
        :rtype: int
        """
        level = int(math.sqrt(n * 2))
        while level * (level + 1) / 2 <= n:
            level += 1
        return level - 1