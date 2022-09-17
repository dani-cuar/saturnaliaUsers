package com.example.saturnaliausers.local.repository

import com.example.saturnaliausers.local.DiscoDao
import com.example.saturnaliausers.local.LocalDisco
import com.example.saturnaliausers.saturnaliaUsers

class LocalDiscoRepository {

    suspend fun saveDisco(localDisco: LocalDisco) {
        val discoDao : DiscoDao = saturnaliaUsers.database.DiscoDao()
        discoDao.createDisco(localDisco)
    }

    suspend fun getDisco() =saturnaliaUsers.database.DiscoDao().getDisco()

    suspend fun deleteDisco(localDisco: LocalDisco) {
        val discoDao : DiscoDao = saturnaliaUsers.database.DiscoDao()
        discoDao.deleteDisco(localDisco)
    }

    suspend fun searchDisco(id: String?) = saturnaliaUsers.database.DiscoDao().searchDisco(id)

}