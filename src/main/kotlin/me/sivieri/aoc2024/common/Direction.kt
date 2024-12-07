package me.sivieri.aoc2024.common

enum class Direction {
    UP,
    DOWN,
    LEFT,
    RIGHT;

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
}
