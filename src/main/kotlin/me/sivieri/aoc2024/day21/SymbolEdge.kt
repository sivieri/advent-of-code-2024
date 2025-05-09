package me.sivieri.aoc2024.day21

import org.jgrapht.graph.DefaultEdge

class SymbolEdge(val symbol: Symbol): DefaultEdge() {
    override fun toString(): String {
        return "SymbolEdge(symbol=$symbol)"
    }
}