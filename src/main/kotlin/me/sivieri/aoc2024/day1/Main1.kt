package me.sivieri.aoc2024.day1

import me.sivieri.aoc2024.common.Utils

object Main1 {

    @JvmStatic
    fun main(args: Array<String>) {
        val data = Utils
            .readInputToList(1)
            .map {
                val d = it.split("   ")
                Pair(d[0].toLong(), d[1].toLong())
            }
        val result = LocationList.calculateTotalDistance(data)
        println(result)
    }

}