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



/*
TODO:
   *Working generating sample data [*]
   *TSP brute-force alpha implement [*]
   *TSP mathematical optimalization [*]
   *Getting data of cities and distances from editable menu (ListCity.kt) [*]
   *Getting data from outside service about distance between every city [-]  (No time, thank you fucking school shit)
   *UI/UX design and code comment/optimalization [-]

 */

package com.example.travelingapp

import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentContainerView


const val CITY_LIMIT = 8

class MainActivity : AppCompatActivity(){
        lateinit var fragmentContainer: FragmentContainerView
    private fun initializeComponents(){
        fragmentContainer = findViewById(R.id.fragmentContainer)

    }

    @RequiresApi(Build.VERSION_CODES.Q)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initializeComponents()

        Log.d("source id",fragmentContainer.id.toString())

    }




}