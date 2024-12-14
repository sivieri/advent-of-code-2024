package me.sivieri.aoc2024.day7

class Calibration(data: List<String>) {

    private val equations: List<Equation> = data
        .mapIndexed { index, d ->
            val parts = d.split(": ")
            Equation(
                index,
                parts[0].toLong(),
                parts[1].split(" ").map { it.toLong() }
            )
        }

    fun calibrateAndSum(): Long = equations
        .filter { println(it.index); it.tryRepairOperators() }
        .sumOf { it.result }

    fun calibrateAndSumWithConcat(): Long = equations
        .filter { println(it.index); it.tryRepairOperatorsWithConcat() }
        .sumOf { it.result }

}