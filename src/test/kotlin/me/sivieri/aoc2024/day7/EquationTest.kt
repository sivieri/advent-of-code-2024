package me.sivieri.aoc2024.day7

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class EquationTest {

    @Test
    fun one() {
        val eq = Equation(1, 190, listOf(10, 19))
        assertEquals(true, eq.tryRepairOperators())
    }
    @Test
    fun two() {
        val eq = Equation(2, 3267, listOf(81, 40, 27))
        assertEquals(true, eq.tryRepairOperators())
    }

    @Test
    fun three() {
        val eq = Equation(3, 83, listOf(17, 5))
        assertEquals(false, eq.tryRepairOperators())
    }

    @Test
    fun four() {
        val eq = Equation(4, 156, listOf(15, 6))
        assertEquals(false, eq.tryRepairOperators())
    }

    @Test
    fun five() {
        val eq = Equation(5, 7290, listOf(6, 8, 6, 15))
        assertEquals(false, eq.tryRepairOperators())
    }

    @Test
    fun six() {
        val eq = Equation(6, 161011, listOf(16, 10, 13))
        assertEquals(false, eq.tryRepairOperators())
    }

    @Test
    fun seven() {
        val eq = Equation(7, 192, listOf(17, 8, 14))
        assertEquals(false, eq.tryRepairOperators())
    }

    @Test
    fun eight() {
        val eq = Equation(8, 21037, listOf(9, 7, 18, 13))
        assertEquals(false, eq.tryRepairOperators())
    }

    @Test
    fun nine() {
        val eq = Equation(9, 292, listOf(11, 6, 16, 20))
        assertEquals(true, eq.tryRepairOperators())
    }


}