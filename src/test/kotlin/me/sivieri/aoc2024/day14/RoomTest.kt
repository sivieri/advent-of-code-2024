package me.sivieri.aoc2024.day14

import me.sivieri.aoc2024.common.Coordinate2
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class RoomTest {

    @Test
    fun `part 1 example`() {
        val data = """
            p=0,4 v=3,-3
            p=6,3 v=-1,-3
            p=10,3 v=-1,2
            p=2,0 v=2,-1
            p=0,0 v=1,3
            p=3,0 v=-2,-2
            p=7,6 v=-1,-3
            p=3,0 v=-1,-2
            p=9,3 v=2,3
            p=7,3 v=-1,2
            p=2,4 v=2,-3
            p=9,5 v=-3,-3
        """.trimIndent().split("\n")
        val room = Room(data, 11, 7)
        val result = room.findSafetyFactor(100)
        assertEquals(12L, result)
    }

}