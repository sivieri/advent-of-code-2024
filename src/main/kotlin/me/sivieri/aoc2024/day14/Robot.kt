package me.sivieri.aoc2024.day14

import me.sivieri.aoc2024.common.Coordinate2
import java.lang.IllegalArgumentException

data class Robot(
    var position: Coordinate2,
    val velocity: Coordinate2
) {

    fun turn(maxX: Int, maxY: Int) {
        var x = position.x + velocity.x
        var y = position.y + velocity.y
        if (x < 0) x += maxX
        else if (x >= maxX) x -= maxX
        if (y < 0) y += maxY
        else if (y >= maxY) y -= maxY
        position = Coordinate2(x, y)
    }

    companion object {
        private val robotRegex = "p=(-?[0-9]+),(-?[0-9]+) v=(-?[0-9]+),(-?[0-9]+)".toRegex()

        fun parse(line: String): Robot =
            robotRegex
                .matchEntire(line)
                ?.destructured
                ?.let { (a, b, c, d) ->
                    Robot(
                        Coordinate2(a.toInt(), b.toInt()),
                        Coordinate2(c.toInt(), d.toInt())
                    )
                } ?: throw IllegalArgumentException("Unable to parse $line")
    }
}
