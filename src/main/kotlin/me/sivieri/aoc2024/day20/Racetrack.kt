package me.sivieri.aoc2024.day20

import me.sivieri.aoc2024.common.Coordinate2
import org.jgrapht.alg.shortestpath.DijkstraShortestPath
import org.jgrapht.graph.DefaultEdge
import org.jgrapht.graph.SimpleDirectedGraph

class Racetrack(data: String) {

    private val map = data
        .split("\n")
        .filter { it.isNotBlank() }
        .map { it.toCharArray().toTypedArray() }
        .toTypedArray()
    private val maxX = map[0].size
    private val maxY = map.size
    private val graph = SimpleDirectedGraph<Coordinate2, DefaultEdge>(DefaultEdge::class.java)
    private val start: Coordinate2
    private val end: Coordinate2

    init {
        var s = Coordinate2.ORIGIN
        var e = Coordinate2.ORIGIN
        (0 until maxX).forEach { x ->
            (0 until maxY).forEach { y ->
                graph.addVertex(Coordinate2(x, y))
                if (map[y][x] == START) s = Coordinate2(x, y)
                if (map[y][x] == END) e = Coordinate2(x, y)
            }
        }
        start = s
        end = e
        (0 until maxX).forEach { x ->
            (0 until maxY).forEach { y ->
                val c = Coordinate2(x, y)
                Coordinate2(x, y)
                    .neighbors { it.isValid() && it.isNotWall() }
                    .forEach { graph.addEdge(c, it) }
            }
        }
    }

    fun countCheats(limit: Int): Int {
        val sp = DijkstraShortestPath(graph)
        val maxLength = sp.getPath(start, end).length
        val allWalls = (1 until maxX - 1).flatMap { x ->
            (1 until maxY - 1).mapNotNull { y ->
                if (map[y][x] == WALL) Coordinate2(x, y)
                else null
            }
        }
        val cheatsLength = allWalls.mapIndexed { index, c ->
            println("$index / ${allWalls.size}")
            graph.addVertex(c)
            val n = c.neighbors { it.isValid() && it.isNotWall() }
            n.forEach { graph.addEdge(it, c); graph.addEdge(c, it) }
            sp.getPath(start, end).length.also {
                n.forEach { graph.removeEdge(it, c); graph.removeEdge(c, it) }
                graph.removeVertex(c)
            }
        }
        return cheatsLength.filter { it < maxLength && (maxLength - it) >= limit }.size
    }

    private fun Coordinate2.isValid(): Boolean =
        this.x in 0 until maxX &&
            this.y in 0 until maxY

    private fun Coordinate2.isNotWall(): Boolean = map[this.y][this.x] != WALL

    companion object {
        private const val EMPTY = '.'
        private const val WALL = '#'
        private const val START = 'S'
        private const val END = 'E'
    }

}