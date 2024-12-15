package me.sivieri.aoc2024.day11

import me.sivieri.aoc2024.common.Utils

object Main2 {

    @JvmStatic
    fun main(args: Array<String>) {
        val data = Utils.readInput(11)
        val pebbles = PlutoPebblesV2(data)
        pebbles.play(75)
        val score = pebbles.score()
        println(score)
    }

}