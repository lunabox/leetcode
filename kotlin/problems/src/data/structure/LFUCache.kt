package data.structure

class LFUCache(capacity: Int) {
    private val data = HashMap<Int, Int>()
    private val frequent = HashMap<Int, Int>()
    private val listOrder = HashMap<Int, LinkedHashSet<Int>>()
    private var m = -1

    fun get(key: Int): Int {
        if (key !in data) {
            return -1
        }
        val value = data[key]!!
        val freq = frequent[key] ?: 0
        frequent[key] = freq + 1
        listOrder[freq]?.remove(key)
        if (freq == m && listOrder[freq]?.isEmpty() != false) {
            m++
//            frequent[freq + 1] = frequent[freq + 1]
        }
        return value
    }

    fun put(key: Int, value: Int) {
        data[key - 1] = value
    }

}