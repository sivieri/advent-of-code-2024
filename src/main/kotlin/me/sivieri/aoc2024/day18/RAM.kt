package me.sivieri.aoc2024.day18

import me.sivieri.aoc2024.common.Coordinate2
import org.jgrapht.alg.shortestpath.DijkstraShortestPath
import org.jgrapht.graph.DefaultEdge
import org.jgrapht.graph.SimpleDirectedGraph

class RAM(
    data: List<String>,
    private val maxX: Int,
    private val maxY: Int
) {
    private val bytes: List<Coordinate2> = data.map {
        val (x, y) = it.split(",")
        Coordinate2(x.toInt(), y.toInt())
    }

    fun findShortestLength(n: Int): Int {
        val subbytes = bytes.subList(0, n)
        val graph = SimpleDirectedGraph<Coordinate2, DefaultEdge>(DefaultEdge::class.java)
        (0 until maxX).forEach { x ->
            (0 until maxY).forEach { y ->
                graph.addVertex(Coordinate2(x, y))
            }
        }
        (0 until maxX).forEach { x ->
            (0 until maxY).forEach { y ->
                val c = Coordinate2(x, y)
                Coordinate2(x, y)
                    .neighbors { it.isValid() }
                    .filterNot { subbytes.contains(it) }
                    .forEach { graph.addEdge(c, it) }
            }
        }
        val sp = DijkstraShortestPath(graph)
        return sp.getPath(Coordinate2(0, 0), Coordinate2(maxX - 1, maxY - 1)).length
    }

    fun findBlockingPath(n: Int): Coordinate2 {
        (n until bytes.size).forEach { i ->
            println("Round $i")
            try {
                val subbytes = bytes.subList(0, i)
                val graph = SimpleDirectedGraph<Coordinate2, DefaultEdge>(DefaultEdge::class.java)
                (0 until maxX).forEach { x ->
                    (0 until maxY).forEach { y ->
                        graph.addVertex(Coordinate2(x, y))
                    }
                }
                (0 until maxX).forEach { x ->
                    (0 until maxY).forEach { y ->
                        val c = Coordinate2(x, y)
                        Coordinate2(x, y)
                            .neighbors { it.isValid() }
                            .filterNot { subbytes.contains(it) }
                            .forEach { graph.addEdge(c, it) }
                    }
                }
                val sp = DijkstraShortestPath(graph)
                sp.getPath(Coordinate2(0, 0), Coordinate2(maxX - 1, maxY - 1)).length
            } catch (e: Exception) {
                return bytes[i - 1]
            }
        }
        throw IllegalStateException("Unknown status")
    }

    private fun Coordinate2.isValid(): Boolean =
        this.x in 0 until maxX &&
        this.y in 0 until maxY

}