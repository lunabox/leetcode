class LeetCodeKt {

    fun licenseKeyFormatting(S: String, K: Int): String {
        val chars = S.filter {
            it != '-'
        }
        val buffer = StringBuilder(chars)
        val step = (chars.length - K).downTo(1).step(K)
        for (i in step) {
            buffer.insert(i, "-")
        }
        return buffer.toString().toUpperCase()
    }

    fun fib(N: Int): Int {
        var a = 0
        var b = 1
        repeat(N - 1) {
            val c = a + b
            a = b
            b = c
        }
        return if (N == 0) 0 else b
    }

    fun distributeCandies(candies: IntArray): Int {
        val set = HashSet<Int>()
        candies.forEach {
            if (it !in set) {
                set.add(it)
            }
        }
        return if (set.size >= candies.size / 2) {
            candies.size / 2
        } else {
            set.size
        }
    }

    fun fourSum(nums: IntArray, target: Int): List<List<Int>> {
        val result = ArrayList<List<Int>>()
        nums.sort()
        for (i in 0 until nums.size - 3) {
            for (j in i + 1 until nums.size - 2) {
                for (k in j + 1 until nums.size - 1) {
                    for (l in k + 1 until nums.size) {
                        if (nums[i] + nums[j] + nums[k] + nums[l] == target) {
                            val array = arrayListOf(nums[i], nums[j], nums[k], nums[l])
                            var add = true
                            result.forEach {
                                if (it[0] == nums[i] && it[1] == nums[j] && it[2] == nums[k] && it[3] == nums[l]) {
                                    add = false
                                    return@forEach
                                }
                            }
                            if (add) {
                                result.add(array)
                            }
                        }
                    }
                }
            }
        }
        return result
    }

    fun threeSum(nums: IntArray): List<List<Int>> {
        val result = ArrayList<List<Int>>()
        nums.sort()
        for (i in 0 until nums.size) {
            if (i == 0 || nums[i] > nums[i - 1]) {
                var l = i + 1
                var r = nums.size - 1
                while (l < r) {
                    val s = nums[i] + nums[l] + nums[r]
                    when {
                        s == 0 -> {
                            result.add(arrayListOf(nums[i], nums[l], nums[r]))
                            l++
                            r--
                            while (l < r && nums[l - 1] == nums[l]) l++
                            while (l < r && nums[r + 1] == nums[r]) r--
                        }
                        s > 0 -> r--
                        else -> l++
                    }
                }
            }
        }
        return result
    }

}