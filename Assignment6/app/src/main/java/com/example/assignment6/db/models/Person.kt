package com.example.assignment6.db.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "person")
data class Person(
    var name: String,
    @ColumnInfo(name = "last_name")
    var lastName: String,
    var age: Int,
    var email: String,
    var phone: String
) {
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
}
