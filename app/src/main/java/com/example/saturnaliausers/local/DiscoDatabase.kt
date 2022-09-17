package com.example.saturnaliausers.local

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [LocalDisco::class], version = 1)
abstract  class DiscoDatabase : RoomDatabase() {

    abstract fun DiscoDao(): DiscoDao
}