package me.sivieri.aoc2024.day1

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.assertEquals

class LocationListTest {

    @Test
    fun `part 1 example`() {
        val data = """
            3   4
            4   3
            2   5
            1   3
            3   9
            3   3
        """.trimIndent()
            .split("\n")
            .map {
                val d = it.split("   ")
                Pair(d[0].toLong(), d[1].toLong())
            }
        val result = LocationList.calculateTotalDistance(data)
        assertEquals(11, result)
    }

}