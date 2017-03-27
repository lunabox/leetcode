class Solution:
    # @param {integer[]} nums
    # @param {integer} k
    # @return {integer[]}
    def maxSlidingWindow(self, nums, k):
        result = []
        for i in range(len(nums) - k + 1):
            if i >= i + k:
                continue
            result.append(max(nums[i:i + k]))
        return result