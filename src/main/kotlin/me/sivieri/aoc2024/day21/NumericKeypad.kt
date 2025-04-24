package me.sivieri.aoc2024.day21

import me.sivieri.aoc2024.day21.Symbol.*
import org.jgrapht.alg.shortestpath.AllDirectedPaths
import org.jgrapht.graph.SimpleDirectedGraph

/**
 * +---+---+---+
 * | 7 | 8 | 9 |
 * +---+---+---+
 * | 4 | 5 | 6 |
 * +---+---+---+
 * | 1 | 2 | 3 |
 * +---+---+---+
 *     | 0 | A |
 *     +---+---+
 */
class NumericKeypad: Keypad {

    private val graph = SimpleDirectedGraph<Symbol, SymbolEdge>(SymbolEdge::class.java)

    init {
        graph.addVertex(ACTIVATE)
        graph.addVertex(ZERO)
        graph.addVertex(ONE)
        graph.addVertex(TWO)
        graph.addVertex(THREE)
        graph.addVertex(FOUR)
        graph.addVertex(FIVE)
        graph.addVertex(SIX)
        graph.addVertex(SEVEN)
        graph.addVertex(EIGHT)
        graph.addVertex(NINE)

        graph.addEdge(ACTIVATE, THREE, SymbolEdge(UP))
        graph.addEdge(ACTIVATE, ZERO, SymbolEdge(LEFT))
        graph.addEdge(ZERO, ACTIVATE, SymbolEdge(RIGHT))
        graph.addEdge(ZERO, TWO, SymbolEdge(UP))
        graph.addEdge(ONE, TWO, SymbolEdge(RIGHT))
        graph.addEdge(ONE, FOUR, SymbolEdge(UP))
        graph.addEdge(TWO, ONE, SymbolEdge(LEFT))
        graph.addEdge(TWO, FIVE, SymbolEdge(UP))
        graph.addEdge(TWO, THREE, SymbolEdge(RIGHT))
        graph.addEdge(TWO, ZERO, SymbolEdge(DOWN))
        graph.addEdge(THREE, ACTIVATE, SymbolEdge(DOWN))
        graph.addEdge(THREE, TWO, SymbolEdge(LEFT))
        graph.addEdge(THREE, SIX, SymbolEdge(UP))
        graph.addEdge(FOUR, FIVE, SymbolEdge(RIGHT))
        graph.addEdge(FOUR, ONE, SymbolEdge(DOWN))
        graph.addEdge(FOUR, SEVEN, SymbolEdge(UP))
        graph.addEdge(FIVE, EIGHT, SymbolEdge(UP))
        graph.addEdge(FIVE, FOUR, SymbolEdge(LEFT))
        graph.addEdge(FIVE, SIX, SymbolEdge(RIGHT))
        graph.addEdge(FIVE, TWO, SymbolEdge(DOWN))
        graph.addEdge(SIX, NINE, SymbolEdge(UP))
        graph.addEdge(SIX, FIVE, SymbolEdge(LEFT))
        graph.addEdge(SIX, THREE, SymbolEdge(DOWN))
        graph.addEdge(SEVEN, FOUR, SymbolEdge(DOWN))
        graph.addEdge(SEVEN, EIGHT, SymbolEdge(RIGHT))
        graph.addEdge(EIGHT, SEVEN, SymbolEdge(LEFT))
        graph.addEdge(EIGHT, FIVE, SymbolEdge(DOWN))
        graph.addEdge(EIGHT, NINE, SymbolEdge(RIGHT))
        graph.addEdge(NINE, EIGHT, SymbolEdge(LEFT))
        graph.addEdge(NINE, SIX, SymbolEdge(DOWN))
    }

    override fun findDistances(start: Symbol, end: Symbol): List<List<Symbol>> {
        val searcher = AllDirectedPaths(graph)
        return searcher
            .getAllPaths(start, end, true, null)
            .map { it.edgeList.map { it.symbol } }
    }

}