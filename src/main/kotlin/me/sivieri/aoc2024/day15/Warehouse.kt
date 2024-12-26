package me.sivieri.aoc2024.day15

import me.sivieri.aoc2024.common.Coordinate2
import me.sivieri.aoc2024.common.Direction
import me.sivieri.aoc2024.common.stringRepresentation

class Warehouse(data: String) {

    private val map: Array<Array<Char>>
    private val maxX: Int
    private val maxY: Int
    private val startingCoordinates: Coordinate2

    private val content: List<Content>
    private val extendedX: Int
    private val extendedY: Int

    private val instructions: List<Direction>

    init {
        // part 1
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

        // part 2
        this.extendedX = this.maxX * 2
        this.extendedY = this.maxY
        content = (0 until maxY).flatMap { y ->
            (0 until maxX).flatMap { x ->
                when (this.map[y][x]) {
                    WALL -> listOf(Wall(Coordinate2(x * 2, y)))
                    EMPTY -> listOf(Empty(Coordinate2(x * 2, y)), Empty(Coordinate2(x * 2 + 1, y)))
                    BOX -> listOf(Box(Coordinate2(x * 2, y)))
                    ROBOT -> listOf(Robot(Coordinate2(x * 2, y)), Empty(Coordinate2(x * 2 + 1, y)))
                    else -> throw IllegalArgumentException("Unknown symbol ${this.map[y][x]}")
                }
            }
        }

        // common
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

    fun sumGPScoordsExtended(): Int {
        playExtended()
        return content
            .filterIsInstance<Box>()
            .sumOf {
                val c = it.c.minByOrNull { it.x }!!
                100 * c.y + c.x
            }
    }

    private fun playExtended(): Coordinate2 = instructions.fold(startingCoordinates) { acc, next ->
        val queue = mutableListOf<Coordinate2>()
        var c = acc // current robot position
        do {
            queue.add(c)
            c = next.updateCoordinate(c)
        } while (map[c.y][c.x] != WALL && map[c.y][c.x] != EMPTY)
        queue.add(c)
        if (map[c.y][c.x] == WALL) acc
        else { // EMPTY, move everything
            TODO()
        }
    }

    fun extendedString(): String {
        val v = Array(extendedY) { Array(extendedX) { EMPTY } }
        content.forEach {
            when (it) {
                is Box -> {
                    v[it.c[0].y][it.c[0].x] = LARGE_BOX[0]
                    v[it.c[1].y][it.c[1].x] = LARGE_BOX[1]
                }
                is Empty -> v[it.c.first().y][it.c.first().x] = EMPTY
                is Robot -> v[it.c.first().y][it.c.first().x] = ROBOT
                is Wall -> it.c.forEach { v[it.y][it.x] = WALL }
            }
        }
        return v.stringRepresentation("")
    }

    companion object {
        private const val WALL = '#'
        private const val BOX = 'O'
        private const val ROBOT = '@'
        private const val EMPTY = '.'
        private val LARGE_BOX = listOf('[', ']')
    }

}