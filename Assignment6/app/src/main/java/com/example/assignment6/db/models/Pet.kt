package com.example.assignment6.db.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "pet")
data class Pet(
    var name: String,
    var type: String,
    @ColumnInfo(name = "person_id")
    var personId: Int
) {
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
}
