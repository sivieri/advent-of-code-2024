package me.sivieri.aoc2024.day14

import me.sivieri.aoc2024.common.Coordinate2
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class RobotTest {

    @Test
    fun `parse test`() {
        val text = "p=10,3 v=-1,2"
        val robot = Robot.parse(text)
        val expected = Robot(Coordinate2(10, 3), Coordinate2(-1, 2))
        assertEquals(expected, robot)
    }

    @Test
    fun `movement test 1`() {
        val robot = Robot(Coordinate2(3, 0), Coordinate2(2, -2))
        robot.turn(11, 7)
        assertEquals(Coordinate2(5, 5), robot.position)
    }

    @Test
    fun `movement test 2`() {
        val robot = Robot(Coordinate2(10, 0), Coordinate2(2, 2))
        robot.turn(11, 7)
        assertEquals(Coordinate2(1, 2), robot.position)
    }

}