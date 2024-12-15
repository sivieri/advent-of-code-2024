package me.sivieri.aoc2024.day11

class PlutoPebblesV2(data: String) {

    private var rounds: Int = 0
    var zeroes: Long = 0L
    var even: MutableMap<Long, Long> = mutableMapOf()
    var others: MutableMap<Long, Long> = mutableMapOf()

    init {
        data
            .trim('\n')
            .split(" ")
            .map { it.toLong() }
            .forEach { i ->
                when {
                    i == 0L -> zeroes++
                    i.toString().length % 2 == 0 -> even.incr(i)
                    else -> others.incr(i)
                }
            }
    }

    fun play(times: Int) = (0 until times).forEach { play() }

    fun play() {
        rounds++

        var updatedZeroes = 0L
        val updatedEven = mutableMapOf<Long, Long>()
        val updatedOthers = mutableMapOf<Long, Long>()

        if (zeroes > 0) updatedOthers.incr(1, zeroes)
        even.forEach { (value, n) ->
            val s = value.toString()
            val part1 = s.substring(0, s.length / 2).toLong()
            val part2 = s.substring(s.length / 2, s.length).toLong()
            if (part1 == 0L) updatedZeroes += n
            else if (part1.toString().length % 2 == 0) updatedEven.incr(part1, n)
            else updatedOthers.incr(part1, n)
            if (part2 == 0L) updatedZeroes += n
            else if (part2.toString().length % 2 == 0) updatedEven.incr(part2, n)
            else updatedOthers.incr(part2, n)
        }
        others.forEach { (value, n) ->
            val res = value * MULTIPLIER
            val s = res.toString()
            if (s.length % 2 == 0) updatedEven.incr(res, n)
            else updatedOthers.incr(res, n)
        }

        zeroes = updatedZeroes
        even = updatedEven
        others = updatedOthers
    }

    fun score(): Long = zeroes + even.values.sum() + others.values.sum()

    companion object {
        private const val MULTIPLIER = 2024

        private fun MutableMap<Long, Long>.incr(key: Long) {
            if (this[key] != null) this[key] = this[key]!! + 1
            else this[key] = 1
        }

        private fun MutableMap<Long, Long>.incr(key: Long, n: Long) {
            if (this[key] != null) this[key] = this[key]!! + n
            else this[key] = n
        }
    }

}