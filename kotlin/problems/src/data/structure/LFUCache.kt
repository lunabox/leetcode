package data.structure

class LFUCache(capacity: Int) {
    private val dataCapacity = capacity
    private val data = HashMap<Int, Int>()
    private val frequent = HashMap<Int, Int>()
    private val listOrder = HashMap<Int, MutableList<Int>>()

    fun get(key: Int): Int {
        if (key !in data) {
            return -1
        }
        val value = data[key]!!
        activeItem(key)
        return value
    }

    fun put(key: Int, value: Int) {
        if (dataCapacity == 0) {
            return
        }
        if (key !in data && data.size >= dataCapacity) {
            val m = frequent.values.min()
            val k = listOrder[m]?.first()
            frequent.remove(k)
            listOrder[m]?.remove(k!!)
            data.remove(k)
        }
        data[key] = value
        activeItem(key)
    }

    private fun activeItem(key: Int) {
        val freq = frequent[key] ?: 0
        frequent[key] = freq + 1
        listOrder[freq]?.remove(key)
        listOrder[freq + 1] = listOrder[freq + 1] ?: mutableListOf()
        listOrder[freq + 1]!!.remove(key)
        listOrder[freq + 1]!!.add(key)
    }

}