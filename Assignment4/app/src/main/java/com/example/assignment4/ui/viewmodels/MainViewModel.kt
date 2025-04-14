package com.example.assignment4.ui.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.assignment4.models.Login

class MainViewModel : ViewModel() {
    private val _loginResult: MutableLiveData<String> = MutableLiveData("")
    val loginResult: LiveData<String> = _loginResult

    private val _showError: MutableLiveData<Boolean> = MutableLiveData(false)
    val showError: LiveData<Boolean> = _showError

    private val login = Login()

    fun login(username: String, password: String) {
        if (login.validateCredentials(username, password)) {
            _loginResult.value = username
        } else {
            _loginResult.value = ""
            _showError.value = true
        }
    }
}
