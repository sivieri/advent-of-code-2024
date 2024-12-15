package me.sivieri.aoc2024.day11

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class PlutoPebblesTest {

    @Test
    fun `part 1 example 1`() {
        val data = "0 1 10 99 999"
        val pebbles = PlutoPebbles(data)
        pebbles.play(1)
        val score = pebbles.score()
        assertEquals(7, score)
    }

    @Test
    fun `part 1 example 2`() {
        val data = "125 17"
        val pebbles = PlutoPebbles(data)
        pebbles.play(6)
        val score = pebbles.score()
        assertEquals(22, score)
    }

    @Test
    fun `part 1 example 3`() {
        val data = "125 17"
        val pebbles = PlutoPebbles(data)
        pebbles.play(25)
        val score = pebbles.score()
        assertEquals(55312, score)
    }

}