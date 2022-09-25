package com.example.saturnaliausers.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.saturnaliausers.data.HomeRepository
import com.example.saturnaliausers.data.ResourceRemote
import com.example.saturnaliausers.model.Home
import com.google.firebase.firestore.ktx.toObject
import kotlinx.coroutines.launch

class HomeViewModel : ViewModel() {

    private val homeRepository = HomeRepository()
    private var homeList: ArrayList<Home> = ArrayList()

    private val _showMsg: MutableLiveData<String?> = MutableLiveData()
    val showMsg: LiveData<String?> = _showMsg

    private val _recomendadoList: MutableLiveData<ArrayList<Home>> = MutableLiveData()
    val recomendadoList: LiveData<ArrayList<Home>> = _recomendadoList

    fun loadRecomendados() {
        viewModelScope.launch {
            var result = homeRepository.searchRecomendados()
            result.let { resourceRemote ->
                when (resourceRemote){
                    is ResourceRemote.Success -> {
                        result.data?.documents?.forEach{ document ->
                            val recomendado = document.toObject<Home>()
                            recomendado?.let { homeList.add(recomendado) }
                        }
                        _recomendadoList.postValue(homeList)
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