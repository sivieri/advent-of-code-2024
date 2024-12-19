package me.sivieri.aoc2024.day12

import me.sivieri.aoc2024.common.Coordinate2
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class PlotTest {

    @Test
    fun `plot A`() {
        val coords = listOf(
            Coordinate2(0, 0),
            Coordinate2(1, 0),
            Coordinate2(2, 0),
            Coordinate2(3, 0),
        )
        val plot = Plot("A", coords)
        assertEquals(10, plot.perimeter())
        assertEquals(4, plot.area())
    }


    @Test
    fun `plot B`() {
        val coords = listOf(
            Coordinate2(0, 1),
            Coordinate2(0, 2),
            Coordinate2(1, 1),
            Coordinate2(1, 2),
        )
        val plot = Plot("B", coords)
        assertEquals(8, plot.perimeter())
        assertEquals(4, plot.area())
    }

    @Test
    fun `plot C`() {
        val coords = listOf(
            Coordinate2(2, 1),
            Coordinate2(2, 2),
            Coordinate2(3, 2),
            Coordinate2(3, 3),
        )
        val plot = Plot("C", coords)
        assertEquals(10, plot.perimeter())
        assertEquals(4, plot.area())
    }

    @Test
    fun `plot D`() {
        val coords = listOf(
            Coordinate2(3, 1),
        )
        val plot = Plot("D", coords)
        assertEquals(4, plot.perimeter())
        assertEquals(1, plot.area())
    }


    @Test
    fun `plot E`() {
        val coords = listOf(
            Coordinate2(0, 3),
            Coordinate2(1, 3),
            Coordinate2(2, 3),
        )
        val plot = Plot("E", coords)
        assertEquals(8, plot.perimeter())
        assertEquals(3, plot.area())
    }

}