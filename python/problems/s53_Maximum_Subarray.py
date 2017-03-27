class Solution(object):
    def maxSubArray(self, nums):
        """
        :type nums: List[int]
        :rtype: int
        """
        resultMax = 0
        curMax = 0
        for n in nums:
            curMax += n
            if curMax > 0:
                if resultMax < curMax:
                    resultMax = curMax
            else:
                curMax = 0
        if resultMax == 0:
            resultMax = -0xFFFFFFFF
            for n in nums:
                if n > resultMax:
                    resultMax = n
        return resultMax