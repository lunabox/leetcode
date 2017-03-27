class Solution(object):
    def countPrimes(self, n):
        """
        :type n: int
        :rtype: int
        """
        count = 0
        nums = [0 for i in range(2, n)]
        for i in range(len(nums)):
            if nums[i] == 1:
                continue
            fator = 2
            t = (i + 2) * fator
            count += 1
            while t < n:
                nums[t - 2] = 1
                fator += 1
                t = (i + 2) * fator
        return count