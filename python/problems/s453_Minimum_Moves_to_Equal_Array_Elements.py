class Solution(object):
    def minMoves(self, nums):
        """
        :type nums: List[int]
        :rtype: int
        """
        m = 0xFFFFFFFF
        count = 0
        for n in nums:
            m = min([m, n])
            count += n
        return count - len(nums) * m