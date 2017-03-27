class Solution(object):
    def numberOfBoomerangs(self, points):
        """
        :type points: List[List[int]]
        :rtype: int
        """
        def dis(n1, n2):
            i = n1[0] - n2[0]
            j = n1[1] - n2[1]
            return i * i + j * j
        result = 0
        for i in range(len(points)):
            disdict = {}
            for j in range(len(points)):
                d = dis(points[i], points[j])
                if disdict.has_key(d):
                    disdict[d] += 1
                else:
                    disdict[d] = 1
            for _, v in disdict.items():
                if v >= 2:
                    result += v * (v - 1)
        return result