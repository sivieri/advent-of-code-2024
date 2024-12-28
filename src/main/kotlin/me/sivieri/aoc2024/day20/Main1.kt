package me.sivieri.aoc2024.day20

import me.sivieri.aoc2024.common.Utils

object Main1 {

    @JvmStatic
    fun main(args: Array<String>) {
        val data = Utils.readInput(20)
        val racetrack = Racetrack(data)
        val result = racetrack.countCheats(100)
        println(result)
    }

}