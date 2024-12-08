package me.sivieri.aoc2024.day6

import me.sivieri.aoc2024.common.Utils

object Main2 {

    @JvmStatic
    fun main(args: Array<String>) {
        val data = Utils.readInput(6)
        val lab = LaboratoryMap(data)
        val result = lab.countObstaclesForLoops()
        println(result)
    }

}