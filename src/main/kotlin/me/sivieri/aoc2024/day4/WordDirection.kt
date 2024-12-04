package me.sivieri.aoc2024.day4

import me.sivieri.aoc2024.common.Coordinate2D

enum class WordDirection(val c: Coordinate2D) {
    UP(Coordinate2D(0, -1)),
    UP_RIGHT(Coordinate2D(1, -1)),
    RIGHT(Coordinate2D(1, 0)),
    DOWN_RIGHT(Coordinate2D(1, 1)),
    DOWN(Coordinate2D(0, 1)),
    DOWN_LEFT(Coordinate2D(-1, 1)),
    LEFT(Coordinate2D(-1, 0)),
    UP_LEFT(Coordinate2D(-1, -1));
}
