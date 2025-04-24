package me.sivieri.aoc2024.day21

interface Keypad {

    fun findDistances(start: Symbol, end: Symbol): List<List<Symbol>>

}