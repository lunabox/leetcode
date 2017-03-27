class Solution(object):
    def climbStairs(self, n):
        """
        :type n: int
        :rtype: int
        """
        if n == 1:
            return 1
        result = []
        result.append(1)
        result.append(2)
        for i in range(2, n):
            result.append(result[i - 1] + result[i - 2])
        return result.pop()
        