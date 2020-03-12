package case

import java.io.File
import java.io.FileReader

object NumberCase {

    /**
     * 加载Int数组列表
     */
    fun loadIntArray(): List<IntArray> {
        val list = mutableListOf<IntArray>()
        val fileReader = FileReader(File("${File("").canonicalPath}${File.separator}src${File.separator}data${File.separator}nums.txt"))
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
}