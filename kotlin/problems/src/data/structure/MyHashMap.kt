package data.structure

class MyHashMap() {
    private val keys = Array<MutableList<Int>>(2048) { mutableListOf() }
    private val values = Array<MutableList<Int>>(keys.size) { mutableListOf() }
    /** Initialize your data structure here. */


    /** value will always be non-negative. */
    fun put(key: Int, value: Int) {
        val groupIndex = key % keys.size
        val index = keys[groupIndex].indexOf(key)
        if (index >= 0) {
            values[groupIndex][index] = value
        } else {
            keys[groupIndex].add(key)
            values[groupIndex].add(value)
        }
    }

    /** Returns the value to which the specified key is mapped, or -1 if this map contains no mapping for the key */
    fun get(key: Int): Int {
        val groupIndex = key % keys.size
        val index = keys[groupIndex].indexOf(key)
        if (index >= 0) {
            return values[groupIndex][index]
        }
        return -1
    }

    /** Removes the mapping of the specified value key if this map contains a mapping for the key */
    fun remove(key: Int) {
        val groupIndex = key % keys.size
        val index = keys[groupIndex].indexOf(key)
        if (index >= 0) {
            keys[groupIndex].removeAt(index)
            values[groupIndex].removeAt(index)
        }
    }

}