class Solution(object):
    def majorityElement(self, nums):
        """
        :type nums: List[int]
        :rtype: int
        """
        count = 1
        cur = nums[0]
        for n in nums[1:]:
            if n == cur:
                count += 1
            else:
                count -= 1
                if count == 0:
                    cur = n
                    count = 1
        return cur
        