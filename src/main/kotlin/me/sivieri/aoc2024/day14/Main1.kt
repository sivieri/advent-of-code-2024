package me.sivieri.aoc2024.day14

import me.sivieri.aoc2024.common.Utils

object Main1 {

    @JvmStatic
    fun main(args: Array<String>) {
        val data = Utils.readInputToList(14)
        val room = Room(data, 101, 103)
        val result = room.findSafetyFactor(100)
        println(result)
    }

}