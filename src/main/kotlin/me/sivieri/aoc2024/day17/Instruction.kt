package me.sivieri.aoc2024.day17

import kotlin.math.pow
import kotlin.math.truncate

sealed interface Instruction {
    val opcode: Int
    val combo: Boolean
    fun calc(op: Int, registers: Registers): Pair<Int?, Int?>

    companion object {
        fun instr(opcode: Int): Instruction = when (opcode) {
            0 -> adv()
            1 -> bxl()
            2 -> bst()
            3 -> jnz()
            4 -> bxc()
            5 -> out()
            6 -> bdv()
            7 -> cdv()
            else -> throw IllegalArgumentException("Unknown opcode $opcode")
        }
    }
}

class adv: Instruction {
    override val opcode: Int = 0
    override val combo: Boolean = true
    override fun calc(op: Int, registers: Registers): Pair<Int?, Int?> {
        registers.a = truncate(registers.a / 2.0.pow(op)).toInt()
        return Pair(null, null)
    }
}

class bxl: Instruction {
    override val opcode: Int = 1
    override val combo: Boolean = false
    override fun calc(op: Int, registers: Registers): Pair<Int?, Int?> {
        registers.b = registers.b xor op
        return Pair(null, null)
    }
}

class bst: Instruction {
    override val opcode: Int = 2
    override val combo: Boolean = true
    override fun calc(op: Int, registers: Registers): Pair<Int?, Int?> {
        registers.b = op.mod(8)
        return Pair(null, null)
    }
}

class jnz: Instruction {
    override val opcode: Int = 3
    override val combo: Boolean = false
    override fun calc(op: Int, registers: Registers): Pair<Int?, Int?> {
        return if (registers.a == 0) Pair(null, null)
        else Pair(op, null)
    }
}

class bxc: Instruction {
    override val opcode: Int = 4
    override val combo: Boolean = false
    override fun calc(op: Int, registers: Registers): Pair<Int?, Int?> {
        registers.b = registers.b xor registers.c
        return Pair(null, null)
    }
}

class out: Instruction {
    override val opcode: Int = 5
    override val combo: Boolean = true
    override fun calc(op: Int, registers: Registers): Pair<Int?, Int?> {
        return Pair(null, op.mod(8))
    }
}

class bdv: Instruction {
    override val opcode: Int = 6
    override val combo: Boolean = true
    override fun calc(op: Int, registers: Registers): Pair<Int?, Int?> {
        registers.b = truncate(registers.a / 2.0.pow(op)).toInt()
        return Pair(null, null)
    }
}

class cdv: Instruction {
    override val opcode: Int = 7
    override val combo: Boolean = true
    override fun calc(op: Int, registers: Registers): Pair<Int?, Int?> {
        registers.c = truncate(registers.a / 2.0.pow(op)).toInt()
        return Pair(null, null)
    }
}
