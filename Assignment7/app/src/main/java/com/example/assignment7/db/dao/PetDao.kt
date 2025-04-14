package com.example.assignment7.db.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.assignment7.db.models.Pet

@Dao
interface PetDao {
    @Query("SELECT * FROM pet")
    suspend fun getAll(): List<Pet>

    @Query("SELECT * FROM pet WHERE id = :id")
    suspend fun getById(id: Int): Pet

    @Insert
    suspend fun insertPet(pet: Pet): Long

    @Update
    suspend fun updatePet(pet: Pet)

    @Delete
    suspend fun deletePet(pet: Pet)
}
