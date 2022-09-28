package com.example.saturnaliausers.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.saturnaliausers.data.DiscoRepository
import com.example.saturnaliausers.data.EventosRepository
import com.example.saturnaliausers.data.HomeRepository
import com.example.saturnaliausers.data.ResourceRemote
import com.example.saturnaliausers.model.Disco
import com.example.saturnaliausers.model.Eventos
import com.example.saturnaliausers.model.Home
import com.google.firebase.firestore.ktx.toObject
import kotlinx.coroutines.launch

class HomeViewModel : ViewModel() {

    private val eventosRepository = EventosRepository()
    private val discoRepository = DiscoRepository()
    private var eventosList: ArrayList<Eventos> = ArrayList()
    private var discoList: ArrayList<Disco> = ArrayList()

    private val _showMsg: MutableLiveData<String?> = MutableLiveData()
    val showMsg: LiveData<String?> = _showMsg

    private val _eventRecomendadoList: MutableLiveData<ArrayList<Eventos>> = MutableLiveData()
    val eventRecomendadoList: LiveData<ArrayList<Eventos>> = _eventRecomendadoList

    private val _discosList: MutableLiveData<ArrayList<Disco>> = MutableLiveData()
    val discosList: LiveData<ArrayList<Disco>> = _discosList

    fun loadEventos() {
        viewModelScope.launch {
            var result = eventosRepository.loadEventRecomend()
            result.let { resourceRemote ->
                when (resourceRemote){
                    is ResourceRemote.Success -> {
                        result.data?.documents?.forEach{ document ->
                            val recomendado = document.toObject<Eventos>()
                            recomendado?.let { eventosList.add(recomendado) }
                        }
                        _eventRecomendadoList.postValue(eventosList)
                    }
                    is ResourceRemote.Error ->{
                        val msg= result.message
                        _showMsg.postValue(msg)
                    }
                    else -> {
                        //dont use
                    }
                }
            }
        }
    }

    fun loadDiscos() {
        viewModelScope.launch {
            discoList.clear()
            var result = discoRepository.searchDisco()
            result.let { resourceRemote ->
                when (resourceRemote){
                    is ResourceRemote.Success -> {
                        result.data?.documents?.forEach{ document ->
                            val disco = document.toObject<Disco>()
                            disco?.let { discoList.add(disco) }
                        }
                        _discosList.postValue(discoList)
                    }
                    is ResourceRemote.Error ->{
                        val msg= result.message
                        _showMsg.postValue(msg)
                    }
                    else -> {
                        //dont use
                    }
                }
            }
        }
    }
}
