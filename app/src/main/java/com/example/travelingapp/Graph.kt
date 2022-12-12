package com.example.travelingapp


import kotlin.math.pow
import kotlin.math.sqrt
import kotlin.random.Random

data class Vertex(val x:Double, val y:Double)

class Graph {
    var vertices:List<Vertex>
    fun getEdge(n1:Int,n2:Int):Double {
        return sqrt (
            ((vertices[n1].x - vertices[n2].x)).pow(2) +
                    ((vertices[n1].y - vertices[n2].y)).pow(2)
        )
    }
    constructor(vertices:List<Vertex>){
        this.vertices = vertices
    }

    constructor(vertexCount: Int = 5, maxEdgeCost: Int = 10) {

        var vertices = mutableListOf<Vertex>()

        for(i in 0 until vertexCount){
            vertices.add(Vertex(Random.nextInt(maxEdgeCost).toDouble(),Random.nextInt(maxEdgeCost).toDouble()))
        }
        this.vertices = vertices



    }


}