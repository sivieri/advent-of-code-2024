package me.sivieri.aoc2024.day21

import me.sivieri.aoc2024.day21.Symbol.Companion.string
import org.jgrapht.alg.shortestpath.AllDirectedPaths
import org.jgrapht.alg.shortestpath.DijkstraShortestPath
import org.jgrapht.graph.DirectedWeightedMultigraph
import org.jgrapht.nio.Attribute
import org.jgrapht.nio.AttributeType
import org.jgrapht.nio.DefaultAttribute
import org.jgrapht.nio.dot.DOTExporter
import java.io.FileOutputStream
import java.io.OutputStreamWriter

class Door {

    fun calculateComplexity(sequence: String): Int {
        val v = findSequenceLength(sequence)
        val n = sequence.replace("A", "").toInt()
        return v * n
    }

    private fun findSequenceLength(sequence: String): Int {
        val s = listOf(START) + sequence.toList().map { Symbol.fromChar(it) }
        val formalStart = SequenceStepVertex(Symbol.NOP, -1, s.string())
        val formalEnd = SequenceStepVertex(Symbol.NOP, Integer.MAX_VALUE, s.string())

        val firstKeypad = NumericKeypad()
        val firstGraph = DirectedWeightedMultigraph<SequenceStepVertex, SequenceEdge>(SequenceEdge::class.java)
        firstGraph.addVertex(formalStart)
        firstGraph.addVertex(formalEnd)
        s
            .windowed(2)
            .forEachIndexed { i, seq ->
                val start = SequenceStepVertex(seq[0], i, s.string())
                val end = SequenceStepVertex(seq[1], i + 1, s.string())
                firstGraph.addVertex(start)
                firstGraph.addVertex(end)
                if (i == 0) firstGraph.addEdge(formalStart, start, SequenceEdge(emptyList()))
                if (i + 1 == s.size - 1) firstGraph.addEdge(end, formalEnd, SequenceEdge(emptyList()))
                firstKeypad
                    .findDistances(seq[0], seq[1])
                    .forEach {
                        firstGraph.addEdge(start, end, SequenceEdge(it + listOf(START)))
                    }
            }
        exporter.exportGraph(firstGraph, OutputStreamWriter(FileOutputStream("firstgraph.dot")))
        val firstDriver = AllDirectedPaths(firstGraph)
        val firstDij = DijkstraShortestPath(firstGraph)
        val firstMin = firstDij.getPath(formalStart, formalEnd).length
        println("First min length: $firstMin")
        val firstPaths = firstDriver
            .getAllPaths(formalStart, formalEnd, true, firstMin)
            .map { it.edgeList.map { it.sequence }.flatten() }
        val firstGlobalLength = firstPaths.minOf { it.size }
        val actualFirst = firstPaths.filter { it.size == firstGlobalLength }
        actualFirst.forEach { println(it.string()) }

        val secondKeypad = DirectionalKeypad()
        val secondGraph = DirectedWeightedMultigraph<SequenceStepVertex, SequenceEdge>(SequenceEdge::class.java)
        secondGraph.addVertex(formalStart)
        secondGraph.addVertex(formalEnd)
        actualFirst.forEach { path ->
            val actual = listOf(START) + path
            val main = actual.string()
            actual
                .windowed(2)
                .forEachIndexed { i, seq ->
                    val paths = secondKeypad.findDistances(seq[0], seq[1])
                    val start = SequenceStepVertex(seq[0], i, main)
                    val end = SequenceStepVertex(seq[1], i + 1, main)
                    secondGraph.addVertex(start)
                    secondGraph.addVertex(end)
                    if (i == 0) secondGraph.addEdge(formalStart, start, SequenceEdge(emptyList()))
                    paths.forEach { secondGraph.addEdge(start, end, SequenceEdge(it + listOf(START))) }
                    if (i + 1 == actual.size - 1) secondGraph.addEdge(end, formalEnd, SequenceEdge(emptyList()))
                }
        }
        exporter.exportGraph(secondGraph, OutputStreamWriter(FileOutputStream("secondgraph.dot")))
        val secondDriver = AllDirectedPaths(secondGraph)
        val secondDij = DijkstraShortestPath(secondGraph)
        val secondMin = secondDij.getPath(formalStart, formalEnd).length
        println("Second min length: $secondMin")
        val secondPaths = secondDriver
            .getAllPaths(formalStart, formalEnd, true, secondMin)
            .map { it.edgeList.map { it.sequence }.flatten() }
        val secondGlobalLength = secondPaths.minOf { it.size }
        val actualSecond = secondPaths.filter { it.size == secondGlobalLength }
        actualSecond.forEach { println(it.string()) }

        val thirdKeypad = DirectionalKeypad()
        val thirdGraph = DirectedWeightedMultigraph<SequenceStepVertex, SequenceEdge>(SequenceEdge::class.java)
        thirdGraph.addVertex(formalStart)
        thirdGraph.addVertex(formalEnd)
        actualSecond.forEach { path ->
            val actual = listOf(START) + path
            val main = actual.string()
            actual
                .windowed(2)
                .forEachIndexed { i, seq ->
                    val paths = thirdKeypad.findDistances(seq[0], seq[1])
                    val start = SequenceStepVertex(seq[0], i, main)
                    val end = SequenceStepVertex(seq[1], i + 1, main)
                    thirdGraph.addVertex(start)
                    thirdGraph.addVertex(end)
                    if (i == 0) thirdGraph.addEdge(formalStart, start, SequenceEdge(emptyList()))
                    paths.forEach { thirdGraph.addEdge(start, end, SequenceEdge(it + listOf(START))) }
                    if (i + 1 == actual.size - 1) thirdGraph.addEdge(end, formalEnd, SequenceEdge(emptyList()))
                }
        }
        exporter.exportGraph(thirdGraph, OutputStreamWriter(FileOutputStream("thirdgraph.dot")))
        val thirdDij = DijkstraShortestPath(thirdGraph)
        val thirdMin = thirdDij.getPath(formalStart, formalEnd)
        println("Third min length: ${thirdMin.length}")
        val final = thirdMin.edgeList.map { it.sequence }.flatten()
        println(final.string())
        return final.size
    }

    companion object {
        val START = Symbol.ACTIVATE
        val END = Symbol.ACTIVATE

        private val exporter = DOTExporter<SequenceStepVertex, SequenceEdge>()

        init {
            exporter.setVertexAttributeProvider { v -> mapOf("label" to DefaultAttribute(v.toString(), AttributeType.STRING)) }
            exporter.setEdgeAttributeProvider { e -> mapOf("label" to DefaultAttribute(e.sequence.string(), AttributeType.STRING)) }
        }
    }

}