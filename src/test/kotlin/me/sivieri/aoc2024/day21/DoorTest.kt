package me.sivieri.aoc2024.day21

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class DoorTest {

    @Test
    fun `029A`() {
        val door = Door()
        val result = door.calculateComplexity("029A")
        assertEquals(68 * 29, result)
    }

    @Test
    fun `980A`() {
        val door = Door()
        val result = door.calculateComplexity("980A")
        assertEquals(60 * 980, result)
    }

    @Test
    fun `179A`() {
        val door = Door()
        val result = door.calculateComplexity("179A")
        assertEquals(68 * 179, result)
    }

    @Test
    fun `456A`() {
        val door = Door()
        val result = door.calculateComplexity("456A")
        assertEquals(64 * 456, result)
    }

    @Test
    fun `379A`() {
        val door = Door()
        val result = door.calculateComplexity("379A")
        assertEquals(64 * 379, result)
    }

    @Test
    fun `part 1 test`() {
        val sequences = mapOf(
            "029A" to "<vA<AA>>^AvAA<^A>A<v<A>>^AvA^A<vA>^A<v<A>^A>AAvA^A<v<A>A>^AAAvA<^A>A".length,
            "980A" to "<v<A>>^AAAvA^A<vA<AA>>^AvAA<^A>A<v<A>A>^AAAvA<^A>A<vA>^A<A>A".length,
            "179A" to "<v<A>>^A<vA<A>>^AAvAA<^A>A<v<A>>^AAvA^A<vA>^AA<A>A<v<A>A>^AAAvA<^A>A".length,
            "456A" to "<v<A>>^AA<vA<A>>^AAvAA<^A>A<vA>^A<A>A<vA>^A<A>A<v<A>A>^AAvA<^A>A".length,
            "379A" to "<v<A>>^AvA^A<vA<AA>>^AAvA<^A>AAvA^A<vA>^AA<A>A<v<A>A>^AAAvA<^A>A".length
        )
        val door = Door()
        val result = sequences
            .entries
            .sumOf {
                door.calculateComplexity(it.key)
            }
        assertEquals(126384, result)
    }

}