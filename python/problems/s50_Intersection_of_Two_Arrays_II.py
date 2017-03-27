class Solution(object):
    def intersect(self, nums1, nums2):
        """
        :type nums1: List[int]
        :type nums2: List[int]
        :rtype: List[int]
        """
        result = list()
        count_num = {}
        for n in nums1:
            if count_num.has_key(n):
                count_num[n] += 1
            else:
                count_num[n] = 1
        for n in nums2:
            if count_num.has_key(n) and count_num[n] > 0:
                count_num[n] -= 1
                result.append(n)
        return result