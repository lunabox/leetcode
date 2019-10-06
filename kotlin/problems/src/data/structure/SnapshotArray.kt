package data.structure

/**
 * https://leetcode-cn.com/problems/snapshot-array/
 * 数组快照
 */
class SnapshotArray(length: Int) {
    private val data = IntArray(length)
    private val snapData = ArrayList<HashMap<Int, Int>>()

    init {
        data.fill(0, 0, data.size)
        snapData.add(HashMap())
    }

    fun set(index: Int, `val`: Int) {
        snapData.last()[index] = `val`
    }

    fun snap(): Int {
        val index = snapData.lastIndex
        snapData.add(HashMap())
        return index
    }

    fun get(index: Int, snap_id: Int): Int {
        (snap_id downTo 0).forEach {
            if (index in snapData[it].keys) {
                return snapData[it][index]!!
            }
        }
        return data[index]
    }
}