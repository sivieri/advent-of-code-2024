package me.sivieri.aoc2024.day22

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class BuyerTest {

    @Test
    fun `123`() {
        val buyer = Buyer()
        val result = buyer.calculateSecretNumber(123)
        assertEquals(15887950, result)
    }

    @Test
    fun `1`() {
        val buyer = Buyer()
        val result = buyer.calculateIterations(1, 2000)
        assertEquals(8685429, result)
    }

    @Test
    fun `10`() {
        val buyer = Buyer()
        val result = buyer.calculateIterations(10, 2000)
        assertEquals(4700978, result)
    }

    @Test
    fun `100`() {
        val buyer = Buyer()
        val result = buyer.calculateIterations(100, 2000)
        assertEquals(15273692, result)
    }

    @Test
    fun `2024`() {
        val buyer = Buyer()
        val result = buyer.calculateIterations(2024, 2000)
        assertEquals(8667524, result)
    }

}