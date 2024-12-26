package me.sivieri.aoc2024.day15

import me.sivieri.aoc2024.common.Coordinate2
import me.sivieri.aoc2024.common.Direction
import me.sivieri.aoc2024.common.stringRepresentation
import java.util.ArrayDeque

class Warehouse(data: String) {

    private val map: Array<Array<Char>>
    private val maxX: Int
    private val maxY: Int
    private val startingCoordinates: Coordinate2

    private var content: Set<Content>
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
                    WALL -> listOf(Wall(Coordinate2(x * 2, y), Coordinate2(x * 2 + 1, y)))
                    EMPTY -> listOf(Empty(Coordinate2(x * 2, y)), Empty(Coordinate2(x * 2 + 1, y)))
                    BOX -> listOf(Box(Coordinate2(x * 2, y), Coordinate2(x * 2 + 1, y)))
                    ROBOT -> listOf(Robot(Coordinate2(x * 2, y)), Empty(Coordinate2(x * 2 + 1, y)))
                    else -> throw IllegalArgumentException("Unknown symbol ${this.map[y][x]}")
                }
            }
        }.toSet()

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
                100 * it.left.y + it.left.x
            }
    }

    private fun playExtended() = instructions.forEachIndexed { index, next ->
        println("Round ${index + 1} / ${instructions.size}, direction $next")
        // println(extendedString())
        val robot = content.find { it is Robot }!!
        val visited = mutableSetOf<Content>()
        val queue = ArrayDeque<Content>()
        queue.push(robot)
        var wallFound = false
        while (!queue.isEmpty() && ! wallFound) {
            when (val c = queue.pop()) {
                is Empty -> visited.add(c)
                is Wall -> wallFound = true
                else -> {
                    visited.add(c)
                    c
                        .findNeighbors(content, next)
                        .forEach { if (!visited.contains(it)) queue.push(it) }
                }
            }
        }
        if (!wallFound) {
            val n = mutableSetOf<Content>()
            val old = content - visited
            val updated = visited.mapNotNull { it.move(next) }
            val updatedCoords = updated.flatMap { it.allCoords() }.toSet()
            val oldUntouched = old.filter { c ->
                updatedCoords.none { c.contains(it) }
            }
            val allCoords = updatedCoords + oldUntouched.flatMap { it.allCoords() }
            val missing = (0 until extendedY).flatMap { y ->
                (0 until extendedX)
                    .filter { x ->
                        !allCoords.contains(Coordinate2(x, y))
                    }
                    .map { x -> Coordinate2(x, y) }
            }.map { Empty(it) }
            n.addAll(updated)
            n.addAll(oldUntouched)
            n.addAll(missing)
            content = n
        }
    }

    fun extendedString(): String {
        val v = Array(extendedY) { Array(extendedX) { NULL } }
        content.forEach {
            when (it) {
                is Box -> {
                    v[it.left.y][it.left.x] = LARGE_BOX[0]
                    v[it.right.y][it.right.x] = LARGE_BOX[1]
                }
                is Empty -> v[it.c.y][it.c.x] = EMPTY
                is Robot -> v[it.c.y][it.c.x] = ROBOT
                is Wall -> {
                    v[it.left.y][it.left.x] = WALL
                    v[it.right.y][it.right.x] = WALL
                }
            }
        }
        return v
            .stringRepresentation("")
            .also {
                if (it.contains("!")) throw IllegalStateException("Not all cells are filled\n$it")
            }
    }

    companion object {
        private const val WALL = '#'
        private const val BOX = 'O'
        private const val ROBOT = '@'
        private const val EMPTY = '.'
        private const val NULL = '!'
        private val LARGE_BOX = listOf('[', ']')
    }

}