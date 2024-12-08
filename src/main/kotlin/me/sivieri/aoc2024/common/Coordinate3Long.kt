package me.sivieri.aoc2024.common

import kotlin.math.pow
import kotlin.math.abs

data class Coordinate3Long(
    val x: Long,
    val y: Long,
    val z: Long
): Comparable<Coordinate3Long> {

    override fun compareTo(other: Coordinate3Long): Int {
        val thisDistance = distance(ORIGIN)
        val otherDistance = other.distance(ORIGIN)
        return thisDistance.compareTo(otherDistance)
    }

    private fun distance(other: Coordinate3Long): Long =
        kotlin.math.sqrt((other.x - x).toDouble().pow(2) + (other.y - y).toDouble().pow(2) + (other.y - y).toDouble().pow(2)).toLong()

    fun manhattanDistance(other: Coordinate3Long): Long =
        abs(x - other.x) + abs(y - other.y) + abs(z - other.z)

    operator fun minus(other: Coordinate3Long): Coordinate3Long =
        Coordinate3Long(
            x - other.x,
            y - other.y,
            z - other.z
        )

    companion object {
        val ORIGIN = Coordinate3Long(0, 0, 0)

        fun parseString(s: String, separator: String = ","): Coordinate3Long {
            val (x, y, z) = s.split(separator, limit = 3)
            return Coordinate3Long(x.toLong(), y.toLong(), z.toLong())
        }

    }

}
