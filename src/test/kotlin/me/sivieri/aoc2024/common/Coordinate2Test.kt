package me.sivieri.aoc2024.common

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class Coordinate2Test {

    @Test
    fun `opposite test 1`() {
        val p1 = Coordinate2(4, 3)
        val p2 = Coordinate2(5, 5)
        val op = p1.findOpposite(p2)
        assertEquals(Coordinate2(6, 7), op)
    }

    @Test
    fun `opposite test 2`() {
        val p1 = Coordinate2(4, 3)
        val p2 = Coordinate2(5, 5)
        val op = p2.findOpposite(p1)
        assertEquals(Coordinate2(3, 1), op)
    }

    @Test
    fun `opposite test 3`() {
        val p1 = Coordinate2(3, 1)
        val p2 = Coordinate2(3, 2)
        val op = p1.findOpposite(p2)
        assertEquals(Coordinate2(3, 3), op)
    }

    @Test
    fun `opposite test 4`() {
        val p1 = Coordinate2(3, 1)
        val p2 = Coordinate2(3, 3)
        val op = p1.findOpposite(p2)
        assertEquals(Coordinate2(3, 5), op)
    }

    @Test
    fun `opposite test 5`() {
        val p1 = Coordinate2(1, 1)
        val p2 = Coordinate2(2, 1)
        val op = p1.findOpposite(p2)
        assertEquals(Coordinate2(3, 1), op)
    }

    @Test
    fun `opposite test 6`() {
        val p1 = Coordinate2(1, 1)
        val p2 = Coordinate2(3, 1)
        val op = p1.findOpposite(p2)
        assertEquals(Coordinate2(5, 1), op)
    }

}