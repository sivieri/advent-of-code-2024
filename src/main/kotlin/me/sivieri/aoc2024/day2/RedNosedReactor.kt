package me.sivieri.aoc2024.day2

class RedNosedReactor(
    private val reports: List<Report>
) {

    fun countSafeReports(): Int = reports.count { it.isSafe() }

}