package me.sivieri.aoc2024.day22

import me.sivieri.aoc2024.common.Utils

object Main1 {

    @JvmStatic
    fun main(args: Array<String>) {
        val data = Utils.readInputToList(22)
        val buyer = Buyer()
        val result = data.sumOf { buyer.calculateIterations(it.toInt(), 2000) }
        println(result)
    }

}