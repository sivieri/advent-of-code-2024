package me.sivieri.aoc2024.day4

import me.sivieri.aoc2024.common.Coordinate2

enum class WordDirection(val c: Coordinate2) {
    UP(Coordinate2(0, -1)),
    UP_RIGHT(Coordinate2(1, -1)),
    RIGHT(Coordinate2(1, 0)),
    DOWN_RIGHT(Coordinate2(1, 1)),
    DOWN(Coordinate2(0, 1)),
    DOWN_LEFT(Coordinate2(-1, 1)),
    LEFT(Coordinate2(-1, 0)),
    UP_LEFT(Coordinate2(-1, -1));
}
