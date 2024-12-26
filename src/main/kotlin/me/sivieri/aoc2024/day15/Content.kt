package me.sivieri.aoc2024.day15

import me.sivieri.aoc2024.common.Coordinate2
import me.sivieri.aoc2024.common.Direction

sealed interface Content {
    fun findNeighbors(content: Set<Content>, next: Direction): List<Content>
    fun contains(other: Coordinate2): Boolean
    fun move(next: Direction): Content?
    fun allCoords(): List<Coordinate2>
}

data class Robot(val c: Coordinate2): Content {
    override fun findNeighbors(content: Set<Content>, next: Direction): List<Content> =
        content.filter { it.contains(next.updateCoordinate(c)) }

    override fun contains(other: Coordinate2): Boolean = c == other

    override fun move(next: Direction): Content = Robot(next.updateCoordinate(c))

    override fun allCoords(): List<Coordinate2> = listOf(c)
}

data class Wall(
    val left: Coordinate2,
    val right: Coordinate2
): Content {
    override fun findNeighbors(content: Set<Content>, next: Direction): List<Content> =
        content.filter {
            (next == Direction.UP || next == Direction.DOWN) && (it.contains(next.updateCoordinate(left)) || it.contains(next.updateCoordinate(right)))
                || (next == Direction.LEFT && it.contains(next.updateCoordinate(left)))
                || (next == Direction.RIGHT && it.contains(next.updateCoordinate(right)))
        }

    override fun contains(other: Coordinate2): Boolean = left == other || right == other

    override fun move(next: Direction): Content = Wall(
        next.updateCoordinate(left),
        next.updateCoordinate(right)
    )

    override fun allCoords(): List<Coordinate2> = listOf(left, right)
}

data class Box(
    val left: Coordinate2,
    val right: Coordinate2
): Content {
    override fun findNeighbors(content: Set<Content>, next: Direction): List<Content> =
        content.filter {
            (next == Direction.UP || next == Direction.DOWN) && (it.contains(next.updateCoordinate(left)) || it.contains(next.updateCoordinate(right)))
                || (next == Direction.LEFT && it.contains(next.updateCoordinate(left)))
                || (next == Direction.RIGHT && it.contains(next.updateCoordinate(right)))
        }

    override fun contains(other: Coordinate2): Boolean = left == other || right == other

    override fun move(next: Direction): Content = Box(
        next.updateCoordinate(left),
        next.updateCoordinate(right)
    )

    override fun allCoords(): List<Coordinate2> = listOf(left, right)
}

data class Empty(val c: Coordinate2): Content {
    override fun findNeighbors(content: Set<Content>, next: Direction): List<Content> =
        content.filter { it.contains(next.updateCoordinate(c)) }

    override fun contains(other: Coordinate2): Boolean = c == other

    override fun move(next: Direction): Content? = null

    override fun allCoords(): List<Coordinate2> = listOf(c)

}
