package com.example.assignment4.ui.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class SecondViewModel : ViewModel() {
    private val _welcomeMessage: MutableLiveData<String> = MutableLiveData("")
    val welcomeMessage: LiveData<String> = _welcomeMessage

    fun setUsername(username: String) {
        _welcomeMessage.value = "Bienvenido, $username!"
    }
}
