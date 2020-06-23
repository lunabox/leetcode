package solution

class WeeklyContest {

    /**
     * https://leetcode-cn.com/contest/weekly-contest-187/problems/destination-city/
     */
    fun destCity(paths: List<List<String>>): String {
        val vote = HashMap<String, Int>()
        paths.forEach {
            vote[it[0]] = vote[it[0]]?.minus(1) ?: -1
            vote[it[1]] = vote[it[1]]?.plus(1) ?: 1
        }
        for (key in vote.keys) {
            if (vote[key] == 1) {
                return key
            }
        }
        return ""
    }

    /**
     * https://leetcode-cn.com/contest/weekly-contest-187/problems/check-if-all-1s-are-at-least-length-k-places-away/
     */
    fun kLengthApart(nums: IntArray, k: Int): Boolean {
        val list = mutableListOf<Int>()
        nums.forEachIndexed { index, i ->
            if (i == 1) {
                list.add(index)
            }
        }
        for (i in 0 until list.size - 1) {
            if (list[i + 1] - list[i] - 1 < k) {
                return false
            }
        }
        return true
    }
}