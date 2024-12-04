package me.sivieri.aoc2024.day4

import me.sivieri.aoc2024.common.Utils

object Main2 {

    @JvmStatic
    fun main(args: Array<String>) {
        val data = Utils.readInput(4)
        val wordField = WordField(data)
        val result = wordField.countXAndMasOccurrences()
        println(result)
    }

}