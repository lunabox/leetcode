class Solution(object):
    def countSegments(self, s):
        """
        :type s: str
        :rtype: int
        """
        count = 0
        seqLen = 0
        for c in s:
            if c != ' ':
                seqLen += 1
                continue
            elif seqLen > 0:
                count += 1
                seqLen = 0
        if seqLen > 0:
            return count + 1
        return count