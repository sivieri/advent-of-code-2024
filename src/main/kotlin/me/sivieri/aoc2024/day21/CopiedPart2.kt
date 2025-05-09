package me.sivieri.aoc2024.day21

import me.sivieri.aoc2024.common.Coordinate2

class CopiedPart2 {

    private val numeric = mapOf(
        Coordinate2(0, 0) to '7', Coordinate2(1, 0) to '8', Coordinate2(2, 0) to '9',
        Coordinate2(0, 1) to '4', Coordinate2(1, 1) to '5', Coordinate2(2, 1) to '6',
        Coordinate2(0, 2) to '1', Coordinate2(1, 2) to '2', Coordinate2(2, 2) to '3',
        Coordinate2(1, 3) to '0', Coordinate2(2, 3) to 'A'
    )

    private val directional = mapOf(
        Coordinate2(1, 0) to '^', Coordinate2(2, 0) to 'A',
        Coordinate2(0, 1) to '<', Coordinate2(1, 1) to 'v', Coordinate2(2, 1) to '>'
    )

    private val numericPaths = buildShortestPaths(keypad = numeric)

    private val directionalPaths = buildShortestPaths(keypad = directional)

    fun solve(codes: List<String>, levels: Int) =
        codes.sumOf { code -> code.findCost(levels) * code.filter { it.isDigit() }.toInt() }

    private fun String.findCost(
        levels: Int,
        keypad: Map<Char, Map<Char, List<String>>> = numericPaths,
        cache: MutableMap<Pair<String, Int>, Long> = mutableMapOf()
    ): Long =
        cache.getOrPut(this to levels) {
            when (levels) {
                0 -> length.toLong()
                else -> {
                    "A$this".zipWithNext().sumOf { (from, to) ->
                        keypad.getValue(from).getValue(to).minOf { path ->
                            "${path}A".findCost(levels - 1, directionalPaths, cache)
                        }
                    }
                }
            }
        }

    private fun buildShortestPaths(keypad: Map<Coordinate2, Char>): Map<Char, Map<Char, List<String>>> =
        buildMap {
            for (start in keypad.keys) {
                val paths = mutableMapOf<Char, MutableList<String>>()
                paths[keypad.getValue(start)] = mutableListOf("")

                val queue = mutableListOf(start to "")
                val visited = mutableSetOf<Coordinate2>()

                while (queue.isNotEmpty()) {
                    val (current, path) = queue.removeFirst()
                    visited += current

                    current.neighbors().forEach {
                        if (it in keypad && it !in visited) {
                            val newPath = path + (it - current).toChar()
                            queue += it to newPath
                            paths.getOrPut(keypad.getValue(it)) { mutableListOf() } += newPath
                        }
                    }
                }

                set(keypad[start]!!, paths)
            }
        }

    private fun Coordinate2.toChar() =
        when (this) {
            UP -> "^"
            RIGHT -> ">"
            DOWN -> "v"
            LEFT -> "<"
            else -> error("Invalid direction: $this")
        }

    companion object {
        private val UP = Coordinate2(0, -1)
        private val RIGHT = Coordinate2(1, 0)
        private val DOWN = Coordinate2(0, 1)
        private val LEFT = Coordinate2(-1, 0)
    }
    
}