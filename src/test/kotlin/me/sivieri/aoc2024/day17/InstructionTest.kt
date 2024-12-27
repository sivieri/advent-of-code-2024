package me.sivieri.aoc2024.day17

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class InstructionTest {

    @Test
    fun `instructions 1`() {
        val registers = Registers(0, 0, 9)
        bst().calc(registers.c, registers)
        assertEquals(1, registers.b)
    }

    @Test
    fun `instructions 2`() {
        val registers = Registers(10, 0, 0)
        val (_, one) = out().calc(0, registers)
        val (_, two) = out().calc(1, registers)
        val (_, three) = out().calc(2, registers)
        val result = listOf(one!!, two!!, three!!).joinToString(",")
        assertEquals("0,1,2", result)
    }

    @Test
    fun `instructions 3`() {
        val registers = Registers(2024, 0, 0)
        adv().calc(1, registers)
        val (_, one) = out().calc(registers.a, registers)
        val (two, _) = jnz().calc(0, registers)
        assertEquals(1012, registers.a)
        assertEquals(4, one)
        assertEquals(0, two)
    }

    @Test
    fun `instructions 4`() {
        val registers = Registers(0, 29, 0)
        bxl().calc(7, registers)
        assertEquals(26, registers.b)
    }

    @Test
    fun `instructions 5`() {
        val registers = Registers(0, 2024, 43690)
        bxc().calc(0, registers)
        assertEquals(44354, registers.b)
    }

}