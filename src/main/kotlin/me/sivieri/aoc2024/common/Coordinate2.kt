package me.sivieri.aoc2024.common

import kotlin.math.abs
import kotlin.math.pow

data class Coordinate2(
    val x: Int,
    val y: Int
): Comparable<Coordinate2> {

    fun distance(other: Coordinate2): Int =
        kotlin.math.sqrt((other.x - x).toDouble().pow(2) + (other.y - y).toDouble().pow(2) + (other.y - y).toDouble().pow(2)).toInt()

    fun manhattanDistance(other: Coordinate2): Int =
        abs(this.x - other.x) + abs(this.y - other.y)

    override fun compareTo(other: Coordinate2): Int = when {
        ORIGIN.distance(this) < ORIGIN.distance(other) -> -1
        ORIGIN.distance(this) > ORIGIN.distance(other) -> 1
        else -> 0
    }

    override fun toString(): String {
        return "($x, $y)"
    }


    companion object {
        val ORIGIN = Coordinate2(0, 0)

        fun parseString(s: String, separator: String = ","): Coordinate2 {
            val (x, y) = s.split(separator, limit = 2)
            return Coordinate2(x.toInt(), y.toInt())
        }

    }

}
