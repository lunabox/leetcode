class Solution:
    # @return a list of integers
    def getRow(self, rowIndex):
        tri = [1]
        for i in range(rowIndex):
            for j in range(len(tri) - 2, -1, -1):
                tri[j + 1] = tri[j] + tri[j + 1]
            tri.append(1)
        return tri