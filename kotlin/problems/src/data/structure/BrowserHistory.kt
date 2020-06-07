package data.structure

import kotlin.math.min

class BrowserHistory(homepage: String) {
    private val history = Array<String?>(5000) { null }
    private var current = 0
    private var count = 1

    init {
        history[current] = homepage
        count = 1
    }

    fun visit(url: String) {
        history[++current] = url
        count = current + 1
    }

    fun back(steps: Int): String {
        val n = min(steps, current)
        current -= n
        return history[current]!!
    }

    fun forward(steps: Int): String {
        val n = min(count - current - 1, steps)
        current += n
        return history[current]!!
    }

}
