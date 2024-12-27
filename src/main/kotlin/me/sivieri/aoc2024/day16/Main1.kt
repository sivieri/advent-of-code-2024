package me.sivieri.aoc2024.day16

import me.sivieri.aoc2024.common.Utils

object Main1 {

    @JvmStatic
    fun main(args: Array<String>) {
        val data = Utils.readInput(16)
        val maze = Maze(data)
        val result = maze.shortestPathCost()
        println(result)
    }

}