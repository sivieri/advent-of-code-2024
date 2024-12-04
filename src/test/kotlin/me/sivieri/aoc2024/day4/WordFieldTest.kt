package me.sivieri.aoc2024.day4

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class WordFieldTest {

    @Test
    fun `part 1 example`() {
        val text = """
            MMMSXXMASM
            MSAMXMSMSA
            AMXSXMAAMM
            MSAMASMSMX
            XMASAMXAMM
            XXAMMXXAMA
            SMSMSASXSS
            SAXAMASAAA
            MAMMMXMMMM
            MXMXAXMASX
        """.trimIndent()
        val wordField = WordField(text)
        val result = wordField.countXmasOccurrences()
        assertEquals(18, result)
    }

    @Test
    fun `part 2 example`() {
        val text = """
            MMMSXXMASM
            MSAMXMSMSA
            AMXSXMAAMM
            MSAMASMSMX
            XMASAMXAMM
            XXAMMXXAMA
            SMSMSASXSS
            SAXAMASAAA
            MAMMMXMMMM
            MXMXAXMASX
        """.trimIndent()
        val wordField = WordField(text)
        val result = wordField.countXAndMasOccurrences()
        assertEquals(9, result)
    }

}