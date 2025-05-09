package me.sivieri.aoc2024.day22

class Buyers(seeds: List<Int>) {

    private val buyer = Buyer()
    private val changes = seeds.map { seed ->
        (0 until ITERATIONS)
            .fold(listOf(seed)) { acc, _ ->
                acc + buyer.calculateSecretNumber(acc.last())
            }
            .map { it.toString().last().digitToInt() }
            .windowed(2)
            .map { it[1] - it[0] }
    }

    fun calculateAllQuartets(): Set<List<Int>> {
        val quartets = mutableSetOf<List<Int>>()
        changes.forEach { l ->
            l.windowed(4).forEach { q ->
                if (q[1] != q[0] && q[2] != q[1] && q[3] != q[2]) quartets.add(q)
            }
        }
        return quartets.toSet()
    }

    fun calculatePricesForQuartet(quartet: List<Int>): Int {
        val singles = changes
            .map { change ->
                val qWithIndexes = change
                    .windowed(4)
                    .mapIndexed { i, l -> Pair(l, i) }
                val i = qWithIndexes
                    .firstOrNull { it.first == quartet }
                    ?.second
                    ?: -1
                if (i == -1) 0
                else change[i + 3]
            }
        return singles.sum()
    }

    companion object {
        private const val ITERATIONS = 2000
    }

}