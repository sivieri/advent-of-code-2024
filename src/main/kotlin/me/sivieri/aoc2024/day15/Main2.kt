package me.sivieri.aoc2024.day15

import me.sivieri.aoc2024.common.Utils

object Main2 {

    @JvmStatic
    fun main(args: Array<String>) {
        val data = Utils.readInput(15)
        val warehouse = Warehouse(data)
        val result = warehouse.sumGPScoordsExtended()
        println(result)
    }

}