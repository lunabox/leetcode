package data.structure

class UndergroundSystem() {
    private val time = mutableListOf<Int>()
    private val ids = mutableListOf<Int>()
    private val names = mutableListOf<String>()
    private val inStation = mutableListOf<Boolean>()

    fun checkIn(id: Int, stationName: String, t: Int) {
        time.add(t)
        ids.add(id)
        names.add(stationName)
        inStation.add(true)
    }

    fun checkOut(id: Int, stationName: String, t: Int) {
        time.add(t)
        ids.add(id)
        names.add(stationName)
        inStation.add(false)
    }

    fun getAverageTime(startStation: String, endStation: String): Double {
        var sumTime = 0
        var count = 0
        val outIds = mutableListOf<Int>()
        val outTime = mutableListOf<Int>()
        for (i in names.lastIndex downTo 0) {
            if (names[i] == endStation && !inStation[i]) {
                outIds.add(ids[i])
                outTime.add(time[i])
            }
        }

        for (i in names.indices) {
            if (names[i] == startStation && inStation[i]) {
                val index =  outIds.indexOf(ids[i])
                if (index >= 0) {
                    count++
                    sumTime += (outTime[index] - time[i])
                }
            }
        }
        return sumTime.toDouble() / count.toDouble()
    }

}