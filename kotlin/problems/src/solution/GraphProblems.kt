package solution

import kotlin.math.abs
import kotlin.math.max
import kotlin.math.sqrt

class GraphProblems {

    /**
     * 双周赛
     */
    fun checkOverlap(radius: Int, x_center: Int, y_center: Int, x1: Int, y1: Int, x2: Int, y2: Int): Boolean {
        val x0 = (x1 + x2) / 2.0
        val y0 = (y1 + y2) / 2.0

        val a1 = max(abs(x_center - x0) - (x2 - x0), 0.0)
        val a2 = max(abs(y_center - y0) - (y2 - y0), 0.0)
        return sqrt(a1 * a1 + a2 * a2) <= radius
    }

}