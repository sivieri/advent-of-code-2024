package me.sivieri.aoc2024.day10

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class TopographicMapTest {

    @Test
    fun `part 1 example 1`() {
        val data = """
            ...0...
            ...1...
            ...2...
            6543456
            7.....7
            8.....8
            9.....9
        """.trimIndent()
        val map = TopographicMap(data)
        val score = map.sumTrailheadScores()
        assertEquals(2, score)
    }

    @Test
    fun `part 1 example 2`() {
        val data = """
            ..90..9
            ...1.98
            ...2..7
            6543456
            765.987
            876....
            987....
        """.trimIndent()
        val map = TopographicMap(data)
        val score = map.sumTrailheadScores()
        assertEquals(4, score)
    }

    @Test
    fun `part 1 example 3`() {
        val data = """
            10..9..
            2...8..
            3...7..
            4567654
            ...8..3
            ...9..2
            .....01
        """.trimIndent()
        val map = TopographicMap(data)
        val score = map.sumTrailheadScores()
        assertEquals(3, score)
    }

    @Test
    fun `part 1 example 4`() {
        val data = """
            89010123
            78121874
            87430965
            96549874
            45678903
            32019012
            01329801
            10456732
        """.trimIndent()
        val map = TopographicMap(data)
        val score = map.sumTrailheadScores()
        assertEquals(36, score)
    }

}