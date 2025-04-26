package me.sivieri.aoc2024.day22

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class BuyersTest {

    @Test
    fun `part 2 combinations`() {
        val numbers = listOf(1, 2, 3, 2024)
        val buyers = Buyers(numbers)
        val combinations = buyers.calculateAllQuartets()
        println(combinations.size)
    }

    @Test
    fun `part 2 quartet`() {
        val numbers = listOf(1, 2, 3, 2024)
        val buyers = Buyers(numbers)
        val result = buyers.calculatePricesForQuartet(listOf(-2,1,-1,3))
        assertEquals(23, result)
    }

}