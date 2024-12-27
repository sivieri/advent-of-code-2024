package me.sivieri.aoc2024.day17

import me.sivieri.aoc2024.common.Utils

object Main2 {

    @JvmStatic
    fun main(args: Array<String>) {
        val data = Utils.readInput(17)
        val computer = Computer(data)
        val result = computer.findRegisterValue()
        println(result)
    }

}