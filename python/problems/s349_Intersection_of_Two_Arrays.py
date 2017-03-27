class Solution(object):
    def intersection(self, nums1, nums2):
        """
        :type nums1: List[int]
        :type nums2: List[int]
        :rtype: List[int]
        """
        result = list()
        uni_num = {}
        for n in nums1:
            uni_num[n] = 1
        for n in nums2:
            if uni_num.has_key(n):
                if result.count(n) == 0:
                    result.append(n)
        return result