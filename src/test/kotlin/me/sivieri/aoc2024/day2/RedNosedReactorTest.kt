package me.sivieri.aoc2024.day2

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class RedNosedReactorTest {

    @Test
    fun `part 1 example`() {
        val data = """
            7 6 4 2 1
            1 2 7 8 9
            9 7 6 2 1
            1 3 2 4 5
            8 6 4 4 1
            1 3 6 7 9
        """.trimIndent()
            .split("\n")
            .map { Report.fromString(it) }
        val reactor = RedNosedReactor(data)
        val count = reactor.countSafeReports()
        assertEquals(2, count)
    }

}