package me.sivieri.aoc2024.day5

import me.sivieri.aoc2024.common.Utils

object Main2 {

    @JvmStatic
    fun main(args: Array<String>) {
        val data = Utils.readInput(5)
        val printer = Printer(data)
        val result = printer.sumMiddlePagesAfterCorrection()
        println(result)
    }

}