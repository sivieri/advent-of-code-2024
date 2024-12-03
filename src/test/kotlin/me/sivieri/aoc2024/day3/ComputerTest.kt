package me.sivieri.aoc2024.day3

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class ComputerTest {

    @Test
    fun `part 1 example`() {
        val text = "xmul(2,4)%&mul[3,7]!@^do_not_mul(5,5)+mul(32,64]then(mul(11,8)mul(8,5))"
        val result = Computer.multiplyResult(text)
        assertEquals(161, result)
    }

    @Test
    fun `part 2 example`() {
        val text = "xmul(2,4)&mul[3,7]!^don't()_mul(5,5)+mul(32,64](mul(11,8)undo()?mul(8,5))"
        val result = Computer.multiplyEnabledResult(text)
        assertEquals(48, result)
    }

}