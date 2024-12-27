package me.sivieri.aoc2024.day16

import me.sivieri.aoc2024.common.Coordinate2
import me.sivieri.aoc2024.common.Direction
import org.jgrapht.alg.shortestpath.DijkstraShortestPath
import org.jgrapht.graph.DefaultEdge
import org.jgrapht.graph.DefaultWeightedEdge
import org.jgrapht.graph.SimpleDirectedWeightedGraph

class Maze(data: String) {

    private val graph = SimpleDirectedWeightedGraph<Tile, DefaultWeightedEdge>(DefaultWeightedEdge::class.java)
    private val map: Array<Array<Char>> = data
        .split("\n")
        .filter { it.isNotBlank() }
        .map { it.toCharArray().toTypedArray() }
        .toTypedArray()
    private val maxX: Int = map[0].size
    private val maxY: Int = map.size
    private var start: Tile = Tile(Coordinate2.ORIGIN, Direction.DOWN)
    private var ends: List<Tile> = emptyList()

    init {
        // nodes
        (0 until maxX).forEach { x ->
            (0 until maxY).forEach { y ->
                if (map[y][x] != WALL) {
                    val c = Coordinate2(x, y)
                    val up = Tile(c, Direction.UP)
                    val down = Tile(c, Direction.DOWN)
                    val left = Tile(c, Direction.LEFT)
                    val right = Tile(c, Direction.RIGHT)
                    graph.addVertex(up)
                    graph.addVertex(down)
                    graph.addVertex(left)
                    graph.addVertex(right)
                    val upRight = graph.addEdge(up, right)
                    val upLeft = graph.addEdge(up, left)
                    val rightUp = graph.addEdge(right, up)
                    val rightDown = graph.addEdge(right, down)
                    val downRight = graph.addEdge(down, right)
                    val downLeft = graph.addEdge(down, left)
                    val leftUp = graph.addEdge(left, up)
                    val leftDown = graph.addEdge(left, down)
                    graph.setEdgeWeight(upRight, ROTATE_WEIGHT)
                    graph.setEdgeWeight(upLeft, ROTATE_WEIGHT)
                    graph.setEdgeWeight(rightUp, ROTATE_WEIGHT)
                    graph.setEdgeWeight(rightDown, ROTATE_WEIGHT)
                    graph.setEdgeWeight(downRight, ROTATE_WEIGHT)
                    graph.setEdgeWeight(downLeft, ROTATE_WEIGHT)
                    graph.setEdgeWeight(leftUp, ROTATE_WEIGHT)
                    graph.setEdgeWeight(leftDown, ROTATE_WEIGHT)
                    if (map[y][x] == START) this.start = right
                    if (map[y][x] == END) this.ends = listOf(up, down, left, right)
                }
            }
        }
        // edges
        (0 until maxX).forEach { x ->
            (0 until maxY).forEach { y ->
                if (map[y][x] != WALL) {
                    val c = Coordinate2(x, y)
                    val up = Tile(c, Direction.UP)
                    val down = Tile(c, Direction.DOWN)
                    val left = Tile(c, Direction.LEFT)
                    val right = Tile(c, Direction.RIGHT)
                    val upper = Direction.UP.updateCoordinate(c)
                    val downer = Direction.DOWN.updateCoordinate(c)
                    val lefter = Direction.LEFT.updateCoordinate(c)
                    val righter = Direction.RIGHT.updateCoordinate(c)
                    if (upper.isValid() && upper.isNotWall()) {
                        val e = graph.addEdge(up, Tile(upper, Direction.UP))
                        graph.setEdgeWeight(e, MOVE_WEIGHT)
                    }
                    if (downer.isValid() && downer.isNotWall()) {
                        val e = graph.addEdge(down, Tile(downer, Direction.DOWN))
                        graph.setEdgeWeight(e, MOVE_WEIGHT)
                    }
                    if (lefter.isValid() && lefter.isNotWall()) {
                        val e = graph.addEdge(left, Tile(lefter, Direction.LEFT))
                        graph.setEdgeWeight(e, MOVE_WEIGHT)
                    }
                    if (righter.isValid() && righter.isNotWall()) {
                        val e = graph.addEdge(right, Tile(righter, Direction.RIGHT))
                        graph.setEdgeWeight(e, MOVE_WEIGHT)
                    }
                }
            }
        }
    }

    fun shortestPathCost(): Int {
        val sp = DijkstraShortestPath(graph)
        return ends.minOf {
            sp.getPathWeight(start, it).toInt()
        }
    }

    private fun Coordinate2.isValid(): Boolean =
        this.x in 0 until maxX &&
        this.y in 0 until maxY

    private fun Coordinate2.isNotWall(): Boolean = map[this.y][this.x] != WALL

    companion object {
        private const val WALL = '#'
        private const val EMPTY = '.'
        private const val START = 'S'
        private const val END = 'E'
        private const val MOVE_WEIGHT = 1.0
        private const val ROTATE_WEIGHT = 1000.0
    }

}