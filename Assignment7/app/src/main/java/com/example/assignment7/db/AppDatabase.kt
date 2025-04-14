package com.example.assignment7.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.assignment7.db.dao.PersonDao
import com.example.assignment7.db.dao.PetDao
import com.example.assignment7.db.models.Person
import com.example.assignment7.db.models.Pet

@Database(
    entities = [Person::class, Pet::class],
    version = 2
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun personDao(): PersonDao

    abstract fun petDao(): PetDao
}
