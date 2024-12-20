package me.sivieri.aoc2024.day12

import me.sivieri.aoc2024.common.Coordinate2

@Suppress("ConvertArgumentToSet")
data class Plot(
    val name: String,
    val space: List<Coordinate2>
) {
    val neighbors: List<Coordinate2> = space.flatMap { it.neighbors() }.minus(space)

    fun calculateFencingPrice(): Int = area() * perimeter()

    fun calculateFencingSidesPrice(): Int = area() * sides()

    fun area(): Int = space.size

    fun perimeter(): Int = neighbors.size

    fun sides(): Int {
        val mx = space.minOf { it.x }
        val my = space.minOf { it.y }
        val max = space.maxOf { it.x }
        val may = space.maxOf { it.y }

        // sum of x sides
        val exes = ((mx - 1) .. (max + 1)).sumOf { x ->
            val cur = space.count { it.x == x }
            val curSpaces = space.filter { it.x == x }.map { it.y }
            val next = space.count { it.x == x + 1 }
            val nextSpaces = space.filter { it.x == x + 1 }.map { it.y }
            compareSides(cur, next, curSpaces, nextSpaces)
        }

        // sum of y sides
        val eyes = ((my - 1) .. (may + 1)).sumOf { y ->
            val cur = space.count { it.y == y }
            val curSpaces = space.filter { it.y == y }.map { it.x }
            val next = space.count { it.y == y + 1 }
            val nextSpaces = space.filter { it.y == y + 1 }.map { it.x }
            compareSides(cur, next, curSpaces, nextSpaces)
        }

        return exes + eyes
    }

    private fun compareSides(cur: Int, next: Int, curSpaces: List<Int>, nextSpaces: List<Int>): Int {
        if (curSpaces == nextSpaces) return 0
        val intersection = curSpaces.intersect(nextSpaces)
        val unique = (curSpaces - intersection) + (nextSpaces - intersection)
        return unique
            .sorted()
            .windowed(2)
            .fold(1) { acc, (first, second) ->
                if (second - first > 1) acc + 1
                else acc
            }
    }

    override fun toString(): String {
        return "Plot(name='$name', perimeter=${perimeter()}, area=${area()}, sides=${sides()})"
    }

}
