package solution

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
     * 
     */
    fun solve(board: Array<CharArray>): Unit {

    }
}