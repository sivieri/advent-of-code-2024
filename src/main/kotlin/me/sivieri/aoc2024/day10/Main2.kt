package me.sivieri.aoc2024.day10

import me.sivieri.aoc2024.common.Utils

object Main2 {

    @JvmStatic
    fun main(args: Array<String>) {
        val data = Utils.readInput(10)
        val map = TopographicMap(data)
        val score = map.sumTrailheadRatings()
        println(score)
    }

}