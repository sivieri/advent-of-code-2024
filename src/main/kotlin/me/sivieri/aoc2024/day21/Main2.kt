package me.sivieri.aoc2024.day21

import me.sivieri.aoc2024.common.Utils

object Main2 {

    @JvmStatic
    fun main(args: Array<String>) {
        val data = Utils.readInputToList(21)
        val copied = CopiedPart2()
        val result = copied.solve(data, 26)
        println(result)
    }

}