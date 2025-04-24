package me.sivieri.aoc2024.day21

import me.sivieri.aoc2024.day21.Symbol.*
import org.jgrapht.alg.shortestpath.AllDirectedPaths
import org.jgrapht.graph.SimpleDirectedGraph

/**
 *     +---+---+
 *     | ^ | A |
 * +---+---+---+
 * | < | v | > |
 * +---+---+---+
 */
class DirectionalKeypad: Keypad {

    private val graph = SimpleDirectedGraph<Symbol, SymbolEdge>(SymbolEdge::class.java)

    init {
        graph.addVertex(ACTIVATE)
        graph.addVertex(LEFT)
        graph.addVertex(RIGHT)
        graph.addVertex(UP)
        graph.addVertex(DOWN)

        graph.addEdge(ACTIVATE, RIGHT, SymbolEdge(DOWN))
        graph.addEdge(ACTIVATE, UP, SymbolEdge(LEFT))
        graph.addEdge(UP, ACTIVATE, SymbolEdge(RIGHT))
        graph.addEdge(UP, DOWN, SymbolEdge(DOWN))
        graph.addEdge(RIGHT, ACTIVATE, SymbolEdge(UP))
        graph.addEdge(RIGHT, DOWN, SymbolEdge(LEFT))
        graph.addEdge(DOWN, UP, SymbolEdge(UP))
        graph.addEdge(DOWN, RIGHT, SymbolEdge(RIGHT))
        graph.addEdge(DOWN, LEFT, SymbolEdge(LEFT))
        graph.addEdge(LEFT, DOWN, SymbolEdge(RIGHT))
    }

    override fun findDistances(start: Symbol, end: Symbol): List<List<Symbol>> {
        val searcher = AllDirectedPaths(graph)
        return searcher
            .getAllPaths(start, end, true, null)
            .map { it.edgeList.map { it.symbol } }
    }

}