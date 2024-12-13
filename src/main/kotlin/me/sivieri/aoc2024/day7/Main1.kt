package me.sivieri.aoc2024.day7

import me.sivieri.aoc2024.common.Utils

object Main1 {

    @JvmStatic
    fun main(args: Array<String>) {
        val data = Utils.readInputToList(7)
        val calibration = Calibration(data)
        val result = calibration.calibrateAndSum()
        println(result)
    }

}