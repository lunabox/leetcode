class Solution(object):
    def convertToBase7(self, num):
        """
        :type num: int
        :rtype: str
        """
        result = ''
        positive = 1
        if num  < 0:
            positive = -1
            num = -num
        while (num >= 7):
            result = str(num % 7) + result
            num /= 7
        if num >= 0:
            result = str(num) + result
        if positive < 0:
            result = '-' + result
        return result