package me.sivieri.aoc2024.day9

class DiskManager(data: String) {

    private val compactedDiskMap: Array<Char> = data.trim('\n').toCharArray().toTypedArray()
    private val diskMap: Array<Int> = Array(compactedDiskMap.size * 10) { EMPTY }
    private val repetitionsMap: Array<Int> = Array(compactedDiskMap.size * 10) { EMPTY }
    private val blanksMap: Array<Int> = Array(compactedDiskMap.size * 10) { EMPTY }
    private var lastPointer: Int = 0
    private val blocks: Int

    init {
        val chunks = compactedDiskMap
            .toList()
            .chunked(2)
        blocks = chunks.size
        chunks
            .forEachIndexed { index, s ->
                val sz = s[0].digitToInt()
                (1 .. sz).forEach { _ ->
                    diskMap[lastPointer] = index
                    repetitionsMap[lastPointer] = sz
                    lastPointer++
                }
                if (s.size == 2) {
                    val blanks = s[1].digitToInt()
                    (1..blanks).forEach { _ ->
                        diskMap[lastPointer] = EMPTY
                        blanksMap[lastPointer] = BLANK
                        lastPointer++
                    }
                }
            }
    }

    fun checksum(): Long =
        diskMap
            .indices
            .sumOf { i ->
                if (diskMap[i] != EMPTY) i * diskMap[i].toLong() else 0
            }

    fun compact() {
        var emptySpace = findFirstEmptySpace()
        var lastBlock = findLastBlock()
        while (emptySpace < lastBlock) {
            diskMap[emptySpace] = diskMap[lastBlock]
            diskMap[lastBlock] = EMPTY
            emptySpace = findFirstEmptySpace()
            lastBlock = findLastBlock()
        }
    }

    fun compactFullFiles() {
        var terminalPosition = lastPointer - 1
        while (terminalPosition >= 0) {
            val (blockPosition, blockSize) = findNextBlock(terminalPosition)
            val freeSpacePosition = findFirstFreeSpace(blockSize)
            if (freeSpacePosition != NOT_FOUND && freeSpacePosition < blockPosition) {
                val value = diskMap[blockPosition]
                (0 until blockSize).forEach { i ->
                    diskMap[freeSpacePosition + i] = value
                    repetitionsMap[freeSpacePosition + i] = blockSize
                    blanksMap[freeSpacePosition + i] = EMPTY
                    diskMap[blockPosition + i] = EMPTY
                    repetitionsMap[blockPosition + i] = EMPTY
                    blanksMap[blockPosition + i] = BLANK
                }
            }
            terminalPosition = blockPosition - 1
        }
    }

    fun findFirstFreeSpace(blockSize: Int): Int {
        var cur = 0
        var sz = 0
        while (cur < lastPointer) {
            if (sz == blockSize) return cur - blockSize
            if (blanksMap[cur] == BLANK) sz++
            else sz = 0
            cur++
        }
        return NOT_FOUND
    }

    fun findNextBlock(terminalPosition: Int): Pair<Int, Int> {
        var cur = terminalPosition
        while (diskMap[cur] == EMPTY) cur--
        val size = repetitionsMap[cur]
        val pos = cur - size + 1
        return Pair(pos, size)
    }

    private fun findFirstEmptySpace(): Int = diskMap
        .indices
        .first { diskMap[it] == EMPTY }

    private fun findLastBlock(): Int = diskMap
        .indices
        .reversed()
        .first { diskMap[it] != EMPTY }

    companion object {
        private const val EMPTY = -1
        private const val NOT_FOUND = -1
        private const val BLANK = -2
    }

}