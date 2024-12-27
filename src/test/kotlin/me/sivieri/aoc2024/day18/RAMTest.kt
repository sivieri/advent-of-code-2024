package me.sivieri.aoc2024.day18

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class RAMTest {

    @Test
    fun `part 1 example`() {
        val data = """
            5,4
            4,2
            4,5
            3,0
            2,1
            6,3
            2,4
            1,5
            0,6
            3,3
            2,6
            5,1
            1,2
            5,5
            2,5
            6,5
            1,4
            0,4
            6,4
            1,1
            6,1
            1,0
            0,5
            1,6
            2,0
        """.trimIndent().split("\n")
        val ram = RAM(data, 7, 7)
        val result = ram.findShortestLength(12)
        assertEquals(22, result)
    }

}