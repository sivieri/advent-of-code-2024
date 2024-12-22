package me.sivieri.aoc2024.day13

import me.sivieri.aoc2024.common.Coordinate2
import org.ejml.data.DMatrixRMaj
import org.ejml.simple.SimpleMatrix
import kotlin.math.roundToInt
import kotlin.math.abs

data class ClawMachine(
    val a: Coordinate2,
    val b: Coordinate2,
    val prize: Coordinate2
) {

    fun calculatePrice(): Int = buttonsPressing()
        ?.let { it.first * 3 + it.second * 1 }
        ?: -1

    private fun buttonsPressing(): Pair<Int, Int>? {
        val a = arrayOf(
            arrayOf(a.x.toDouble(), b.x.toDouble()).toDoubleArray(),
            arrayOf(a.y.toDouble(), b.y.toDouble()).toDoubleArray()
        )
        val b = arrayOf(
            prize.x.toDouble(),
            prize.y.toDouble()
        ).toDoubleArray()
        val am = SimpleMatrix.wrap(DMatrixRMaj(a))
        val bm = SimpleMatrix.wrap(DMatrixRMaj(b))
        val x = am.solve(bm)
        val ap = x.get(0)
        val bp = x.get(1)
        println("For $this: a $ap, b $bp")
        val apr = ap.roundToInt()
        val bpr = bp.roundToInt()
        return if (abs(ap - apr) > 0.0001 || abs(bp - bpr) > 0.0001) null
        else Pair(apr, bpr)
    }

    companion object {
        private val linea = "Button A: X\\+([0-9]+), Y\\+([0-9]+)".toRegex()
        private val lineb = "Button B: X\\+([0-9]+), Y\\+([0-9]+)".toRegex()
        private val lineprize = "Prize: X=([0-9]+), Y=([0-9]+)".toRegex()

        fun parse(line1: String, line2: String, line3: String): ClawMachine {
            val a = linea
                .matchEntire(line1)
                ?.destructured
                ?.let { (x, y) ->
                    Coordinate2(x.toInt(), y.toInt())
                } ?: throw IllegalArgumentException("Unable to parse button A from $line1")
            val b = lineb
                .matchEntire(line2)
                ?.destructured
                ?.let { (x, y) ->
                    Coordinate2(x.toInt(), y.toInt())
                } ?: throw IllegalArgumentException("Unable to parse button B from $line2")
            val prize = lineprize
                .matchEntire(line3)
                ?.destructured
                ?.let { (x, y) ->
                    Coordinate2(x.toInt(), y.toInt())
                } ?: throw IllegalArgumentException("Unable to parse prize from $line3")
            return ClawMachine(a, b, prize)
        }
    }
}