class Solution(object):
    def countAndSay(self, n):
        """
        :type n: int
        :rtype: str
        """
        last = '1'
        result = last
        for _ in range(1, n):
            result = ''
            count = 0
            cur = last[0]
            for j in range(len(last)):
                if last[j] == cur:
                    count += 1
                else:
                    result += (str(count) + cur)
                    cur = last[j]
                    count = 1
            else:
                result += (str(count) + cur)
            last = result
        return result