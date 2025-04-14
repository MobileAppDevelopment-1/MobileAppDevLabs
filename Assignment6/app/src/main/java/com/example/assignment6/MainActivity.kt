package com.example.assignment6

import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.room.Room
import com.example.assignment6.db.AppDatabase
import com.example.assignment6.db.models.Person
import com.example.assignment6.db.models.Pet

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val db = Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java, "test_db"
        ).allowMainThreadQueries()
            .build()

        val personDao = db.personDao()
        personDao.insertPerson(
            Person(
                "Juan",
                "Perez",
                25,
                "123456789",
                "juan@test.com"
            )
        )

        personDao.insertPerson(
            Person(
                "Xavier",
                "Quiroga",
                40,
                "12123434",
                "xavier@test.com"
            )
        )

        personDao.insertPerson(
            Person(
                "Raul",
                "Sanchez",
                70,
                "676734",
                "raul@test.com"
            )
        )

        val person = personDao.getById(1)
        Log.d("PERSON", person.toString())

        val petDao = db.petDao()
        petDao.insertPet(
            Pet(
                "Huesos",
                "Dog",
                1
            )
        )

        petDao.insertPet(
            Pet(
                "Loki",
                "Cat",
                1
            )
        )

        petDao.insertPet(
            Pet(
                "Bobby",
                "Dog",
                2
            )
        )

        petDao.insertPet(
            Pet(
                "Dobby",
                "Dog",
                3
            )
        )

        val pets = petDao.getByPersonId(1)
        Log.d("PETS 1", "Mascotas de Juan: $pets")

        val pets2 = petDao.getByPersonId(2)
        Log.d("PETS 2", "Mascotas de Xavier: $pets2")

        val pets3 = petDao.getByPersonId(3)
        Log.d("PETS 3", "Mascotas de Raul: $pets3")
    }
}
