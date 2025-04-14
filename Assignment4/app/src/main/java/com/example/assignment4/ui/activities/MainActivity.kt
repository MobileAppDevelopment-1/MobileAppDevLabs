package com.example.assignment4.ui.activities

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.assignment4.R
import com.example.assignment4.databinding.ActivityMainBinding
import com.example.assignment4.ui.viewmodels.MainViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        setupEventListeners()
        setupViewModelObservers()
    }

    private fun setupEventListeners() {
        binding.loginBtn.setOnClickListener {
            val username = binding.usernameTxt.text.toString()
            val password = binding.passwordTxt.text.toString()

            viewModel.login(username, password)
        }
    }

    private fun setupViewModelObservers() {
        viewModel.loginResult.observe(this) {
            if (it.isNotEmpty()) {
                val intent = Intent(this, SecondActivity::class.java).apply {
                    putExtra("username", it)
                }
                startActivity(intent)
            } else {
                binding.usernameTxt.text.clear()
                binding.passwordTxt.text.clear()
            }
        }

        viewModel.showError.observe(this) {
            if (it) {
                Toast.makeText(
                    this,
                    getString(R.string.invalid_user_msg),
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }
}
