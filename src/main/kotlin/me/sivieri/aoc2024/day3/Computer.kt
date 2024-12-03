package me.sivieri.aoc2024.day3

object Computer {

    fun multiplyResult(text: String): Long = findUncorruptedMultiply(text)
        .sumOf { it.result() }

    private fun findUncorruptedMultiply(text: String): List<Multiply> {
        val matched = Multiply.regex.findAll(text)
        return matched.map {
            val parts = it
                .value
                .replace("mul(", "")
                .replace(")", "")
                .split(",")
            Multiply(it.range.first, parts[0].toLong(), parts[1].toLong())
        }.toList()
    }

    fun multiplyEnabledResult(text: String): Long {
        val instructions = findUncorruptedInstructions(text).sortedBy { it.index }
        var result = 0L
        var enabled = true
        instructions.forEach {
            when (it) {
                is Do -> enabled = true
                is Dont -> enabled = false
                is Multiply -> {
                    if (enabled) result += it.result()
                }
            }
        }
        return result
    }

    private fun findUncorruptedInstructions(text: String): List<Instruction> =
        findUncorruptedDo(text) + findUncorruptedDont(text) + findUncorruptedMultiply(text)

    private fun findUncorruptedDo(text: String): List<Do> {
        val matched = Do.regex.findAll(text)
        return matched.map {
            Do(it.range.first)
        }.toList()
    }

    private fun findUncorruptedDont(text: String): List<Dont> {
        val matched = Dont.regex.findAll(text)
        return matched.map {
            Dont(it.range.first)
        }.toList()
    }

}