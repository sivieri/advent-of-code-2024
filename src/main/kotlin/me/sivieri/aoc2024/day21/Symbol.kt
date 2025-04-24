package me.sivieri.aoc2024.day21

enum class Symbol(val c: Char) {

    ZERO('0'),
    ONE('1'),
    TWO('2'),
    THREE('3'),
    FOUR('4'),
    FIVE('5'),
    SIX('6'),
    SEVEN('7'),
    EIGHT('8'),
    NINE('9'),
    ACTIVATE('A'),
    LEFT('<'),
    RIGHT('>'),
    UP('^'),
    DOWN('v'),
    NOP('T');

    companion object {
        fun fromChar(c: Char) = Symbol.entries.first { it.c == c }

        fun List<Symbol>.string(): String = this.joinToString("") { it.c.toString() }
    }

}