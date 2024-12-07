package me.sivieri.aoc2024.day6

import me.sivieri.aoc2024.common.Coordinate2D
import me.sivieri.aoc2024.common.Direction

class LaboratoryMap(input: String) {

    private val map = input
        .split("\n")
        .filter { it.isNotBlank() }
        .map { it.trim('\n').toCharArray().toTypedArray() }
        .toTypedArray()
    private val maxX = map[0].size
    private val maxY = map.size
    private var guard: Coordinate2D = Coordinate2D(-1, -1)

    init {
        (0 until maxY).forEach { y ->
            (0 until maxX).forEach { x ->
                if (map[y][x] == GUARD) {
                    guard = Coordinate2D(x, y)
                    map[y][x] = EMPTY
                }
            }
        }
    }

    fun countPatrol(): Int {
        var current = Pair(guard, Direction.UP)
        val positions = mutableSetOf<Coordinate2D>()

        while (current.first.isValid()) {
            val possibleDirections = listOf(
                current.second,
                current.second.turnRight(),
                current.second.turnRight().turnRight(),
                current.second.turnRight().turnRight().turnRight(),
            )
            possibleDirections.fold(null as Direction?) { cur, dir ->
                if (cur == null) {
                    val next = move(current.first, dir)
                    if (next.isValid()) {
                        if (map[next.y][next.x] == EMPTY) {
                            positions.add(next)
                            current = Pair(next, dir)
                            dir
                        }
                        else null
                    }
                    else {
                        current = Pair(next, dir)
                        dir
                    }
                }
                else cur
            }
        }

        return positions.size
    }

    private fun move(position: Coordinate2D, direction: Direction): Coordinate2D = when (direction) {
        Direction.UP -> Coordinate2D(position.x, position.y - 1)
        Direction.DOWN -> Coordinate2D(position.x, position.y + 1)
        Direction.LEFT -> Coordinate2D(position.x - 1, position.y)
        Direction.RIGHT -> Coordinate2D(position.x + 1, position.y)
    }

    private fun Coordinate2D.isValid(): Boolean =
        this.x >= 0 &&
        this.x < maxX &&
        this.y >= 0 &&
        this.y < maxY

    companion object {
        private const val EMPTY = '.'
        private const val OBSTACLE = '#'
        private const val GUARD = '^'
    }

}