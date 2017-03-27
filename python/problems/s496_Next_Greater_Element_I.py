class Solution(object):
    def nextGreaterElement(self, findNums, nums):
        """
        :type findNums: List[int]
        :type nums: List[int]
        :rtype: List[int]
        """
        result = []
        table = {}
        stack = []
        for i in range(len(nums) - 1, -1, -1):
            while len(stack) > 0 and stack[len(stack) - 1] < nums[i]:
                stack.pop()
            if len(stack) == 0:
                table[nums[i]] = -1
            else:
                table[nums[i]] = stack[len(stack) - 1]
            stack.append(nums[i])
        for i in findNums:
            result.append(table[i])
        return result