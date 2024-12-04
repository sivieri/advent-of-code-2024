package me.sivieri.aoc2024.day4

class WordField(
    text: String
) {

    private val letters = text
        .split("\n")
        .map { it.toCharArray().toTypedArray() }
        .toTypedArray()
    private val maxX = letters[0].size
    private val maxY = letters.size

    fun countXmasOccurrences(): Int {
        var counter = 0

        (0 until maxX).forEach { x ->
            (0 until maxY).forEach { y ->
                if (letters[y][x] == XMAS[0]) {
                   counter += searchXmas(x, y)
                }
            }
        }

        return counter
    }

    private fun searchXmas(x: Int, y: Int): Int {
        TODO("Not yet implemented")
    }

    companion object {
        private const val XMAS = "XMAS"
    }

}