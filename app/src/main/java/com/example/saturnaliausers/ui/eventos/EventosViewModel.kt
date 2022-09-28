package com.example.saturnaliausers.ui.eventos

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.saturnaliausers.data.EventosRepository
import com.example.saturnaliausers.data.ResourceRemote
import com.example.saturnaliausers.model.Eventos
import com.google.firebase.firestore.ktx.toObject
import kotlinx.coroutines.launch

class EventosViewModel : ViewModel() {

    private val eventosRepository = EventosRepository()
    private var eventosList: ArrayList<Eventos> = ArrayList()

    private val _showMsg: MutableLiveData<String?> = MutableLiveData()
    val showMsg: LiveData<String?> = _showMsg

    private val _eventoList: MutableLiveData<ArrayList<Eventos>> = MutableLiveData()
    val eventoList: LiveData<ArrayList<Eventos>> = _eventoList

    fun loadEventos(discoId: String) {
        viewModelScope.launch {
            var result = eventosRepository.searchEvento(discoId)
            result.let { resourceRemote ->
                when (resourceRemote){
                    is ResourceRemote.Success -> {
                        result.data?.documents?.forEach{ document ->
                            val evento = document.toObject<Eventos>()
                            evento?.let { eventosList.add(evento) }
                        }
                        _eventoList.postValue(eventosList)
                    }
                    is ResourceRemote.Error ->{
                        val msg = result.message
                        _showMsg.postValue(msg)
                    }
                    else ->{
                        //dont use
                    }
                }
            }
        }
    }

}