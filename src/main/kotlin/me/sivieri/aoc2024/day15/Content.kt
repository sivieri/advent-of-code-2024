package me.sivieri.aoc2024.day15

import me.sivieri.aoc2024.common.Coordinate2
import me.sivieri.aoc2024.common.Direction

sealed interface Content {
    fun findNeighbors(content: Set<Content>, next: Direction): List<Content>
    fun contains(other: Coordinate2): Boolean
    fun move(next: Direction)
}

data class Robot(var c: Coordinate2): Content {
    override fun findNeighbors(content: Set<Content>, next: Direction): List<Content> =
        content.filter { it.contains(next.updateCoordinate(c)) }

    override fun contains(other: Coordinate2): Boolean = c == other

    override fun move(next: Direction) {
        c = next.updateCoordinate(c)
    }
}

data class Wall(
    var left: Coordinate2,
    var right: Coordinate2
): Content {
    override fun findNeighbors(content: Set<Content>, next: Direction): List<Content> =
        content.filter {
            (next == Direction.UP || next == Direction.DOWN) && (it.contains(next.updateCoordinate(left)) || it.contains(next.updateCoordinate(right)))
                || (next == Direction.LEFT && it.contains(next.updateCoordinate(left)))
                || (next == Direction.RIGHT && it.contains(next.updateCoordinate(right)))
        }

    override fun contains(other: Coordinate2): Boolean = left == other || right == other

    override fun move(next: Direction) {
        left = next.updateCoordinate(left)
        right = next.updateCoordinate(right)
    }
}

data class Box(
    var left: Coordinate2,
    var right: Coordinate2
): Content {
    override fun findNeighbors(content: Set<Content>, next: Direction): List<Content> =
        content.filter {
            (next == Direction.UP || next == Direction.DOWN) && (it.contains(next.updateCoordinate(left)) || it.contains(next.updateCoordinate(right)))
                || (next == Direction.LEFT && it.contains(next.updateCoordinate(left)))
                || (next == Direction.RIGHT && it.contains(next.updateCoordinate(right)))
        }

    override fun contains(other: Coordinate2): Boolean = left == other || right == other

    override fun move(next: Direction) {
        left = next.updateCoordinate(left)
        right = next.updateCoordinate(right)
    }
}

data class Empty(var c: Coordinate2): Content {
    override fun findNeighbors(content: Set<Content>, next: Direction): List<Content> =
        content.filter { it.contains(next.updateCoordinate(c)) }

    override fun contains(other: Coordinate2): Boolean = c == other

    override fun move(next: Direction) {
        c = next.updateCoordinate(c)
    }
}
