package me.sivieri.aoc2024.day17

class Computer(data: String) {

    private val program: List<Int>
    private var registers: Registers
    private var pointer = 0

    init {
        val (part1, part2) = data.split("\n\n")
        val registerStrings = part1.split("\n")
        val a = aRegister
            .matchEntire(registerStrings[0])
            ?.destructured
            ?.let { (it) -> it.toInt()}
            ?: throw IllegalArgumentException("Unable to parse ${registerStrings[0]}")
        val b = bRegister
            .matchEntire(registerStrings[1])
            ?.destructured
            ?.let { (it) -> it.toInt()}
            ?: throw IllegalArgumentException("Unable to parse ${registerStrings[1]}")
        val c = cRegister
            .matchEntire(registerStrings[2])
            ?.destructured
            ?.let { (it) -> it.toInt()}
            ?: throw IllegalArgumentException("Unable to parse ${registerStrings[2]}")
        registers = Registers(a, b, c)
        val (_, codes) = part2.trim('\n').split(" ")
        program = codes.split(",").map { it.toInt() }
    }

    fun calculateOutput(): String {
        val output = mutableListOf<Int>()
        while (pointer < program.size) {
            val opcode = program[pointer]
            val v = program[pointer + 1]
            val instruction = Instruction.instr(opcode)
            val operand = if (instruction.combo) comboOperand(v) else v
            val (jump, out) = instruction.calc(operand, registers)
            if (out != null) output.add(out)
            if (jump == null) pointer += 2
            else pointer = jump
        }
        return output.joinToString(",")
    }

    fun findRegisterValue(): Int {
        val ref = program.joinToString(",")
        var out: String
        var i = -1
        do {
            i++
            if (i % 1_000_000 == 0) println("Round $i")
            registers = Registers(i, 0, 0)
            pointer = 0
            out = calculateOutput()
        } while (out != ref)
        return i
    }

    private fun comboOperand(v: Int): Int = when (v) {
        0 -> 0
        1 -> 1
        2 -> 2
        3 -> 3
        4 -> registers.a
        5 -> registers.b
        6 -> registers.c
        7 -> throw IllegalArgumentException("Operand 7 is reserved")
        else -> throw AssertionError("Operand must be between 0 and 7")
    }

    companion object {
        private val aRegister = "Register A: ([0-9]+)".toRegex()
        private val bRegister = "Register B: ([0-9]+)".toRegex()
        private val cRegister = "Register C: ([0-9]+)".toRegex()
    }

}