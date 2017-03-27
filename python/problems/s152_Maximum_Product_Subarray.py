class Solution(object):
    def maxProduct(self, nums):
        """
        :type nums: List[int]
        :rtype: int
        """
        if len(nums) == 1:
            return nums[0]
        resultMax = nums[0]
        curMax = nums[0]
        curMin = nums[0]
        for i in range(1, len(nums)):
            n = nums[i]
            temp = curMax
            curMax = max([max([temp * n, n]), curMin * n])
            curMin = min([min([temp * n, n]), curMin * n])
            resultMax = max([resultMax, curMax])
        return resultMax
        