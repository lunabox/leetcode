package data.structure

class MyHashSet() {
    private val data = IntArray(1000000 / 32 + 1) { 0 }

    /** Initialize your data structure here. */

    fun add(key: Int) {
        data[key / 32] = data[key / 32] or (0x1 shl (key and 0x1F))
    }

    fun remove(key: Int) {
        data[key / 32] = data[key / 32] and (0x1 shl (key and 0x1F)).inv()
    }

    /** Returns true if this set contains the specified element */
    fun contains(key: Int): Boolean {
        // 需要判断不等于0，如果刚好一个Int的首位标记位，结果是负数
        return data[key / 32] and (0x1 shl (key and 0x1F)) != 0
    }

}