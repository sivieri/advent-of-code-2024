package me.sivieri.aoc2024.day11

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.fail
import org.junit.jupiter.api.Test

class PlutoPebblesV2Test {

    @Test
    fun `part 1 example 1`() {
        val data = "0 1 10 99 999"
        val pebbles = PlutoPebblesV2(data)
        pebbles.play(1)
        val score = pebbles.score()
        assertEquals(7, score)
    }

    @Test
    fun `part 1 example 2`() {
        val data = "125 17"
        val pebbles = PlutoPebblesV2(data)
        pebbles.play(6)
        val score = pebbles.score()
        assertEquals(22, score)
    }

    @Test
    fun `part 1 example 3`() {
        val data = "125 17"
        val pebbles = PlutoPebblesV2(data)
        pebbles.play(25)
        val score = pebbles.score()
        assertEquals(55312, score)
    }

    @Test
    fun `strategies comparison`() {
        val data = "125 17"
        val pebbles1 = PlutoPebbles(data)
        val pebbles2 = PlutoPebblesV2(data)
        (1 .. 25).forEach { round ->
            println("Round $round")
            pebbles1.play()
            pebbles2.play()
            val expected = pebbles1.stones.groupingBy { it }.eachCount().mapValues { it.value.toLong() }
            if (!(expected[0] == null && pebbles2.zeroes == 0L || expected[0] == pebbles2.zeroes)) fail<Unit>("Zeroes failed")
            if (pebbles2.even.any { expected[it.key] != it.value }) fail<Unit>("Even failed")
            if (pebbles2.others.any { expected[it.key] != it.value }) fail<Unit>("Even failed")
        }
    }

}