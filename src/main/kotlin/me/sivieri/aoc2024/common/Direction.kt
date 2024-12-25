package me.sivieri.aoc2024.common

enum class Direction(private val c: Char) {
    UP('^'),
    DOWN('v'),
    LEFT('<'),
    RIGHT('>');

    fun orthogonal(): List<Direction> = when (this) {
        UP -> listOf(LEFT, RIGHT)
        DOWN -> listOf(LEFT, RIGHT)
        LEFT -> listOf(UP, DOWN)
        RIGHT -> listOf(UP, DOWN)
    }

    fun turnRight(): Direction = when (this) {
        UP -> RIGHT
        DOWN -> LEFT
        LEFT -> UP
        RIGHT -> DOWN
    }

    fun updateCoordinate(c: Coordinate2): Coordinate2 = when (this) {
        UP -> Coordinate2(c.x, c.y - 1)
        DOWN -> Coordinate2(c.x, c.y + 1)
        LEFT -> Coordinate2(c.x - 1, c.y)
        RIGHT -> Coordinate2(c.x + 1, c.y)
    }

    companion object {
        fun parse(c: Char): Direction = when (c) {
            '<' -> LEFT
            '>' -> RIGHT
            '^' -> UP
            'v' -> DOWN
            else -> throw IllegalArgumentException("$c is not a direction")
        }
    }
}
