package me.sivieri.aoc2024.day4

import me.sivieri.aoc2024.common.Coordinate2D

class WordField(
    text: String
) {

    private val letters = text
        .split("\n")
        .filter { it.isNotBlank() }
        .map { it.trim('\n').toCharArray().toTypedArray() }
        .toTypedArray()
    private val maxX = letters[0].size
    private val maxY = letters.size

    fun countXmasOccurrences(): Int {
        var counter = 0

        (0 until maxY).forEach { y ->
            (0 until maxX).forEach { x ->
                counter += searchXmas(Coordinate2D(x, y))
            }
        }

        return counter
    }

    private fun searchXmas(c: Coordinate2D): Int {
        // X
        if (letters[c.y][c.x] != 'X') return 0

        // M
        val directionOfWord = WordDirection.values().filter { dir ->
            val updated = updateSafe(c, dir.c)
            updated != null && letters[updated.y][updated.x] == 'M'
        }
        return directionOfWord.count { dir ->
            val locationOfM = updateSafe(c, dir.c)!!

            // A
            val locationOfA = updateSafe(locationOfM, dir.c)
            if (locationOfA == null || letters[locationOfA.y][locationOfA.x] != 'A') return@count false

            // S
            val locationOfS = updateSafe(locationOfA, dir.c)
            if (locationOfS == null || letters[locationOfS.y][locationOfS.x] != 'S') return@count false

            return@count true
        }
    }

    fun countXAndMasOccurrences(): Int {
        var counter = 0

        val xs = (0 until maxX).windowed(3)
        (0 until maxY - 2).forEach { y ->
            xs.forEach { range ->
                val slice = IntRange(range[0], range[2])
                val current = arrayOf(
                    letters[y].sliceArray(slice),
                    letters[y + 1].sliceArray(slice),
                    letters[y + 2].sliceArray(slice),
                )
                val s = listOf(
                    current[0][0],
                    '.',
                    current[0][2],
                    '\n',
                    '.',
                    current[1][1],
                    '.',
                    '\n',
                    current[2][0],
                    '.',
                    current[2][2]
                ).joinToString("")
                if (footprints.contains(s)) counter++
            }
        }

        return counter
    }

    private fun updateSafe(c: Coordinate2D, incr: Coordinate2D): Coordinate2D? =
        if (c.x + incr.x >= maxX ||
            c.x + incr.x < 0 ||
            c.y + incr.y >= maxY ||
            c.y + incr.y < 0) null
        else Coordinate2D(c.x + incr.x, c.y + incr.y)

    companion object {
        private val footprints = setOf(
            """
                M.S
                .A.
                M.S
            """.trimIndent(),
            """
                M.M
                .A.
                S.S
            """.trimIndent(),
            """
                S.S
                .A.
                M.M
            """.trimIndent(),
            """
                S.M
                .A.
                S.M
            """.trimIndent()
        )
    }

}