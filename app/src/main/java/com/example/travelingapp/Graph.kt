package com.example.travelingapp

import android.util.Log
import kotlin.random.Random

data class Edge(val n1:Int,val n2: Int, val c:Int)
data class Vertex(val n: Int, val c:Int)

class Graph(val nodeCount: Int,val edgeCount: Int) {
    private lateinit var adjacencyList: List<List<Vertex>>
    lateinit var edges: List<Edge>

    fun getEdge(n1:Int,n2:Int): Vertex? {
        return if (n1<n2){
            adjacencyList[n1].find { it.n == n2 }
        }else {
            adjacencyList[n2].find { it.n == n1 }
        }
    }

    constructor(nodeCount: Int = 8, edgeCount: Int = 50, maxEdgeCost: Int = 10) : this(nodeCount,edgeCount) {

        val mutableAL : List<MutableList<Vertex>> = List(nodeCount) { mutableListOf() }
        val mutableEdges = MutableList(edgeCount) { Edge(0, 0, 0) }

        for(i in 0 until edgeCount){

            val n1 = Random.nextInt(nodeCount)
            val r = Random.nextInt(nodeCount - 1)

            val c = Random.nextInt(1,maxEdgeCost)

            // assure that the 2 random numbers are not the same
            // always saving edges that lead smaller to bigger
            if (n1 > r){
                mutableAL[r].add(Vertex(n1,c))
            } else {
                mutableAL[n1].add(Vertex(r+1,c))
            }

        }

        adjacencyList = mutableAL
        edges = mutableEdges
    }


}