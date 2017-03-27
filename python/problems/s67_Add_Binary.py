import string


class Solution(object):
    def addBinary(self, a, b):
        """
        :type a: str
        :type b: str
        :rtype: str
        """
        aa = string.atoi(a, 2)
        bb = string.atoi(b, 2)
        return str(bin(aa + bb))[2:]
        