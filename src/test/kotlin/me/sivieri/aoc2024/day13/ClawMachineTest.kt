package me.sivieri.aoc2024.day13

import me.sivieri.aoc2024.common.Coordinate2
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test

class ClawMachineTest {

    @Test
    fun `parse test`() {
        val text = """
            Button A: X+94, Y+34
            Button B: X+22, Y+67
            Prize: X=8400, Y=5400
        """.trimIndent().split("\n")
        val expected = ClawMachine(
            Coordinate2(94, 34),
            Coordinate2(22, 67),
            Coordinate2(8400, 5400)
        )
        val machine = ClawMachine.parse(text[0], text[1], text[2])
        assertEquals(expected, machine)
    }

    @Test
    fun `claw 1`() {
        val text = """
            Button A: X+94, Y+34
            Button B: X+22, Y+67
            Prize: X=8400, Y=5400
        """.trimIndent().split("\n")
        val machine = ClawMachine.parse(text[0], text[1], text[2])
        val result = machine.calculatePrice()
        assertEquals(280, result)
    }

    @Test
    fun `claw 2`() {
        val text = """
            Button A: X+26, Y+66
            Button B: X+67, Y+21
            Prize: X=12748, Y=12176
        """.trimIndent().split("\n")
        val machine = ClawMachine.parse(text[0], text[1], text[2])
        val result = machine.calculatePrice()
        assertEquals(0, result)
    }

    @Test
    fun `claw 3`() {
        val text = """
            Button A: X+17, Y+86
            Button B: X+84, Y+37
            Prize: X=7870, Y=6450
        """.trimIndent().split("\n")
        val machine = ClawMachine.parse(text[0], text[1], text[2])
        val result = machine.calculatePrice()
        assertEquals(200, result)
    }

    @Test
    fun `claw 4`() {
        val text = """
            Button A: X+69, Y+23
            Button B: X+27, Y+71
            Prize: X=18641, Y=10279
        """.trimIndent().split("\n")
        val machine = ClawMachine.parse(text[0], text[1], text[2])
        val result = machine.calculatePrice()
        assertEquals(0, result)
    }

    @Test
    fun `claw 1 larger`() {
        val text = """
            Button A: X+94, Y+34
            Button B: X+22, Y+67
            Prize: X=8400, Y=5400
        """.trimIndent().split("\n")
        val machine = ClawMachine.parse(text[0], text[1], text[2])
        val result = machine.calculateLargerPrice()
        assertTrue(result == 0L)
    }

    @Test
    fun `claw 2 larger`() {
        val text = """
            Button A: X+26, Y+66
            Button B: X+67, Y+21
            Prize: X=12748, Y=12176
        """.trimIndent().split("\n")
        val machine = ClawMachine.parse(text[0], text[1], text[2])
        val result = machine.calculateLargerPrice()
        assertTrue(result > 0)
    }

    @Test
    fun `claw 3 larger`() {
        val text = """
            Button A: X+17, Y+86
            Button B: X+84, Y+37
            Prize: X=7870, Y=6450
        """.trimIndent().split("\n")
        val machine = ClawMachine.parse(text[0], text[1], text[2])
        val result = machine.calculateLargerPrice()
        assertTrue(result == 0L)
    }

    @Test
    fun `claw 4 larger`() {
        val text = """
            Button A: X+69, Y+23
            Button B: X+27, Y+71
            Prize: X=18641, Y=10279
        """.trimIndent().split("\n")
        val machine = ClawMachine.parse(text[0], text[1], text[2])
        val result = machine.calculateLargerPrice()
        assertTrue(result > 0)
    }

}