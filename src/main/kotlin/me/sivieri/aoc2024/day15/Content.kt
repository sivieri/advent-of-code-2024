package me.sivieri.aoc2024.day15

import me.sivieri.aoc2024.common.Coordinate2

sealed class Content(
    val c: List<Coordinate2>
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is Content) return false

        if (c != other.c) return false

        return true
    }

    override fun hashCode(): Int {
        return c.hashCode()
    }

    override fun toString(): String {
        return "Content(c=$c)"
    }

}

class Robot(c: Coordinate2): Content(listOf(c))

class Wall(c: Coordinate2): Content(listOf(c, Coordinate2(c.x + 1, c.y)))

class Box(c: Coordinate2): Content(listOf(c, Coordinate2(c.x + 1, c.y)))

class Empty(c: Coordinate2): Content(listOf(c))
