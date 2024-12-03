package me.sivieri.aoc2024.day3

object Computer {

    fun findUncorruptedMultiply(text: String): List<Multiply> {
        val matched = Multiply.regex.findAll(text)
        return matched.map {
            val parts = it
                .value
                .replace("mul(", "")
                .replace(")", "")
                .split(",")
            Multiply(parts[0].toLong(), parts[1].toLong())
        }.toList()
    }

}