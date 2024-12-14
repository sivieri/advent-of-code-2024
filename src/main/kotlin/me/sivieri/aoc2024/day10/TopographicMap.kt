package me.sivieri.aoc2024.day10

import me.sivieri.aoc2024.common.Coordinate2
import me.sivieri.aoc2024.common.Coordinate2Payload
import org.jgrapht.alg.connectivity.ConnectivityInspector
import org.jgrapht.graph.DefaultEdge
import org.jgrapht.graph.SimpleDirectedGraph

class TopographicMap(data: String) {

    private val map = data
        .split("\n")
        .filter { it.isNotBlank() }
        .map { it.toCharArray().toTypedArray() }
        .toTypedArray()
    private val maxX = map[0].size
    private val maxY = map.size
    private val graph = SimpleDirectedGraph<Coordinate2Payload<Int>, DefaultEdge>(DefaultEdge::class.java)

    init {
        // nodes
        (0 until maxY).forEach { y ->
            (0 until maxX).forEach { x ->
                val value = map[y][x]
                if (value in '0' .. '9') graph.addVertex(Coordinate2Payload(x, y, value.digitToInt()))
            }
        }
        // edges
        (0 until maxY).forEach { y ->
            (0 until maxX).forEach { x ->
                val value = map[y][x]
                if (value in '0' .. '9'){
                    val p = Coordinate2Payload(x, y, value.digitToInt())
                    p
                        .toCoordinate2()
                        .neighbors()
                        .mapNotNull {
                            val v = map[it.y][it.x]
                            if (v in '0' .. '9') Coordinate2Payload(it.x, it.y, map[it.y][it.x].digitToInt())
                            else null
                        }
                        .filter { it.payload == p.payload + 1 }
                        .forEach { n -> graph.addEdge(p, n) }
                }
            }
        }
    }

    private fun Coordinate2.neighbors(): List<Coordinate2> = listOf(
        Coordinate2(this.x - 1, this.y),
        Coordinate2(this.x + 1, this.y),
        Coordinate2(this.x, this.y - 1),
        Coordinate2(this.x, this.y + 1)
    ).filter { it.isValid() }

    private fun Coordinate2.isValid(): Boolean =
        this.x in 0 until maxX &&
            this.y in 0 until maxY

    fun sumTrailheadScores(): Int {
        val inspector = ConnectivityInspector(graph)
        val zeros = graph.vertexSet().filter { it.payload == 0 }
        val nines = graph.vertexSet().filter { it.payload == 9 }
        return zeros.sumOf { zero ->
            nines.filter { nine ->
                inspector.pathExists(zero, nine)
            }.size
        }
    }

}