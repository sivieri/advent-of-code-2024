package me.sivieri.aoc2024.day5

class Printer(input: String) {

    private val rules: List<Rule>
    private val updates: List<Update>

    init {
        val (part1, part2) = input.split("\n\n", limit = 2)
        rules = part1
            .split("\n")
            .filter { it.isNotBlank() }
            .map {
                val parts = it.split("|")
                Rule(parts[0].toInt(), parts[1].toInt(), RuleStatus.NOT_USED)
            }
        updates = part2
            .split("\n")
            .filter { it.isNotBlank() }
            .map {
                Update(it.split(",").map { it.toInt() })
            }
    }

    fun sumMiddlePagesOfValidUpdates(): Int = findValidUpdates().first.sumOf { it.middle() }

    fun sumMiddlePagesAfterCorrection(): Int = findValidUpdates()
        .second
        .map { it.correct(rules) }
        .sumOf { it.middle() }

    private fun findValidUpdates(): Pair<List<Update>, List<Update>> {
        return updates.partition { update ->
            update.isValid(rules)
        }
    }

}