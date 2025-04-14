package com.example.assignment6.db.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.assignment6.db.models.Pet

@Dao
interface PetDao {
    @Query("SELECT * FROM pet")
    fun getAll(): List<Pet>

    @Query("SELECT * FROM pet WHERE id = :id")
    fun getById(id: Int): Pet

    @Query("SELECT * FROM pet WHERE person_id = :personId")
    fun getByPersonId(personId: Int): List<Pet>

    @Insert
    fun insertPet(pet: Pet)

    @Update
    fun updatePet(pet: Pet)

    @Delete
    fun deletePet(pet: Pet)
}
