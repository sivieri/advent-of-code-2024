package me.sivieri.aoc2024.common

import kotlin.math.pow
import kotlin.math.abs

data class Coordinate3(
    val x: Int,
    val y: Int,
    val z: Int
): Comparable<Coordinate3> {

    override fun compareTo(other: Coordinate3): Int {
        val thisDistance = distance(ORIGIN)
        val otherDistance = other.distance(ORIGIN)
        return thisDistance.compareTo(otherDistance)
    }

    private fun distance(other: Coordinate3): Int =
        kotlin.math.sqrt((other.x - x).toDouble().pow(2) + (other.y - y).toDouble().pow(2) + (other.y - y).toDouble().pow(2)).toInt()

    fun manhattanDistance(other: Coordinate3): Int =
        abs(x - other.x) + abs(y - other.y) + abs(z - other.z)

    operator fun minus(other: Coordinate3): Coordinate3 =
        Coordinate3(
            x - other.x,
            y - other.y,
            z - other.z
        )

    companion object {
        val ORIGIN = Coordinate3(0, 0, 0)

        fun parseString(s: String, separator: String = ","): Coordinate3 {
            val (x, y, z) = s.split(separator, limit = 3)
            return Coordinate3(x.toInt(), y.toInt(), z.toInt())
        }

    }

}
