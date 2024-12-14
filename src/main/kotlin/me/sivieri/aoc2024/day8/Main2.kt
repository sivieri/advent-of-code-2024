package me.sivieri.aoc2024.day8

import me.sivieri.aoc2024.common.Utils

object Main2 {

    @JvmStatic
    fun main(args: Array<String>) {
        val data = Utils.readInput(8)
        val map = AntennasMap(data)
        val result = map.countAntinodesIgnoringDistance()
        println (result)
    }

}