package me.sivieri.aoc2024.day15

import me.sivieri.aoc2024.common.Coordinate2
import me.sivieri.aoc2024.common.Direction

class Warehouse(data: String) {

    private val map: Array<Array<Char>>
    private val maxX: Int
    private val maxY: Int
    private val startingCoordinates: Coordinate2
    private val instructions: List<Direction>

    init {
        val (map, instructions) = data.split("\n\n")
        this.map = map
            .split("\n")
            .map { it.toCharArray().toTypedArray() }
            .toTypedArray()
        this.maxX = this.map[0].size
        this.maxY = this.map.size
        var sc = Coordinate2(-1, -1)
        (0 until maxY).forEach { y ->
            (0 until maxX).forEach { x ->
                if (this.map[y][x] == ROBOT) sc = Coordinate2(x, y)
            }
        }
        this.startingCoordinates = sc
        this.instructions = instructions
            .replace("\n", "")
            .map { Direction.parse(it) }
    }

    fun sumGPScoords(): Int {
        play()
        return (0 until maxY).flatMap { y ->
            (0 until maxX).map { x ->
                if (map[y][x] == BOX) 100 * y + x
                else 0
            }
        }.sum()
    }

    private fun play(): Coordinate2 = instructions.fold(startingCoordinates) { acc, next ->
        val queue = mutableListOf<Coordinate2>()
        var c = acc // current robot position
        do {
            queue.add(c)
            c = next.updateCoordinate(c)
        } while (map[c.y][c.x] != WALL && map[c.y][c.x] != EMPTY)
        queue.add(c)
        if (map[c.y][c.x] == WALL) acc
        else { // EMPTY, move everything
            queue
                .reversed()
                .windowed(2)
                .forEach { (cur, prev) ->
                    map[cur.y][cur.x] = map[prev.y][prev.x]
                    map[prev.y][prev.x] = EMPTY
                }
            queue[1]
        }
    }

    companion object {
        private const val WALL = '#'
        private const val BOX = 'O'
        private const val ROBOT = '@'
        private const val EMPTY = '.'
    }

}