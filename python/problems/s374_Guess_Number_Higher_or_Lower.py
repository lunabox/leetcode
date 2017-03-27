# coding:utf8

def guess(num):
    if num > 6:
        return -1
    if num < 6:
        return 1
    return 0

class Solution(object):
    def guessNumber(self, n):
        """
        :type n: int
        :rtype: int
        """
        low = 1
        high = n
        mid = (low + high) / 2
        while low < high:
            re = guess(mid)
            if re == 0:
                return mid
            elif re < 0:
                high = mid - 1
            else:
                low = mid + 1
            mid = (low + high) / 2
        return low
        