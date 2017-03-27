#coding:utf8
'''
Created on 2017年3月27日

@author: wanlipeng
'''

class Solution(object):
    def readBinaryWatch(self, num):
        """
        :type num: int
        :rtype: List[str]
        """
        def countbit(n):
            count = 0
            while n > 0:
                count += n & 0x1
                n >>= 1
            return count
        result = list()
        for hour in range(12):
            hour_bit = countbit(hour)
            for minu in range(60):
                if hour_bit + countbit(minu) == num:
                    if minu < 10:
                        result.append(str(hour) + ':0' + str(minu))
                    else:
                        result.append(str(hour) + ':' + str(minu))
        return result