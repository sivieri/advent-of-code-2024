package me.sivieri.aoc2024.day17

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class ComputerTest {

    @Test
    fun `part 1 example`() {
        val data = """
            Register A: 729
            Register B: 0
            Register C: 0

            Program: 0,1,5,4,3,0
        """.trimIndent()
        val computer = Computer(data)
        val result = computer.calculateOutput()
        assertEquals("4,6,3,5,6,3,5,2,1,0", result)
    }

}