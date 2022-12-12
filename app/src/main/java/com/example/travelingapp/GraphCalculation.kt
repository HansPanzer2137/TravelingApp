/*
    TSP implementation in Android Studio
        HUBERT TOPOLSKI 4PRT5 (https://github.com/HansPanzer2137)

        Special thanks for:
        Huber Wasilewski 4PRT5 (https://github.com/SNIAPA)
        Nikodem Mikucki 4PRT5 (https://github.com/miamilemon)
 */

/*

    This file is part of TravelingApp.

    TravelingApp is free software: you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation; either version 3 of the License, or
    (at your option) any later version.

    Foobar is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License
    along with Traveling.  If not, see <http://www.gnu.org/licenses/>.

 */


package com.example.travelingapp


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ProgressBar
import androidx.fragment.app.Fragment


class GraphCalculation : Fragment() {

    companion object {
        lateinit var graph: Graph
    }

    lateinit var progressBar: ProgressBar
    lateinit var cancelButton: Button
    lateinit var nextButton: Button

    lateinit var algorithm: Algorithm

    private fun initializeComponents(view: View){

        with(view){

            progressBar = findViewById(R.id.progressBar)
            cancelButton = findViewById(R.id.cancelButton)
            nextButton = findViewById(R.id.nextButton)

        }

    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        with(inflater.inflate(R.layout.fragment_graph_calculation, container, false)){

            initializeComponents(this)

            cancelButton.setOnClickListener {
                parentFragmentManager.beginTransaction().replace(R.id.fragmentContainer,GraphCreation()).commit()

            }


            algorithm = Algorithm(GraphCalculation.graph)


            algorithm.updateEvent.on {
                progressBar.progress = (it.progress.toDouble()/algorithm.permutationCount.toDouble() * progressBar.max.toDouble()).toInt()
            }

            (Thread{
                val ans = algorithm.solve()
                activity?.runOnUiThread {
                    nextButton.isEnabled = true

                    nextButton.setOnClickListener {
                        GraphResult.ans = ans
                        parentFragmentManager.beginTransaction().replace(R.id.fragmentContainer,GraphResult()).commit()
                    }

                }
            }).start()





            return this
        }
    }

}