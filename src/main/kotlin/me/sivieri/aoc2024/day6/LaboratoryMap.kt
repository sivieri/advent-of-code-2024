package me.sivieri.aoc2024.day6

import me.sivieri.aoc2024.common.Coordinate2
import me.sivieri.aoc2024.common.Coordinate2Payload
import me.sivieri.aoc2024.common.Direction

class LaboratoryMap(input: String) {

    private val map = input
        .split("\n")
        .filter { it.isNotBlank() }
        .map { it.trim('\n').toCharArray().toTypedArray() }
        .toTypedArray()
    private val maxX = map[0].size
    private val maxY = map.size
    private var guard: Coordinate2 = Coordinate2(-1, -1)

    init {
        (0 until maxY).forEach { y ->
            (0 until maxX).forEach { x ->
                if (map[y][x] == GUARD) {
                    guard = Coordinate2(x, y)
                    map[y][x] = EMPTY
                }
            }
        }
    }

    fun countPatrol(): Int {
        var current = Pair(guard, Direction.UP)
        val positions = mutableSetOf<Coordinate2>()

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

    fun countObstaclesForLoops(): Int {
        var loops = 0
        var current = Pair(guard, Direction.UP)
        val positions = mutableSetOf<Coordinate2>()

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
                            loops += checkForLoop(current.first, Coordinate2Payload(next.x, next.y, dir))
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

        return loops
    }

    private fun checkForLoop(obstacle: Coordinate2, position: Coordinate2Payload<Direction>): Int {
        val positions = mutableListOf(position)
        var current = position

        map[obstacle.y][obstacle.x] = OBSTACLE
        while (current.isValid()) {
            val possibleDirections = listOf(
                current.payload,
                current.payload.turnRight(),
                current.payload.turnRight().turnRight(),
                current.payload.turnRight().turnRight().turnRight(),
            )
            possibleDirections.fold(null as Direction?) { cur, dir ->
                if (cur == null) {
                    val next = move(current, dir)
                    if (next.isValid()) {
                        if (map[next.y][next.x] == EMPTY) {
                            if (positions.contains(next)) { // loop
                                map[obstacle.y][obstacle.x] = EMPTY
                                return 1
                            }
                            positions.add(next)
                            current = next
                            dir
                        }
                        else null
                    }
                    else {
                        current = next
                        dir
                    }
                }
                else cur
            }
        }

        // no loops found
        map[obstacle.y][obstacle.x] = EMPTY
        return 0
    }

    private fun move(position: Coordinate2, direction: Direction): Coordinate2 = when (direction) {
        Direction.UP -> Coordinate2(position.x, position.y - 1)
        Direction.DOWN -> Coordinate2(position.x, position.y + 1)
        Direction.LEFT -> Coordinate2(position.x - 1, position.y)
        Direction.RIGHT -> Coordinate2(position.x + 1, position.y)
    }

    private fun move(position: Coordinate2Payload<Direction>, direction: Direction): Coordinate2Payload<Direction> = when (direction) {
        Direction.UP -> Coordinate2Payload(position.x, position.y - 1, direction)
        Direction.DOWN -> Coordinate2Payload(position.x, position.y + 1, direction)
        Direction.LEFT -> Coordinate2Payload(position.x - 1, position.y, direction)
        Direction.RIGHT -> Coordinate2Payload(position.x + 1, position.y, direction)
    }

    private fun Coordinate2.isValid(): Boolean =
        this.x in 0 until maxX &&
        this.y in 0 until maxY

    private fun <T> Coordinate2Payload<T>.isValid(): Boolean =
        this.x in 0 until maxX &&
            this.y in 0 until maxY

    companion object {
        private const val EMPTY = '.'
        private const val OBSTACLE = '#'
        private const val GUARD = '^'
    }

}