package me.sivieri.aoc2024.day2

import kotlin.math.abs

data class Report(
    val levels: List<Int>
) {

    fun isSafe(): Boolean {
        val windowed = levels.windowed(2)
        val allAscending = windowed.all { it[1] > it[0] }
        val allDescending = windowed.all { it[1] < it[0] }
        val diff = windowed.all { abs(it[0] - it[1]) in 1..3 }
        return (allAscending || allDescending) && diff
    }

    companion object {
        fun fromString(s: String): Report = Report(s.split(" ").map { it.toInt() })
    }
}
