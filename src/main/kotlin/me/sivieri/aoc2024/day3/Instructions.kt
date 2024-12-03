package me.sivieri.aoc2024.day3

sealed interface Instruction

data class Multiply(
    val a: Long,
    val b: Long
): Instruction {
    fun result(): Long = a * b

    companion object {
        val regex = "mul\\([0-9]+,[0-9]+\\)".toRegex()
    }
}

data object Do: Instruction

data object Dont: Instruction
