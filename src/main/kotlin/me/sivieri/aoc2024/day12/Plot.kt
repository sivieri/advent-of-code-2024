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
            val curSpaces = space.filter { it.x == x }.map { it.y }
            val nextSpaces = space.filter { it.x == x + 1 }.map { it.y }
            compareSides(curSpaces, nextSpaces)
        }

        // sum of y sides
        val eyes = ((my - 1) .. (may + 1)).sumOf { y ->
            val curSpaces = space.filter { it.y == y }.map { it.x }
            val nextSpaces = space.filter { it.y == y + 1 }.map { it.x }
            compareSides(curSpaces, nextSpaces)
        }

        return exes + eyes
    }

    @Suppress("KotlinConstantConditions")
    private fun compareSides(curSpaces: List<Int>, nextSpaces: List<Int>): Int {
        if (curSpaces == nextSpaces) return 0
        val min = (curSpaces + nextSpaces).minOrNull()!!
        val max = (curSpaces + nextSpaces).maxOrNull()!!
        var counter = 0
        ((min - 1)..(max + 1))
            .windowed(2)
            .forEach { (i, j) ->
                val ci = curSpaces.contains(i)
                val ni = nextSpaces.contains(i)
                val cj = curSpaces.contains(j)
                val nj = nextSpaces.contains(j)
                // vertical example, looking for A
                when {
                    // AA
                    // AA
                    (ci && ni) && (cj && nj) -> { /* nothing to be done */ }
                    // AA
                    // AB
                    (ci && ni) && (cj && !nj) -> counter++
                    // AB
                    // AA
                    (ci && ni) && (!cj && nj) -> counter++
                    // AB
                    // AB
                    (ci && ni) && (!cj && !nj) -> { /* nothing changed vertically */ }
                    // BA
                    // AA
                    (!ci && ni) && (cj && nj) -> { /* fence counted when entering */ }
                    // BA
                    // AB
                    (!ci && ni) && (cj && !nj) -> counter++
                    // BB
                    // AA
                    (!ci && ni) && (!cj && nj) -> { /* nothing to be done */ }
                    // BB
                    // AB
                    (!ci && ni) && (!cj && !nj) -> { /* fence counted when entering */ }
                    // AA
                    // BA
                    (ci && !ni) && (cj && nj) -> { /* fence counted when entering */ }
                    // AA
                    // BB
                    (ci && !ni) && (cj && !nj) -> { /* nothing to be done */ }
                    // AB
                    // BA
                    (ci && !ni) && (!cj && nj) -> counter++
                    // AB
                    // BB
                    (ci && !ni) && (!cj && !nj) -> { /* fence counted when entering */ }
                    // BA
                    // BA
                    (!ci && !ni) && (cj && nj) -> { /* nothing to be done */ }
                    // BA
                    // BB
                    (!ci && !ni) && (cj && !nj) -> counter++
                    // BB
                    // BA
                    (!ci && !ni) && (!cj && nj) -> counter++
                    // BB
                    // BB
                    (!ci && !ni) && (!cj && !nj) -> { /* nothing to be done */ }
                }
            }
        return counter
    }

    override fun toString(): String {
        return "Plot(name='$name', perimeter=${perimeter()}, area=${area()}, sides=${sides()})"
    }

}
