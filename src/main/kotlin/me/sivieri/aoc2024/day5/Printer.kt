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

    fun sumMiddlePagesOfValidUpdates(): Int = findValidUpdates().sumOf { it.middle() }

    private fun findValidUpdates(): List<Update> {
        return updates.filter { update ->
            rules.forEach { it.status = RuleStatus.NOT_USED }
            update
                .pages
                .forEach { v ->
                    rules.forEach { rule ->
                        if (v == rule.before && rule.status == RuleStatus.NOT_USED) rule.status = RuleStatus.START
                        if (v == rule.before && rule.status == RuleStatus.END) rule.status = RuleStatus.END_BEFORE_START
                        if (v == rule.before && rule.status == RuleStatus.END_BEFORE_START) rule.status = RuleStatus.END_BEFORE_START
                        if (v == rule.before && rule.status == RuleStatus.START) rule.status = RuleStatus.START

                        if (v == rule.after && rule.status == RuleStatus.NOT_USED) rule.status = RuleStatus.END
                        if (v == rule.after && rule.status == RuleStatus.START) rule.status = RuleStatus.END
                        if (v == rule.after && rule.status == RuleStatus.END) rule.status = RuleStatus.END
                        if (v == rule.after && rule.status == RuleStatus.END_BEFORE_START) rule.status = RuleStatus.END_BEFORE_START
                    }
                }
            rules.none { it.status == RuleStatus.END_BEFORE_START }
        }
    }

}