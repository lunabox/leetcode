class Solution:
    # @param {integer} n
    # @return {boolean}
    def isHappy(self, n):
        while True:
            sn = str(n)
            n = sum(int(sn[i]) ** 2 for i in range(len(sn)))
            if n == 1:
                return True
            elif n < 10:
                return False