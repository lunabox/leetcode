package case

import java.io.File
import java.io.FileReader
import kotlin.random.Random

object NumberCase {

    /**
     * 加载Int数组列表
     */
    fun loadIntArray(): List<IntArray> {
        val list = mutableListOf<IntArray>()
        val fileReader =
            FileReader(File("${File("").canonicalPath}${File.separator}src${File.separator}data${File.separator}nums.txt"))
        fileReader.readLines().forEach {
            val str = it.split(",")
            val array = IntArray(str.size)
            str.forEachIndexed { index, s ->
                array[index] = s.toInt()
            }
            list.add(array)
        }
        return list
    }

    /**
     * 随机生成数字
     */
    fun randomIntArray(rangeMin: Int = 0, rangeMax: Int, lengthMax: Int, caseSum: Int): List<IntArray> {
        val result = ArrayList<IntArray>(caseSum)
        val random = Random(System.currentTimeMillis())
        repeat(caseSum) {
            val curArraySize = random.nextInt(1, lengthMax)
            val array = IntArray(curArraySize)
            repeat(curArraySize) {
                array[it] = random.nextInt(rangeMin, rangeMax)
            }
            array.sort()
            result.add(array)
        }
        return result
    }

    inline fun <T> Iterable<T>.forEachCase(action: (Int, T) -> Unit) {
        val printCase: (Int, Any?) -> Unit = { index, element ->
            print("<$index> $element -> ")
        }
        for ((index, element) in this.withIndex()) {
            when (element) {
                is Iterable<*> -> printCase(index, element.toList())
                is IntArray -> printCase(index, element.toList())
                is LongArray -> printCase(index, element.toList())
                else -> printCase(index, element.toString())
            }
            action(index, element)
        }
    }

}