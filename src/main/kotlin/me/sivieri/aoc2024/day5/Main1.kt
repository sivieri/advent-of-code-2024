package me.sivieri.aoc2024.day5

import me.sivieri.aoc2024.common.Utils

object Main1 {

    @JvmStatic
    fun main(args: Array<String>) {
        val data = Utils.readInput(5)
        val printer = Printer(data)
        val result = printer.sumMiddlePagesOfValidUpdates()
        println(result)
    }

}