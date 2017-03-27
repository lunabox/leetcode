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
        top = prices[0]
        bottom = prices[0]
        for i in range(1, len(prices)):
            if prices[i] < top:
                profit += top - bottom
                print profit
                top = prices[i]
                bottom = prices[i]
            elif prices[i] >= top:
                top = prices[i]
                if i == len(prices) - 1:
                    profit += top - bottom
        return profit