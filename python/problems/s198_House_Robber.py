#coding:utf8
'''
Created on 2017年3月27日

@author: wanlipeng
dp问题，动态求解，先写好递推公式
'''
class Solution(object):
    def rob(self, nums):
        """
        :type nums: List[int]
        :rtype: int
        """
        l = len(nums)
        if l == 0:
            return 0
        if l == 1:
            return nums[0]
        dp = [0] * l
        dp[0] = nums[0]
        dp[1] = max([nums[0], nums[1]])
        for i in range(2, l):
            dp[i] = max([dp[i - 2] + nums[i], dp[i- 1]])
        return dp[l - 1]