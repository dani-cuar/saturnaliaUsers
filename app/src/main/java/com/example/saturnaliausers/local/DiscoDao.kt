package com.example.saturnaliausers.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface DiscoDao {

    @Insert
    suspend fun createDisco(disco: LocalDisco)

    @Query("SELECT * FROM table_disco")
    suspend fun getDisco(): MutableList<LocalDisco>

    @Delete
    suspend fun deleteDisco(localDisco: LocalDisco)

    @Query("SELECT * FROM table_disco WHERE id LIKE :id")
    suspend fun searchDisco(id: String?): LocalDisco
}