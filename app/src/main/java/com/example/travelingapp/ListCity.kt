package com.example.travelingapp

import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class ListCity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.listcity)

        val backButton: Button = findViewById(R.id.button)
        val saveButton: Button = findViewById<Button>(R.id.button3)

        backButton.setOnClickListener(){

        }

        saveButton.setOnClickListener(){
            //TODO: saving list of cities (minimum 6 max 8) after click and back or throw error in log/communicate
        }

    }
}