class SimulatedProgram {

    /**
     * https://leetcode-cn.com/problems/robot-return-to-origin/
     */
    fun judgeCircle(moves: String): Boolean {
        var x = 0
        var y = 0
        moves.forEach {
            when (it) {
                'R' -> x++
                'L' -> x--
                'D' -> y--
                'U' -> y++
            }
        }
        return x == 0 && y == 0
    }
}