package me.sivieri.aoc2024.common

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class LineTest {

    @Test
    fun `horizontal line`() {
        val p1 = Coordinate2(4, 3)
        val p2 = Coordinate2(5, 3)
        val line = Line.findLine(p1, p2)
        assertEquals(HorizontalLine(3), line)
    }

    @Test
    fun `vertical line`() {
        val p1 = Coordinate2(4, 3)
        val p2 = Coordinate2(4, 6)
        val line = Line.findLine(p1, p2)
        assertEquals(VerticalLine(4), line)
    }

    @Test
    fun `diagonal line 1`() {
        val p1 = Coordinate2(1, 2)
        val p2 = Coordinate2(2, 4)
        val line = Line.findLine(p1, p2)
        assertEquals(DiagonalLine(2.0, 0.0), line)
    }

    @Test
    fun `diagonal line 2`() {
        val p1 = Coordinate2(0, -2)
        val p2 = Coordinate2(-3, 2)
        val line = Line.findLine(p1, p2)
        assertEquals(DiagonalLine(-4.0 / 3.0, 2.0), line)
    }

}