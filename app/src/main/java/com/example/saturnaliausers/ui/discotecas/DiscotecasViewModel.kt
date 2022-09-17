package com.example.saturnaliausers.ui.discotecas

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.saturnaliausers.model.Disco
import kotlinx.coroutines.launch

class DiscotecasViewModel : ViewModel() {


    fun addDiscoToFavorites(disco: Disco) {
        val localDisco = disco?.id?.let {

        }
        viewModelScope.launch {
            localDisco?.let {  }
        }
    }

}