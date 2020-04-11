package solution

class BitProblems {

    /**
     * https://leetcode-cn.com/problems/single-number-iii/
     */
    fun singleNumber(nums: IntArray): IntArray {
        var mask = 0
        nums.forEach {
            mask = mask.xor(it)
        }
        val last = mask.and(-mask)
        var x = 0
        nums.forEach {
            if (it.and(last) != 0) {
                x = x.xor(it)
            }
        }
        return intArrayOf(x, mask.xor(x))
    }

    /**
     * https://leetcode-cn.com/problems/sort-integers-by-the-number-of-1-bits/
     */
    fun sortByBits(arr: IntArray): IntArray {
        return arr.sortedWith(Comparator<Int> { o1, o2 ->
            val b1 = Integer.bitCount(o1)
            val b2 = Integer.bitCount(o2)
            if (b1 == b2) {
                o1 - o2
            } else {
                b1 - b2
            }
        }).toIntArray()
    }

    /**
     * https://leetcode-cn.com/problems/insert-into-bits-lcci/
     */
    fun insertBits(N: Int, M: Int, i: Int, j: Int): Int {
        var mask = 1
        repeat(j - i) {
            mask = mask.shl(1).or(0x1)
        }
        mask = mask.shl(i).inv()
        return N.and(mask).or(M.shl(i))
    }
}