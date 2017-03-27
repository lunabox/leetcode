class Solution:
    # @return a list of lists of integers
    def generate(self, numRows):
        if numRows < 1:
            return []
        result = [[1]] 
        for i in range(numRows - 1):
            tri = [1]
            result.append(tri)
            preTri = result[len(result) - 2]
            for j in range(len(preTri) - 1):
                tri.append(preTri[j] + preTri[j + 1])
            tri.append(1)    
        return result