package me.sivieri.aoc2024.day11

class PlutoPebbles(data: String) {

    private val stones: MutableList<Long> = data.trim('\n').split(" ").map { it.toLong() }.toMutableList()
    private var rounds: Int = 0

    fun play(times: Int) = (0 until times).forEach { play() }

    private fun play() {
        rounds++
        var counter = 0
        var max = stones.size
        while (counter < max) {
            val s = stones[counter].toString()
            when {
                stones[counter] == 0L -> stones[counter] = 1
                s.length % 2 == 0 -> {
                    val part1 = s.substring(0, s.length / 2)
                    val part2 = s.substring(s.length / 2, s.length)
                    stones[counter] = part1.toLong()
                    stones.add(counter + 1, part2.toLong())
                    max++
                    counter++
                }
                else -> stones[counter] = stones[counter] * 2024
            }
            counter++
        }
    }

    fun score(): Int = stones.size

}