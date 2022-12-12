

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
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TableLayout
import android.widget.TableRow
import android.widget.TextView
import android.widget.Toast
import com.example.travelingapp.Graph
import com.example.travelingapp.Vertex
import com.google.android.material.textfield.TextInputEditText
import kotlin.random.Random

data class City(val name: String, val lat: Double, val long:Double, val view:TableRow)

class GraphCreation : Fragment() {

    private lateinit var nameInput: TextInputEditText
    private lateinit var latInput: TextInputEditText
    private lateinit var longInput: TextInputEditText
    private lateinit var addButton: Button
    private lateinit var randomizeButton: Button
    private lateinit var calculatePathButton: Button
    private lateinit var cityTable: TableLayout

    private var cities = mutableListOf<City>()

    private fun initializeComponents(view:View){

        with(view){
            nameInput = findViewById(R.id.nameInput)
            latInput = findViewById(R.id.latInput)
            longInput = findViewById(R.id.longInput)
            addButton = findViewById(R.id.addButton)
            randomizeButton = findViewById(R.id.randomizeButton)
            calculatePathButton = findViewById(R.id.calculatePathButton)
            cityTable = findViewById(R.id.cityTable)
        }

    }

    private fun addCity(name:String, lat:Double,long:Double){

        val cityRow = TableRow(context)
        cityRow.addView(TextView(context).apply { text = name;gravity=1 })
        cityRow.addView(TextView(context).apply { text = lat.toString();gravity=1 })
        cityRow.addView(TextView(context).apply { text = long.toString();gravity=1 })

        cities.add(City(name,lat,long,cityRow))
        cityTable.addView(cityRow)
    }

    private fun clearCities(){
        cities.forEach{
            cityTable.removeView(it.view)
        }
    }

    private fun errorPopup(message:String){
        //TODO: this aint workin for some reason
        // Im suspecting the context is fucked
        Toast.makeText(context ,message,Toast.LENGTH_LONG)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        with(inflater.inflate(R.layout.fragment_graph_creation, container, false)){

            initializeComponents(this)

            addButton.setOnClickListener {

                if(cities.size >= CITY_LIMIT){
                    errorPopup("City limit is %s".format(CITY_LIMIT))
                    return@setOnClickListener
                }

                val name = nameInput.text.toString()
                if(name.isEmpty()){
                    errorPopup("Invalid name")
                    return@setOnClickListener
                }

                val lat = latInput.text.toString().toDoubleOrNull()
                if(lat == null){
                    errorPopup("Invalid latitude")
                    return@setOnClickListener
                }
                val long = longInput.text.toString().toDoubleOrNull()
                if(long == null){
                    errorPopup("Invalid longitude")
                    return@setOnClickListener
                }

                addCity(nameInput.text.toString(), lat, long)

            }

            randomizeButton.setOnClickListener {

                clearCities()
                for ( i in 0 until CITY_LIMIT){
                    addCity(i.toString(),Random.nextInt(10).toDouble(),Random.nextInt(10).toDouble())
                }


            }

            calculatePathButton.setOnClickListener {

                if (cities.size < 3){
                    errorPopup("Not enough cities")
                }

                val graph = Graph(cities.map { Vertex(it.lat,it.lat) })

                GraphCalculation.graph = graph
                parentFragmentManager.beginTransaction().replace(R.id.fragmentContainer,GraphCalculation()).commit()

            }


            return this
        }
    }
}