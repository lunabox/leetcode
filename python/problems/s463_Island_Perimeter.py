#coding:utf8

class Solution(object):
    
    def islandPerimeter(self, grid):
        """
        :type grid: List[List[int]]
        :rtype: int
        """
        count = 0
        for i in range(len(grid)):
            for j in range(len(grid[i])):
                if grid[i][j] == 1:
                    count += 4
                    if i > 0 and grid[i - 1][j] == 1:  # Up
                        count -= 1
                    if j > 0 and grid[i][j - 1] == 1:  # Left
                        count -= 1
                    if i < len(grid) - 1 and grid[i + 1][j] == 1:  # Down
                        count -= 1
                    if j < len(grid[i]) - 1 and grid[i][j + 1] == 1:  # Right
                        count -= 1
        return count