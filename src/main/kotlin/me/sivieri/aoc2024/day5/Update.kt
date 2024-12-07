package me.sivieri.aoc2024.day5

data class Update(
    val pages: List<Int>
) {

    fun middle(): Int = pages[pages.size / 2]

    fun isValid(rules: List<Rule>): Boolean = isValid(pages, rules)

    fun correct(rules: List<Rule>): Update {
        val resultingPages = pages.toMutableList()
        while (true) {
            resultingPages
                .mapIndexed { index, p -> Pair(index, p) }
                .reversed()
                .forEach { (index, p) ->
                    val afterPages = rules.filter { rule -> rule.before == p }.map { rule -> rule.after }
                    (0 until index).forEach { i ->
                        if (resultingPages[i] in afterPages) {
                            resultingPages.add(resultingPages.removeAt(i))
                        }
                    }
                }
            if (isValid(resultingPages, rules)) return Update(resultingPages)
        }
    }

    companion object {
        private fun isValid(pages: List<Int>, rules: List<Rule>): Boolean {
            rules.forEach { it.status = RuleStatus.NOT_USED }
            pages
                .forEach { v ->
                    rules.forEach { rule ->
                        if (v == rule.before && rule.status == RuleStatus.NOT_USED) rule.status = RuleStatus.START
                        if (v == rule.before && rule.status == RuleStatus.END) rule.status = RuleStatus.END_BEFORE_START
                        if (v == rule.before && rule.status == RuleStatus.END_BEFORE_START) rule.status =
                            RuleStatus.END_BEFORE_START
                        if (v == rule.before && rule.status == RuleStatus.START) rule.status = RuleStatus.START

                        if (v == rule.after && rule.status == RuleStatus.NOT_USED) rule.status = RuleStatus.END
                        if (v == rule.after && rule.status == RuleStatus.START) rule.status = RuleStatus.END
                        if (v == rule.after && rule.status == RuleStatus.END) rule.status = RuleStatus.END
                        if (v == rule.after && rule.status == RuleStatus.END_BEFORE_START) rule.status =
                            RuleStatus.END_BEFORE_START
                    }
                }
            return rules.none { it.status == RuleStatus.END_BEFORE_START }
        }
    }

}
