package com.example.android_kotlin

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val text:TextView = findViewById(R.id.text)
        val button = findViewById<Button>(R.id.button)

        button.setOnClickListener {
            (text).setText(R.string.kotlin)
        }

    }
}