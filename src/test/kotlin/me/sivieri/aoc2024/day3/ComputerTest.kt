package me.sivieri.aoc2024.day3

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class ComputerTest {

    @Test
    fun `part 1 example`() {
        val text = "xmul(2,4)%&mul[3,7]!@^do_not_mul(5,5)+mul(32,64]then(mul(11,8)mul(8,5))"
        val instructions = Computer.findUncorruptedMultiply(text)
        val result = instructions.sumOf { it.result() }
        assertEquals(161, result)
    }

}