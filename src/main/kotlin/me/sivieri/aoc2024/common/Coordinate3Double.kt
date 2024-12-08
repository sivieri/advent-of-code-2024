package me.sivieri.aoc2024.common

import kotlin.math.pow
import kotlin.math.abs

data class Coordinate3Double(
    val x: Double,
    val y: Double,
    val z: Double
): Comparable<Coordinate3Double> {

    override fun compareTo(other: Coordinate3Double): Int {
        val thisDistance = distance(ORIGIN)
        val otherDistance = other.distance(ORIGIN)
        return thisDistance.compareTo(otherDistance)
    }

    private fun distance(other: Coordinate3Double): Double =
        kotlin.math.sqrt((other.x - x).pow(2) + (other.y - y).pow(2) + (other.y - y).pow(2))

    fun manhattanDistance(other: Coordinate3Double): Double =
        abs(x - other.x) + abs(y - other.y) + abs(z - other.z)

    operator fun minus(other: Coordinate3Double): Coordinate3Double =
        Coordinate3Double(
            x - other.x,
            y - other.y,
            z - other.z
        )

    companion object {
        val ORIGIN = Coordinate3Double(0.0, 0.0, 0.0)

        fun parseString(s: String, separator: String = ","): Coordinate3Double {
            val (x, y, z) = s.split(separator, limit = 3)
            return Coordinate3Double(x.toDouble(), y.toDouble(), z.toDouble())
        }

    }

}
