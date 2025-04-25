package me.sivieri.aoc2024.day22

import kotlin.math.floor

class Buyer {

    fun calculateIterations(seed: Long, iterations: Int): Long =
        (0 until iterations).fold(seed) { acc, _ ->
            calculateSecretNumber(acc)
        }

    fun calculateSecretNumber(seed: Long): Long {
        val step1 = prune(mix(seed * STEP1, seed))
        val step2 = prune(mix(floor(step1 / STEP2).toLong(), step1))
        val step3 = prune(mix(step2 * STEP3, step2))
        return step3
    }

    private fun mix(a: Long, b: Long): Long = a.xor(b)

    private fun prune(a: Long): Long = a.mod(PRUNED_VALUE)

    companion object {
        private const val PRUNED_VALUE = 16777216L
        private const val STEP1 = 64L
        private const val STEP2 = 32.0
        private const val STEP3 = 2048L
    }

}