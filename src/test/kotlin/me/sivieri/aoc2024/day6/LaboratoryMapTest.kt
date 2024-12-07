package me.sivieri.aoc2024.day6

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class LaboratoryMapTest {

    @Test
    fun `part 1 example`() {
        val text = """
            ....#.....
            .........#
            ..........
            ..#.......
            .......#..
            ..........
            .#..^.....
            ........#.
            #.........
            ......#...
        """.trimIndent()
        val lab = LaboratoryMap(text)
        val result = lab.countPatrol()
        assertEquals(41, result)
    }

}