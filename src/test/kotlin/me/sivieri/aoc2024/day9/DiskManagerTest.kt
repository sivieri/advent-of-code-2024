package me.sivieri.aoc2024.day9

import me.sivieri.aoc2024.common.zipWithIndex
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class DiskManagerTest {

    @Test
    fun `part 1 example`() {
        val data = "2333133121414131402"
        val manager = DiskManager(data)
        manager.compact()
        val result = manager.checksum()
        assertEquals(1928L, result)
    }

    @Test
    fun `12345 example`() {
        val data = "12345"
        val manager = DiskManager(data)
        manager.compact()
        val result = manager.checksum()
        val expected = "022111222".toList().zipWithIndex { it }.sumOf { it.first * it.second.digitToInt() }.toLong()
        assertEquals(expected, result)
    }

}