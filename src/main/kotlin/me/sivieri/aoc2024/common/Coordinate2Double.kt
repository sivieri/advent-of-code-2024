package me.sivieri.aoc2024.common

import kotlin.math.abs
import kotlin.math.pow

data class Coordinate2Double(
    val x: Double,
    val y: Double
): Comparable<Coordinate2Double> {

    fun distance(other: Coordinate2Double): Double =
        kotlin.math.sqrt((other.x - x).pow(2) + (other.y - y).pow(2) + (other.y - y).pow(2))

    fun manhattanDistance(other: Coordinate2Double): Double =
        abs(this.x - other.x) + abs(this.y - other.y)

    fun findOpposite(pivot: Coordinate2Double): Coordinate2Double {
        val xdiff = this.x - pivot.x
        val ydiff = this.y - pivot.y
        return Coordinate2Double(pivot.x - xdiff, pivot.y - ydiff)
    }

    override fun compareTo(other: Coordinate2Double): Int = when {
        ORIGIN.distance(this) < ORIGIN.distance(other) -> -1
        ORIGIN.distance(this) > ORIGIN.distance(other) -> 1
        else -> 0
    }

    override fun toString(): String {
        return "($x, $y)"
    }


    companion object {
        val ORIGIN = Coordinate2Double(0.0, 0.0)

        fun parseString(s: String, separator: String = ","): Coordinate2Double {
            val (x, y) = s.split(separator, limit = 2)
            return Coordinate2Double(x.toDouble(), y.toDouble())
        }

    }

}
