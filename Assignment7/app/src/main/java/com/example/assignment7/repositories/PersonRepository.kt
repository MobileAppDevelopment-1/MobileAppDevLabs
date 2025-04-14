package com.example.assignment7.repositories

import android.content.Context
import com.example.assignment7.db.models.Person

object PersonRepository {
    suspend fun getPersonList(context: Context): List<Person> {
        return RoomRepository
            .getRoomInstance(context)
            .personDao()
            .getAll()
    }

    suspend fun getPersonById(context: Context, id: Int): Person {
        return RoomRepository
            .getRoomInstance(context)
            .personDao()
            .getById(id)
    }

    private suspend fun insertPerson(context: Context, person: Person): Long {
        return RoomRepository
            .getRoomInstance(context)
            .personDao()
            .insertPerson(person)
    }

    suspend fun savePerson(context: Context, person: Person): Int {
        if (person.id == 0) {
            return insertPerson(context, person).toInt()
        } else {
            updatePerson(context, person)
            return person.id
        }
    }

    private suspend fun updatePerson(context: Context, person: Person) {
        return RoomRepository
            .getRoomInstance(context)
            .personDao()
            .updatePerson(person)
    }

    suspend fun deletePerson(context: Context, person: Person) {
        RoomRepository
            .getRoomInstance(context)
            .personDao()
            .deletePerson(person)
    }
}
