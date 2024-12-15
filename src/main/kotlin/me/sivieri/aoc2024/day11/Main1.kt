package me.sivieri.aoc2024.day11

import me.sivieri.aoc2024.common.Utils

object Main1 {

    @JvmStatic
    fun main(args: Array<String>) {
        val data = Utils.readInput(11)
        val pebbles = PlutoPebbles(data)
        pebbles.play(25)
        val score = pebbles.score()
        println(score)
    }

}