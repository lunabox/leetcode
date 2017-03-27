class Solution(object):
    def toHex(self, num):
        """
        :type num: int
        :rtype: str
        """
        if num == 0:
            return '0'
        result = ''
        num &= 0xFFFFFFFF
        while num > 0:
            n = num & 0xF
            if n >= 10 and n <= 15:
                result = chr(n + 87) + result
            else:
                result = str(n) + result
            num >>= 4
        return result