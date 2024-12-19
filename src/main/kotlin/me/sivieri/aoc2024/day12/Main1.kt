package me.sivieri.aoc2024.day12

import me.sivieri.aoc2024.common.Utils

object Main1 {

    @JvmStatic
    fun main(args: Array<String>) {
        val data = Utils.readInput(12)
        val garden = Garden(data)
        val result = garden.calculateFencingPrice()
        println(result)
    }

}