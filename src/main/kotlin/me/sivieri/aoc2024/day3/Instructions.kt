package me.sivieri.aoc2024.day3

sealed class Instruction(val index: Int)

class Multiply(
    index: Int,
    val a: Long,
    val b: Long
): Instruction(index) {
    fun result(): Long = a * b

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Multiply

        if (index != other.index) return false
        if (a != other.a) return false
        if (b != other.b) return false

        return true
    }

    override fun hashCode(): Int {
        var result = a.hashCode()
        result = 31 * result + b.hashCode()
        result = 31 * result + index.hashCode()
        return result
    }

    override fun toString(): String {
        return "Multiply(index=$index, a=$a, b=$b)"
    }

    companion object {
        val regex = "mul\\([0-9]+,[0-9]+\\)".toRegex()
    }
}

class Do(index: Int): Instruction(index) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Do

        if (index != other.index) return false

        return true
    }

    override fun hashCode(): Int {
        val result = index.hashCode()
        return result
    }

    override fun toString(): String {
        return "Do(index=$index)"
    }

    companion object {
        val regex = "do\\(\\)".toRegex()
    }
}

class Dont(index: Int): Instruction(index) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Dont

        if (index != other.index) return false

        return true
    }

    override fun hashCode(): Int {
        val result = index.hashCode()
        return result
    }

    override fun toString(): String {
        return "Dont(index=$index)"
    }

    companion object {
        val regex = "don't\\(\\)".toRegex()
    }
}
