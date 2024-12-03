package me.sivieri.aoc2024.day3

import me.sivieri.aoc2024.common.Utils

object Main1 {

    @JvmStatic
    fun main(args: Array<String>) {
        val data = Utils.readInput(3)
        val instructions = Computer.findUncorruptedMultiply(data)
        val result = instructions.sumOf { it.result() }
        println(result)
    }

}