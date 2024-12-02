package me.sivieri.aoc2024.day2

import me.sivieri.aoc2024.common.Utils

object Main1 {

    @JvmStatic
    fun main(args: Array<String>) {
        val data = Utils
            .readInputToList(2)
            .map { Report.fromString(it) }
        val reactor = RedNosedReactor(data)
        val result = reactor.countSafeReports()
        println(result)
    }

}