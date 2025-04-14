package com.example.assignment1

import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {

    private lateinit var btnWelcomeApp: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        btnWelcomeApp = findViewById(R.id.btnWelcomeApp)
        setupEventListeners()
    }

    private fun setupEventListeners() {
        btnWelcomeApp.setOnClickListener {
            Toast.makeText(
                this,
                getString(R.string.hello_world_msg),
                Toast.LENGTH_LONG
            ).show()
        }
    }
}
