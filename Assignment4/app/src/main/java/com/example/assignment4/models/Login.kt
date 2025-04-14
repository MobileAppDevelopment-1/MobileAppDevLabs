package com.example.assignment4.models

class Login {
    fun validateCredentials(username: String, password: String): Boolean {
        return (username == "admin" && password == "admin") ||
                (username == "balu" && password == "balu123")
    }
}
