class Solution(object):
    def findContentChildren(self, g, s):
        """
        :type g: List[int]
        :type s: List[int]
        :rtype: int
        """
        g = sorted(g)
        s = sorted(s)
        size = len(s)
        count = 0
        pointer = 0
        for gg in g:
            while pointer < size:
                if s[pointer] >= gg:
                    count += 1
                    pointer += 1
                    break
                pointer += 1
            if pointer == size:
                break
        return count