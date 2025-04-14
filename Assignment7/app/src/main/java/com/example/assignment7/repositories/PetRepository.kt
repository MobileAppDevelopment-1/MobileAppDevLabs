package com.example.assignment7.repositories

import android.content.Context
import com.example.assignment7.db.models.Pet

object PetRepository {
    suspend fun getPetList(context: Context): List<Pet> {
        return RoomRepository
            .getRoomInstance(context)
            .petDao()
            .getAll()
    }

    suspend fun getPetById(context: Context, id: Int): Pet {
        return RoomRepository
            .getRoomInstance(context)
            .petDao()
            .getById(id)
    }

    suspend fun insertPet(context: Context, pet: Pet): Long {
        return RoomRepository
            .getRoomInstance(context)
            .petDao()
            .insertPet(pet)
    }

    private suspend fun updatePet(context: Context, pet: Pet) {
        return RoomRepository
            .getRoomInstance(context)
            .petDao()
            .updatePet(pet)
    }

    suspend fun deletePet(context: Context, pet: Pet) {
        return RoomRepository
            .getRoomInstance(context)
            .petDao()
            .deletePet(pet)
    }
}
