package me.sivieri.aoc2024.day5

data class Rule(
    val before: Int,
    val after: Int,
    var status: RuleStatus
)
