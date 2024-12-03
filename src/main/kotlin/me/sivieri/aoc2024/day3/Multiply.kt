package me.sivieri.aoc2024.day3

data class Multiply(
    val a: Long,
    val b: Long
) {
    fun result(): Long = a * b

    companion object {
        val regex = "mul\\([0-9]+,[0-9]+\\)".toRegex()
    }
}
