package me.sivieri.aoc2024.day20

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class RacetrackTest {

    @Test
    fun `part 1 example 1`() {
        val data = """
            ###############
            #...#...#.....#
            #.#.#.#.#.###.#
            #S#...#.#.#...#
            #######.#.#.###
            #######.#.#...#
            #######.#.###.#
            ###..E#...#...#
            ###.#######.###
            #...###...#...#
            #.#####.#.###.#
            #.#...#.#.#...#
            #.#.#.#.#.#.###
            #...#...#...###
            ###############
        """.trimIndent()
        val racetrack = Racetrack(data)
        val result = racetrack.countCheats(1)
        assertEquals(44, result)
    }

    @Test
    fun `part 1 example 2`() {
        val data = """
            ###############
            #...#...#.....#
            #.#.#.#.#.###.#
            #S#...#.#.#...#
            #######.#.#.###
            #######.#.#...#
            #######.#.###.#
            ###..E#...#...#
            ###.#######.###
            #...###...#...#
            #.#####.#.###.#
            #.#...#.#.#...#
            #.#.#.#.#.#.###
            #...#...#...###
            ###############
        """.trimIndent()
        val racetrack = Racetrack(data)
        val result = racetrack.countCheats(20)
        assertEquals(5, result)
    }

    @Test
    fun `part 2 example`() {
        val data = """
            ###############
            #...#...#.....#
            #.#.#.#.#.###.#
            #S#...#.#.#...#
            #######.#.#.###
            #######.#.#...#
            #######.#.###.#
            ###..E#...#...#
            ###.#######.###
            #...###...#...#
            #.#####.#.###.#
            #.#...#.#.#...#
            #.#.#.#.#.#.###
            #...#...#...###
            ###############
        """.trimIndent()
        val racetrack = Racetrack(data)
        val result = racetrack.countCheats(20, 50)
        assertEquals(285, result)
    }

}