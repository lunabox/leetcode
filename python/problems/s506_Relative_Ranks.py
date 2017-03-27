class Solution(object):
    def findRelativeRanks(self, nums):
        """
        :type nums: List[int]
        :rtype: List[str]
        """
        rank = sorted(nums, reverse=True);
        result = list()
        for n in nums:
            num = rank.index(n)
            if num == 0:
                result.append('Gold Medal')
            elif num == 1:
                result.append('Silver Medal')
            elif num == 2:
                result.append('Bronze Medal')
            else:
                result.append(str(num + 1))
        return result