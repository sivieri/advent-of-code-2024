package me.sivieri.aoc2024.day18

import me.sivieri.aoc2024.common.Utils

object Main2 {

    @JvmStatic
    fun main(args: Array<String>) {
        val data = Utils.readInputToList(18)
        val ram = RAM(data, 71, 71)
        val result = ram.findBlockingPath(1024)
        println(result)
    }

}