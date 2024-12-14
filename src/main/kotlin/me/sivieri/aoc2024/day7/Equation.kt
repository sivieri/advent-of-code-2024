package me.sivieri.aoc2024.day7

import me.sivieri.aoc2024.common.head
import me.sivieri.aoc2024.common.tail
import me.sivieri.aoc2024.external.combinationsWithRepetition
import me.sivieri.aoc2024.external.permutations

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
                var result = values.head()
                val accepted = opsWithValues.all { (value, op) ->
                    result = op.apply(result, value)
                    result <= this.result
                }
                accepted && result == this.result
            }

    fun tryRepairOperatorsWithConcat(): Boolean =
        listOf(Plus, Mult, Concat)
            .combinationsWithRepetition(values.size - 1)
            .flatMap { it.permutations() }
            .any { combo ->
                val opsWithValues = values.tail().zip(combo)
                var result = values.head()
                val accepted = opsWithValues.all { (value, op) ->
                    result = op.apply(result, value)
                    result <= this.result
                }
                accepted && result == this.result
            }
}
