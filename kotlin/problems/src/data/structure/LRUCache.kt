package data.structure

class LRUCache(capacity: Int) {
    private val dataCapacity = capacity
    private val data = HashMap<Int, Int>()
    private val frequent = mutableListOf<Int>()

    fun get(key: Int): Int {
        if (key !in data) {
            return -1
        }
        val value = data[key]!!
        activeKey(key)
        return value
    }

    fun put(key: Int, value: Int) {
        if (dataCapacity == 0) {
            return
        }
        if (key !in data && data.size >= dataCapacity) {
            val m = frequent.first()
            data.remove(m)
            frequent.remove(m)
        }
        data[key] = value
        activeKey(key)
    }

    private fun activeKey(key: Int) {
        frequent.remove(key)
        frequent.add(key)
    }
}