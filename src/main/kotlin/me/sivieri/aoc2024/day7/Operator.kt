package me.sivieri.aoc2024.day7

sealed interface Operator {
    fun apply(left: Long, right: Long): Long
}

data object Plus: Operator {
    override fun apply(left: Long, right: Long): Long = left + right
}

data object Mult: Operator {
    override fun apply(left: Long, right: Long): Long = left * right
}