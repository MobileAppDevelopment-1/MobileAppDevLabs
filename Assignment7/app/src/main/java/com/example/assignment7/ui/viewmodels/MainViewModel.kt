package com.example.assignment7.ui.viewmodels

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.assignment7.db.models.Person
import com.example.assignment7.repositories.PersonRepository
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {
    private val _personList: MutableLiveData<MutableList<Person>> = MutableLiveData(mutableListOf())
    val personList: LiveData<MutableList<Person>> = _personList

    fun loadData(context: Context) {
        viewModelScope.launch {
            _personList.postValue(PersonRepository.getPersonList(context) as MutableList<Person>)
        }
    }

    fun deletePerson(context: Context, person: Person) {
        viewModelScope.launch {
            Log.d("Person", "Deleting person with id ${person.id}")
            PersonRepository.deletePerson(context, person)
        }
    }
}
