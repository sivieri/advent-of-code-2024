package me.sivieri.aoc2024.day1

import kotlin.math.abs

object LocationList {

    fun totalDistance(pairs: List<Pair<Long, Long>>): Long {
        val first = pairs.map { it.first }.sorted()
        val second = pairs.map { it.second }.sorted()
        return first.zip(second).sumOf { abs(it.first - it.second) }
    }

    fun similarityScore(pairs: List<Pair<Long, Long>>): Long {
        val first = pairs.map { it.first }
        val second = pairs.map { it.second }
        return first.sumOf { v ->
            v * second.count { v == it }
        }
    }

}