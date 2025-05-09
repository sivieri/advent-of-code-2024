package me.sivieri.aoc2024.day14

import me.sivieri.aoc2024.common.Coordinate2
import me.sivieri.aoc2024.common.multiplyBy
import me.sivieri.aoc2024.common.splitInTwo
import me.sivieri.aoc2024.common.stringRepresentation

class Room(
    lines: List<String>,
    private val maxX: Int,
    private val maxY: Int
) {

    private val robots = lines.map { Robot.parse(it) }

    fun findSafetyFactor(turns: Int): Long {
        repeat(turns) { robots.forEach { it.turn(maxX, maxY) } }
        println(boardToString())
        val quadrants = calculateQuadrants()
        return quadrants.multiplyBy { it.toLong() }
    }

    fun findTurnsToTree(): Int {
        var rounds = 0
        while (!findTree()) {
            rounds++
            if (rounds % 1000 == 0) println("Turn $rounds")
            robots.forEach { it.turn(maxX, maxY) }
        }
        return rounds
    }

    private fun findTree(): Boolean {
        val board = boardToString()
        if (board.contains(TREE_THRESHOLD)) {
            println(board)
            print("Continue? Y/N ")
            return when (readln()[0]) {
                'Y' -> false
                'N' -> true
                else -> throw IllegalArgumentException("Unknown input")
            }
        }
        else return false
    }

    private fun calculateQuadrants(): List<Int> {
        val (x1, x2) = (0 until maxX - 1)
            .toList()
            .splitInTwo()
            .let { (a, b) ->
                Pair(
                    a,
                    b.map { it + 1 }
                )
            }
        val (y1, y2) = (0 until maxY - 1)
            .toList()
            .splitInTwo()
            .let { (a, b) ->
                Pair(
                    a,
                    b.map { it + 1 }
                )
            }
        return listOf(
            Pair(x1, y1),
            Pair(x1, y2),
            Pair(x2, y1),
            Pair(x2, y2)
        ).map { (exes, eyes) ->
            exes.sumOf { x ->
                eyes.sumOf { y ->
                    val c = Coordinate2(x, y)
                    robots.count { it.position == c }
                }
            }
        }
    }

    private fun boardToString(): String =
        (0 until maxY)
            .map { y -> (0 until maxX).map { x -> Pair(x, y) } }
            .stringRepresentation("") { (x, y) ->
                val c = Coordinate2(x, y)
                robots
                    .count { it.position == c }
                    .let { if (it == 0) "." else it.toString() }
            }

    companion object {
        private const val TREE_THRESHOLD = "1111111111"
    }

}