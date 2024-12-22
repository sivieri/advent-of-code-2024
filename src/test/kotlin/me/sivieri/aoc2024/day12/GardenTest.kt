package me.sivieri.aoc2024.day12

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class GardenTest {

    @Test
    fun `part 1 example 1`() {
        val data = """
            AAAA
            BBCD
            BBCC
            EEEC
        """.trimIndent()
        val garden = Garden(data)
        val result = garden.calculateFencingPrice()
        assertEquals(140, result)
    }

    @Test
    fun `part 1 example 2`() {
        val data = """
            OOOOO
            OXOXO
            OOOOO
            OXOXO
            OOOOO
        """.trimIndent()
        val garden = Garden(data)
        val result = garden.calculateFencingPrice()
        assertEquals(772, result)
    }

    @Test
    fun `part 1 example 3`() {
        val data = """
            RRRRIICCFF
            RRRRIICCCF
            VVRRRCCFFF
            VVRCCCJFFF
            VVVVCJJCFE
            VVIVCCJJEE
            VVIIICJJEE
            MIIIIIJJEE
            MIIISIJEEE
            MMMISSJEEE
        """.trimIndent()
        val garden = Garden(data)
        val result = garden.calculateFencingPrice()
        assertEquals(1930, result)
    }

    @Test
    fun `part 2 example 1`() {
        val data = """
            AAAA
            BBCD
            BBCC
            EEEC
        """.trimIndent()
        val garden = Garden(data)
        val result = garden.calculateFencingSidesPrice()
        assertEquals(80, result)
    }

    @Test
    fun `part 2 example 2`() {
        val data = """
            OOOOO
            OXOXO
            OOOOO
            OXOXO
            OOOOO
        """.trimIndent()
        val garden = Garden(data)
        val result = garden.calculateFencingSidesPrice()
        assertEquals(436, result)
    }

    @Test
    fun `part 2 example 3`() {
        val data = """
            EEEEE
            EXXXX
            EEEEE
            EXXXX
            EEEEE
        """.trimIndent()
        val garden = Garden(data)
        val result = garden.calculateFencingSidesPrice()
        assertEquals(236, result)
    }

    @Test
    fun `part 2 example 4`() {
        val data = """
            AAAAAA
            AAABBA
            AAABBA
            ABBAAA
            ABBAAA
            AAAAAA
        """.trimIndent()
        val garden = Garden(data)
        val result = garden.calculateFencingSidesPrice()
        assertEquals(368, result)
    }

    @Test
    fun `part 2 example 5`() {
        val data = """
            RRRRIICCFF
            RRRRIICCCF
            VVRRRCCFFF
            VVRCCCJFFF
            VVVVCJJCFE
            VVIVCCJJEE
            VVIIICJJEE
            MIIIIIJJEE
            MIIISIJEEE
            MMMISSJEEE
        """.trimIndent()
        val garden = Garden(data)
        val result = garden.calculateFencingSidesPrice()
        assertEquals(1206, result)
    }

}