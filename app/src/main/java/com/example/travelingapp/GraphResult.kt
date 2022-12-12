
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
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.core.view.drawToBitmap
import androidx.fragment.app.Fragment
import java.text.SimpleDateFormat
import java.util.*


@Suppress("DEPRECATION")
class GraphResult : Fragment() {

    companion object {
        var ans : Pair<List<Int>,Double>? = null
    }

    lateinit var ansTextView : TextView
    lateinit var backButton : Button
    lateinit var saveButton : Button

    private fun initializeComponents(view: View){

        with(view){

            ansTextView = findViewById(R.id.ansTextView)
            backButton = findViewById(R.id.backButton)
            saveButton = findViewById(R.id.saveButton)


            backButton.setOnClickListener {

                parentFragmentManager.beginTransaction().replace(R.id.fragmentContainer,GraphCreation()).commit()

            }

            saveButton.setOnClickListener {

                val bitmap = requireActivity().window.decorView.rootView.drawToBitmap()

                val sdf = SimpleDateFormat("yyyyMMdd_HHmmss", Locale.getDefault())
                val date: String = sdf.format(Date())

                MediaStore.Images.Media.insertImage(context.getContentResolver(), bitmap, date+".jpg" , "");

            }


        }



    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        with(inflater.inflate(R.layout.fragment_graph_result, container, false)){

            initializeComponents(this)


            ansTextView.text = ans.toString()

            return this
        }





    }

}