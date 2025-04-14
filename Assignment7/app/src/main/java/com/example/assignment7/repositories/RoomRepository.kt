package com.example.assignment7.repositories

import android.content.Context
import androidx.room.Room
import com.example.assignment7.db.AppDatabase

object RoomRepository {
    fun getRoomInstance(context: Context): AppDatabase {
        return Room.databaseBuilder(
            context,
            AppDatabase::class.java, "test-db"
        )
            .build()
    }
}
