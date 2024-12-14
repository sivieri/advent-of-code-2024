package me.sivieri.aoc2024.common

sealed interface Line {
    companion object {
        fun findLine(
            first: Coordinate2,
            second: Coordinate2
        ): Line =
            if (first.x == second.x) VerticalLine(first.x)
            else if (first.y == second.y) HorizontalLine(first.y)
            else DiagonalLine(
                (second.y - first.y).toDouble() / (second.x - first.x),
                (second.y - first.y).toDouble() / (second.x - first.x) * first.x - first.y
            )
    }
}

data class DiagonalLine(val m: Double, val q: Double): Line

data class HorizontalLine(val y: Int): Line

data class VerticalLine(val x: Int): Line