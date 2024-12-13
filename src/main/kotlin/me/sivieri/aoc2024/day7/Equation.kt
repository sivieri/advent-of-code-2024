package me.sivieri.aoc2024.day7

import me.sivieri.aoc2024.common.combinationsWithRepetition
import me.sivieri.aoc2024.common.head
import me.sivieri.aoc2024.common.permutations
import me.sivieri.aoc2024.common.tail

data class Equation(
    val index: Int,
    val result: Long,
    val values: List<Long>
) {
    fun tryRepairOperators(): Boolean =
        listOf(Plus, Mult)
            .combinationsWithRepetition(values.size - 1)
            .flatMap { it.permutations() }
            .any { combo ->
                val opsWithValues = values.tail().zip(combo)
                val result = opsWithValues.fold(values.head()) { acc, (cur, op) ->
                    op.apply(acc, cur)
                }
                result == this.result
            }
}
