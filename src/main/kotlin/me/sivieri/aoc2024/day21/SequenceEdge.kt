package me.sivieri.aoc2024.day21

import org.jgrapht.graph.DefaultWeightedEdge

class SequenceEdge(val sequence: List<Symbol>): DefaultWeightedEdge() {

    override fun getWeight(): Double = sequence.size.toDouble()

    override fun toString(): String {
        return "SequenceEdge(sequence=$sequence)"
    }
}