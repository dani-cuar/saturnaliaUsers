package com.example.saturnaliausers

import android.app.Application
import androidx.room.Room
import com.example.saturnaliausers.local.DiscoDatabase

class saturnaliaUsers : Application() {

    companion object{
        lateinit var database: DiscoDatabase
    }

    override fun onCreate() {
        super.onCreate()

        database = Room.databaseBuilder(
            this,
            DiscoDatabase::class.java,
            "disco_db"
        ).build()
    }
}