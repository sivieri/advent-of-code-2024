package me.sivieri.aoc2024.day13

class Arcade(data: String) {

    private val machines = data
        .split("\n\n")
        .map {
            val lines = it.split("\n")
            ClawMachine.parse(lines[0], lines[1], lines[2])
        }

    fun minTokens(): Int {
        val solvable = machines.filter { it.calculatePrice() != -1 }
        TODO()
    }

}