package me.sivieri.aoc2024.day3

import me.sivieri.aoc2024.common.Utils

object Main2 {

    @JvmStatic
    fun main(args: Array<String>) {
        val data = Utils.readInput(3)
        val result = Computer.multiplyEnabledResult(data)
        println(result)
    }

}