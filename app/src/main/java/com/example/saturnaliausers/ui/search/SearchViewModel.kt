package com.example.saturnaliausers.ui.search

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.saturnaliausers.data.DiscoRepository
import com.example.saturnaliausers.data.ResourceRemote
import com.example.saturnaliausers.model.Disco
import com.google.firebase.firestore.ktx.toObject
import kotlinx.coroutines.launch

class SearchViewModel : ViewModel() {

    private val discoRepository= DiscoRepository()

    private var discoList: ArrayList<Disco> = ArrayList()

    private val _showMsg: MutableLiveData<String?> = MutableLiveData()
    val showMsg: LiveData<String?> = _showMsg

    private val _discosList: MutableLiveData<ArrayList<Disco>> = MutableLiveData()
    val discosList: LiveData<ArrayList<Disco>> = _discosList

    private val _discoData:MutableLiveData<String> = MutableLiveData()
    val discoData: LiveData<String> = _discoData

    fun validateData(discotecaName: String) {
        if (discotecaName.isEmpty())
            _showMsg.value = "Debe digitar el nombre de la discoteca"
        else{
            viewModelScope.launch {
                val result = discoRepository.searchDisco()
                result.let { resourceRemote ->
                    when (resourceRemote) {
                        is ResourceRemote.Success -> {
                            var existDisco = false
                            result.data?.documents?.forEach { document ->
                                val disco = document.toObject<Disco>()
                                if (discotecaName == disco?.nombre) {
                                    _discoData.postValue("Nombre: " + disco.nombre)
                                    existDisco = true
                                }
                            }
                            if (!existDisco) {
                                _discoData.postValue("Discoteca no encontrada")
                            }
                        }
                        is ResourceRemote.Error -> {
                            val msg = result.message
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
    fun loadDisco(){
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


