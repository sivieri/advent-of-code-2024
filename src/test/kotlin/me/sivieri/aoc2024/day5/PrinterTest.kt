package me.sivieri.aoc2024.day5

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class PrinterTest {

    @Test
    fun `part 1 example`() {
        val text = """
            47|53
            97|13
            97|61
            97|47
            75|29
            61|13
            75|53
            29|13
            97|29
            53|29
            61|53
            97|53
            61|29
            47|13
            75|47
            97|75
            47|61
            75|61
            47|29
            75|13
            53|13

            75,47,61,53,29
            97,61,53,29,13
            75,29,13
            75,97,47,61,53
            61,13,29
            97,13,75,29,47
        """.trimIndent()
        val printer = Printer(text)
        val result = printer.sumMiddlePagesOfValidUpdates()
        assertEquals(143, result)
    }

    @Test
    fun `part 1 example 1`() {
        val text = """
            47|53
            97|13
            97|61
            97|47
            75|29
            61|13
            75|53
            29|13
            97|29
            53|29
            61|53
            97|53
            61|29
            47|13
            75|47
            97|75
            47|61
            75|61
            47|29
            75|13
            53|13

            75,47,61,53,29
        """.trimIndent()
        val printer = Printer(text)
        val result = printer.sumMiddlePagesOfValidUpdates()
        assertEquals(61, result)
    }

    @Test
    fun `part 1 example 2`() {
        val text = """
            47|53
            97|13
            97|61
            97|47
            75|29
            61|13
            75|53
            29|13
            97|29
            53|29
            61|53
            97|53
            61|29
            47|13
            75|47
            97|75
            47|61
            75|61
            47|29
            75|13
            53|13

            97,61,53,29,13
        """.trimIndent()
        val printer = Printer(text)
        val result = printer.sumMiddlePagesOfValidUpdates()
        assertEquals(53, result)
    }

    @Test
    fun `part 1 example 3`() {
        val text = """
            47|53
            97|13
            97|61
            97|47
            75|29
            61|13
            75|53
            29|13
            97|29
            53|29
            61|53
            97|53
            61|29
            47|13
            75|47
            97|75
            47|61
            75|61
            47|29
            75|13
            53|13

            75,29,13
        """.trimIndent()
        val printer = Printer(text)
        val result = printer.sumMiddlePagesOfValidUpdates()
        assertEquals(29, result)
    }

    @Test
    fun `part 1 example 4`() {
        val text = """
            47|53
            97|13
            97|61
            97|47
            75|29
            61|13
            75|53
            29|13
            97|29
            53|29
            61|53
            97|53
            61|29
            47|13
            75|47
            97|75
            47|61
            75|61
            47|29
            75|13
            53|13

            75,97,47,61,53
        """.trimIndent()
        val printer = Printer(text)
        val result = printer.sumMiddlePagesOfValidUpdates()
        assertEquals(0, result)
    }

    @Test
    fun `part 1 example 5`() {
        val text = """
            47|53
            97|13
            97|61
            97|47
            75|29
            61|13
            75|53
            29|13
            97|29
            53|29
            61|53
            97|53
            61|29
            47|13
            75|47
            97|75
            47|61
            75|61
            47|29
            75|13
            53|13

            61,13,29
        """.trimIndent()
        val printer = Printer(text)
        val result = printer.sumMiddlePagesOfValidUpdates()
        assertEquals(0, result)
    }

    @Test
    fun `part 1 example 6`() {
        val text = """
            47|53
            97|13
            97|61
            97|47
            75|29
            61|13
            75|53
            29|13
            97|29
            53|29
            61|53
            97|53
            61|29
            47|13
            75|47
            97|75
            47|61
            75|61
            47|29
            75|13
            53|13

            97,13,75,29,47
        """.trimIndent()
        val printer = Printer(text)
        val result = printer.sumMiddlePagesOfValidUpdates()
        assertEquals(0, result)
    }

    @Test
    fun `part 2 example`() {
        val text = """
            47|53
            97|13
            97|61
            97|47
            75|29
            61|13
            75|53
            29|13
            97|29
            53|29
            61|53
            97|53
            61|29
            47|13
            75|47
            97|75
            47|61
            75|61
            47|29
            75|13
            53|13

            75,47,61,53,29
            97,61,53,29,13
            75,29,13
            75,97,47,61,53
            61,13,29
            97,13,75,29,47
        """.trimIndent()
        val printer = Printer(text)
        val result = printer.sumMiddlePagesAfterCorrection()
        assertEquals(123, result)
    }

    @Test
    fun `part 2 example 4`() {
        val text = """
            47|53
            97|13
            97|61
            97|47
            75|29
            61|13
            75|53
            29|13
            97|29
            53|29
            61|53
            97|53
            61|29
            47|13
            75|47
            97|75
            47|61
            75|61
            47|29
            75|13
            53|13

            75,97,47,61,53
        """.trimIndent()
        val printer = Printer(text)
        val result = printer.sumMiddlePagesAfterCorrection()
        assertEquals(47, result)
    }

    @Test
    fun `part 2 example 5`() {
        val text = """
            47|53
            97|13
            97|61
            97|47
            75|29
            61|13
            75|53
            29|13
            97|29
            53|29
            61|53
            97|53
            61|29
            47|13
            75|47
            97|75
            47|61
            75|61
            47|29
            75|13
            53|13

            61,13,29
        """.trimIndent()
        val printer = Printer(text)
        val result = printer.sumMiddlePagesAfterCorrection()
        assertEquals(29, result)
    }

    @Test
    fun `part 2 example 6`() {
        val text = """
            47|53
            97|13
            97|61
            97|47
            75|29
            61|13
            75|53
            29|13
            97|29
            53|29
            61|53
            97|53
            61|29
            47|13
            75|47
            97|75
            47|61
            75|61
            47|29
            75|13
            53|13

            97,13,75,29,47
        """.trimIndent()
        val printer = Printer(text)
        val result = printer.sumMiddlePagesAfterCorrection()
        assertEquals(47, result)
    }

}