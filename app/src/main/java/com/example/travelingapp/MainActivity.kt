package com.example.travelingapp

import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity() {


    class Salesman{
        public var logcat = ""
        private fun <T>permutations(list: List<T>)  = sequence<List<T>> {
            val factorials = IntArray(list.size + 1)
            factorials[0] = 1
            for (i in 1..list.size) {
                factorials[i] = factorials[i - 1] * i
            }
            for (i in 0 until factorials[list.size]) {
                var onePermutation = mutableListOf<T>()
                var temp = list
                var positionCode = i
                for (position in list.size downTo 1) {
                    val selected = positionCode / factorials[position - 1]
                    onePermutation += temp[selected]
                    positionCode %= factorials[position - 1]
                    temp = list.subList(0, selected) + temp.subList(selected + 1,temp.size);
                }
                yield(onePermutation)
            }
        }
        var graph:Graph;

        constructor(graph: Graph){
            this.graph = graph
        }

        fun calculatePath(permutation: List<Int>): Int? {

            var currentPath = 0
            for (i in 1 until permutation.size){

                val n1 = permutation[i-1]
                val n2 = permutation[i]

                val edge = graph.getEdge(n1,n2) ?: return null

                currentPath +=  edge.c

            }
            return currentPath
        }

        fun solve(): Pair<List<Int>, Int>? {
            val permutations = permutations((0 until graph.nodeCount).toList() )

            var ans = Pair(listOf<Int>(),Int.MAX_VALUE)

            for (permutation in permutations){
                Log.d("TRY",permutation.toString())
                logcat = logcat + permutation.toString() + "\n"


                var currentPath = calculatePath(permutation) ?: continue
                if (currentPath <  ans.second) {
                    ans = Pair(permutation,currentPath)
                }
            }
            if (ans.second == Int.MAX_VALUE){
                return null
            }
            return ans
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val calcBNT: Button = findViewById(R.id.calculate)
        val logTXT: TextView = findViewById(R.id.logText)
        val finalTXT: TextView = findViewById(R.id.text2137)


        calcBNT.setOnClickListener{
            var graph = Graph()
            var algo = Salesman(graph)
            var path = algo.solve()
            logTXT.text = algo.logcat
            Log.d("ans",path.toString())
            finalTXT.text = path.toString()
        }

    }
}