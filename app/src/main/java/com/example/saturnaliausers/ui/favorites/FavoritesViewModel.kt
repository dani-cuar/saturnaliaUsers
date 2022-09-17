package com.example.saturnaliausers.ui.favorites

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.saturnaliausers.local.LocalDisco
import com.example.saturnaliausers.local.repository.LocalDiscoRepository
import com.example.saturnaliausers.model.Disco
import kotlinx.coroutines.launch

class FavoritesViewModel : ViewModel() {

    var localDiscoRepository = LocalDiscoRepository()

    private val _discosLoaded : MutableLiveData<ArrayList<LocalDisco>> = MutableLiveData()
    val discosLoaded: LiveData<ArrayList<LocalDisco>> = _discosLoaded

    fun loadDiscos() {
        viewModelScope.launch {
            val listFavoritesDiscos = localDiscoRepository.getDisco()
            _discosLoaded.postValue(listFavoritesDiscos as ArrayList<LocalDisco>)
        }
    }

    fun deleteDisco(localDisco: LocalDisco) {
        viewModelScope.launch {
            localDiscoRepository.deleteDisco(localDisco)
        }
    }
}