package me.sivieri.aoc2024.day13

import me.sivieri.aoc2024.common.Utils

object Main2 {

    @JvmStatic
    fun main(args: Array<String>) {
        val data = Utils.readInput(13)
        val arcade = Arcade(data)
        val result = arcade.minLargerTokens()
        println(result)
    }

}