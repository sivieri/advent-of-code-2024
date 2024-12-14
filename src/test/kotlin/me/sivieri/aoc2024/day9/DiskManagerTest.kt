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
    fun `part 2 example`() {
        val data = "2333133121414131402"
        val manager = DiskManager(data)
        manager.compactFullFiles()
        val result = manager.checksum()
        assertEquals(2858L, result)
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

    @Test
    fun `find blank size 3`() {
        // 00...111....2...333.44.5555.6666.777.888899
        val data = "2334133121414131402"
        val manager = DiskManager(data)
        val pos = manager.findFirstFreeSpace(3)
        assertEquals(2, pos)
    }

    @Test
    fun `find blank size 4`() {
        // 00...111....2...333.44.5555.6666.777.888899
        val data = "2334133121414131402"
        val manager = DiskManager(data)
        val pos = manager.findFirstFreeSpace(4)
        assertEquals(8, pos)
    }

    @Test
    fun `find internal file`() {
        // 00...111....2...333.44.5555.6666.777.888899
        val data = "2334133121414131402"
        val manager = DiskManager(data)
        val pos = manager.findNextBlock(22)
        assertEquals(Pair(20, 2), pos)
    }

    @Test
    fun `find last file`() {
        // 00...111....2...333.44.5555.6666.777.888899
        val data = "2334133121414131402"
        val manager = DiskManager(data)
        val pos = manager.findNextBlock(42)
        assertEquals(Pair(41, 2), pos)
    }

    @Test
    fun `find first file`() {
        // 00...111....2...333.44.5555.6666.777.888899
        val data = "2334133121414131402"
        val manager = DiskManager(data)
        val pos = manager.findNextBlock(4)
        assertEquals(Pair(0, 2), pos)
    }

}