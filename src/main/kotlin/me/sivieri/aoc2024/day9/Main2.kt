package me.sivieri.aoc2024.day9

import me.sivieri.aoc2024.common.Utils

object Main2 {

    @JvmStatic
    fun main(args: Array<String>) {
        val data = Utils.readInput(9)
        val manager = DiskManager(data)
        manager.compactFullFiles()
        val result = manager.checksum()
        println(result)
    }

}