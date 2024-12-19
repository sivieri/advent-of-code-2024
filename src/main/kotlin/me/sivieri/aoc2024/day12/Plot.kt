package me.sivieri.aoc2024.day12

import me.sivieri.aoc2024.common.Coordinate2

data class Plot(
    val name: String,
    val space: List<Coordinate2>
) {
    val neighbors: List<Coordinate2> = space.flatMap { it.neighbors() }.minus(space)

    fun calculateFencingPrice(): Int = area() * perimeter()

    fun area(): Int = space.size

    fun perimeter(): Int = neighbors.size

    override fun toString(): String {
        return "Plot(name='$name', perimeter=${perimeter()}, area=${area()})"
    }

}
