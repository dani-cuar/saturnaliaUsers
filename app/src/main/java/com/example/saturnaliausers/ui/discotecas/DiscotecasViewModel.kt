package com.example.saturnaliausers.ui.discotecas

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.saturnaliausers.local.LocalDisco
import com.example.saturnaliausers.local.repository.LocalDiscoRepository
import com.example.saturnaliausers.model.Disco
import kotlinx.coroutines.launch
import java.sql.Types.NULL

class DiscotecasViewModel : ViewModel() {

    val localDiscoRepository = LocalDiscoRepository()

    private val _discoExist : MutableLiveData<Boolean> = MutableLiveData()
    val discoExist: LiveData<Boolean> = _discoExist

    fun addDiscoToFavorites(disco: Disco) {
        val localDisco = LocalDisco(
            id= NULL,
            name = disco.nombre)

        viewModelScope.launch {
            localDiscoRepository.saveDisco(localDisco)
        }
    }

    fun searchDiscos(id: String?){
        var discoExistAux = false
        viewModelScope.launch {
            val localDisco = localDiscoRepository.searchDisco(id)
            if (localDisco != null)
                discoExistAux = true
            _discoExist.postValue(discoExistAux)
        }
    }
}

