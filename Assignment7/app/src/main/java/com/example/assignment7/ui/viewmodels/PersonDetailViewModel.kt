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

class PersonDetailViewModel : ViewModel() {
    private val _person: MutableLiveData<Person> = MutableLiveData(null)
    val person: LiveData<Person> = _person
    private val _hasErrorSaving: MutableLiveData<Boolean> = MutableLiveData(false)
    val hasErrorSaving: LiveData<Boolean> = _hasErrorSaving

    private val _personSaved = MutableLiveData<Person>(null)
    val personSaved: LiveData<Person> = _personSaved

    fun loadPerson(context: Context, id: Int) {
        viewModelScope.launch {
            _person.postValue(PersonRepository.getPersonById(context, id))
        }
    }

    fun savePerson(context: Context, person: Person) {
        viewModelScope.launch {
            try {
                val id = PersonRepository.savePerson(context, person)
                person.id = id
                _personSaved.postValue(person)
            } catch (e: Exception) {
                e.printStackTrace()
                _hasErrorSaving.postValue(true)
            }
        }
    }
}
