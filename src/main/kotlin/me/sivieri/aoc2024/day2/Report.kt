package me.sivieri.aoc2024.day2

import kotlin.math.abs

data class Report(
    val levels: List<Int>
) {

    fun isSafe(): Boolean = Companion.isSafe(levels)

    fun isSafeWithProblemDampener(): Boolean {
        return if (Companion.isSafe(levels)) true
        else levels.indices.any { i ->
            val sublist = if (i == levels.size - 1) levels.subList(0, i)
            else levels.subList(0, i) + levels.subList(i + 1, levels.size)
            Companion.isSafe(sublist)
        }
    }

    companion object {
        fun fromString(s: String): Report = Report(s.split(" ").map { it.toInt() })

        private fun isSafe(levels: List<Int>): Boolean {
            val windowed = levels.windowed(2)
            val allAscending = windowed.all { it[1] > it[0] }
            val allDescending = windowed.all { it[1] < it[0] }
            val diff = windowed.all { abs(it[0] - it[1]) in 1..3 }
            return (allAscending || allDescending) && diff
        }
    }
}
