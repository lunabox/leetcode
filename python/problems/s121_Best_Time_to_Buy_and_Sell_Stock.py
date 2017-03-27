#coding:utf8
'''
Created on 2017年3月27日

@author: wanlipeng
'''

class Solution(object):
    def maxProfit(self, prices):
        """
        :type prices: List[int]
        :rtype: int
        """
        if len(prices) == 0:
            return 0
        profit = 0
        m = prices[0]
        for i in range(1, len(prices)):
            if prices[i] < m:
                m = prices[i]
            elif prices[i] - m > profit:
                profit = prices[i] - m
        return profit