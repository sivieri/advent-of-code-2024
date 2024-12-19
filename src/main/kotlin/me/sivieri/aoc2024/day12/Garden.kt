package me.sivieri.aoc2024.day12

import me.sivieri.aoc2024.common.Coordinate2

class Garden(private val data: String) {

    private val map = data
        .split("\n")
        .filter { it.isNotBlank() }
        .map { it.toCharArray().toTypedArray() }
        .toTypedArray()
    private val maxX = map[0].size
    private val maxY = map.size

    fun calculateFencingPrice(): Long = findPlots().sumOf { println(it); it.calculateFencingPrice().toLong() }

    private fun findPlots(): List<Plot> =
        ('A' .. 'Z').flatMap { l ->
            val filtered = data
                .split("\n")
                .filter { it.isNotBlank() }
                .map { it.toList().map { if (it == l) it else EMPTY }.toTypedArray() }
                .toTypedArray()
            val plots = mutableListOf<Plot>()
            while (!allEmpty(filtered)) {
                val coords = mutableSetOf<Coordinate2>()
                var sz: Int
                do {
                    sz = coords.size
                    (0 until maxY).forEach { y ->
                        (0 until maxX).forEach { x ->
                            if (filtered[y][x] != EMPTY) {
                                val c = Coordinate2(x, y)
                                if (coords.isEmpty()) coords.add(c)
                                else if (c.neighbors { it.isValid() }.any { coords.contains(it) }) coords.add(c)
                            }
                        }
                    }
                } while (sz != coords.size)
                plots.add(Plot(l.toString(), coords.toList()))
                coords.forEach { filtered[it.y][it.x] = EMPTY }
            }
            plots
        }

    private fun allEmpty(filtered: Array<Array<Char>>): Boolean = (0 until maxY).all { y ->
        (0 until maxX).all { x ->
            filtered[y][x] == EMPTY
        }
    }

    private fun Coordinate2.isValid(): Boolean =
        this.x in 0 until maxX &&
            this.y in 0 until maxY

    companion object {
        private const val EMPTY = '.'
    }

}