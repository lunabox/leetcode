class Solution(object):
    def isPerfectSquare(self, num):
        """
        :type num: int
        :rtype: bool
        """
        if num <= 1:
            return True
        low = 0
        high = num
        mid = int((low + high) / 2)
        while low < high and high - low > 1:
            if mid * mid == num:
                return True
            elif mid * mid < num:
                low = mid
            else:
                high = mid
            mid = int((low + high) / 2)
        return False