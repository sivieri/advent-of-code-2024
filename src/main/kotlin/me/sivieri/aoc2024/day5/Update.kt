package me.sivieri.aoc2024.day5

data class Update(
    val pages: List<Int>
) {

    fun middle(): Int = pages[pages.size / 2]

}
