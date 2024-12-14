package me.sivieri.aoc2024.common

import kotlin.math.abs
import kotlin.math.pow
import kotlin.math.sqrt

data class Coordinate2Payload<T>(
    val x: Int,
    val y: Int,
    val payload: T
): Comparable<Coordinate2Payload<T>> {

    fun distance(other: Coordinate2Payload<T>): Int =
        sqrt((other.x - x).toDouble().pow(2) + (other.y - y).toDouble().pow(2) + (other.y - y).toDouble().pow(2)).toInt()

    fun manhattanDistance(other: Coordinate2Payload<T>): Int =
        abs(this.x - other.x) + abs(this.y - other.y)

    fun toCoordinate2(): Coordinate2 = Coordinate2(x, y)

    override fun compareTo(other: Coordinate2Payload<T>): Int = when {
        originDistance(this) < originDistance(other) -> -1
        originDistance(this) > originDistance(other) -> 1
        else -> 0
    }

    override fun toString(): String {
        return "($x, $y, $payload)"
    }

    companion object {
        private val ORIGIN_X = 0
        private val ORIGIN_Y = 0

        private fun <T> originDistance(point: Coordinate2Payload<T>): Int =
            sqrt((ORIGIN_X - point.x).toDouble().pow(2) + (ORIGIN_Y - point.y).toDouble().pow(2) + (ORIGIN_Y - point.y).toDouble().pow(2)).toInt()
    }

}
