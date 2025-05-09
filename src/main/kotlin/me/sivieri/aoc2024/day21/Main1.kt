package me.sivieri.aoc2024.day21

import me.sivieri.aoc2024.common.Utils

object Main1 {

    @JvmStatic
    fun main(args: Array<String>) {
        val data = Utils.readInputToList(21)
        val door = Door()
        val result = data.sumOf { door.calculateComplexity(it) }
        println(result)
    }

}