package me.sivieri.aoc2024.day9

class DiskManager(data: String) {

    private val initialDiskMap: Array<Char> = data.trim('\n').toCharArray().toTypedArray()
    private val expandedDiskMap: Array<Int> = Array(initialDiskMap.size * 10) { EMPTY }
    private var lastPointer: Int = 0

    init {
        initialDiskMap
            .toList()
            .chunked(2)
            .forEachIndexed { index, s ->
                val sz = s[0].digitToInt()
                (1 .. sz).forEach { _ -> expandedDiskMap[lastPointer++] = index }
                if (s.size == 2) {
                    val blanks = s[1].digitToInt()
                    (1..blanks).forEach { _ -> expandedDiskMap[lastPointer++] = EMPTY }
                }
            }
    }

    fun checksum(): Long =
        expandedDiskMap
            .indices
            .sumOf { i ->
                if (expandedDiskMap[i] != EMPTY) i * expandedDiskMap[i].toLong() else 0
            }

    fun compact() {
        var emptySpace = findFirstEmptySpace()
        var lastBlock = findLastBlock()
        while (emptySpace < lastBlock) {
            expandedDiskMap[emptySpace] = expandedDiskMap[lastBlock]
            expandedDiskMap[lastBlock] = EMPTY
            emptySpace = findFirstEmptySpace()
            lastBlock = findLastBlock()
        }
    }

    private fun findFirstEmptySpace(): Int = expandedDiskMap
        .indices
        .first { expandedDiskMap[it] == EMPTY }

    private fun findLastBlock(): Int = expandedDiskMap
        .indices
        .reversed()
        .first { expandedDiskMap[it] != EMPTY }

    companion object {
        private const val EMPTY = -1
    }

}