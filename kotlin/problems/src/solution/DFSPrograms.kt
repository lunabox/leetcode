package solution

import com.sun.org.apache.xpath.internal.operations.Bool

class DFSPrograms {
    /**
     * https://leetcode-cn.com/problems/number-of-islands/
     */
    fun numIslands(grid: Array<CharArray>): Int {
        var ans = 0
        grid.forEachIndexed { i, chars ->
            chars.forEachIndexed { j, c ->
                if (c == '1') {
                    ans++
                    dfsIslands(grid, i, j)
                }
            }
        }
        return ans
    }

    private fun dfsIslands(grid: Array<CharArray>, x: Int, y: Int) {
        if (x < 0 || y < 0 || x >= grid.size || y >= grid[0].size || grid[x][y] == '0') {
            return
        }
        grid[x][y] = '0'
        dfsIslands(grid, x + 1, y)
        dfsIslands(grid, x - 1, y)
        dfsIslands(grid, x, y + 1)
        dfsIslands(grid, x, y - 1)
    }

    /**
     * https://leetcode-cn.com/problems/partition-equal-subset-sum/
     */
    fun canPartition(nums: IntArray): Boolean {
        val sum = nums.sum()
        if (sum % 2 == 1) {
            return false
        }
        val half = sum / 2
        nums.sortDescending()
        if (nums[0] > half) {
            return false
        }
        return partitionDfs(nums, 0, half)
    }

    private fun partitionDfs(nums: IntArray, index: Int, sum: Int): Boolean {
        if (sum == 0) {
            return true
        }
        if (sum < 0) {
            return false
        }
        for (i in index until nums.size) {
            if (partitionDfs(nums, i + 1, sum - nums[i])) {
                return true
            }
        }
        return false
    }
}