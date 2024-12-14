package me.sivieri.aoc2024.day8

import me.sivieri.aoc2024.common.Coordinate2
import me.sivieri.aoc2024.external.permutations

class AntennasMap(data: String) {

    private val map = data
        .split("\n")
        .filter { it.isNotBlank() }
        .map { it.toCharArray().toTypedArray() }
        .toTypedArray()
    private val maxX = map[0].size
    private val maxY = map.size

    fun countAntinodes(): Int = listOf<List<Char>>(
        ('a' .. 'z').toList(),
        ('A' .. 'Z').toList(),
        ('0' .. '9').toList()
    )
        .flatten()
        .flatMap { findAntinodesForLetter(it) }
        .distinct()
        .size

    private fun findAntinodesForLetter(c: Char): List<Coordinate2> {
        val coordinates = findCoordinatesForLetter(c)
        return if (coordinates.size < 2) emptyList()
        else coordinates
            .permutations(2)
            .flatMap { l ->
                val first = l[0]
                val second = l[1]
                val p1 = first.findOpposite(second)
                val p2 = second.findOpposite(first)
                listOf(p1, p2)
            }
            .filter { isValid(it) }
            .toList()
    }

    private fun findCoordinatesForLetter(c: Char): List<Coordinate2> =
        (0 until maxY).flatMap { y ->
            (0 until maxX).map { x ->
                if (map[y][x] == c) Coordinate2(x, y) else null
            }
        }.filterNotNull()

    private fun isValid(c: Coordinate2): Boolean =
        c.x in 0 until maxX &&
        c.y in 0 until maxY

}