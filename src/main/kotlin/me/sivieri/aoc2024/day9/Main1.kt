package me.sivieri.aoc2024.day9

import me.sivieri.aoc2024.common.Utils

object Main1 {

    @JvmStatic
    fun main(args: Array<String>) {
        val data = Utils.readInput(9)
        val manager = DiskManager(data)
        manager.compact()
        val result = manager.checksum()
        println(result)
    }

}