package com.example.saturnaliausers.ui.carta

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.saturnaliausers.data.CartaRepository
import com.example.saturnaliausers.data.ResourceRemote
import com.example.saturnaliausers.model.Carta
import com.google.firebase.firestore.ktx.toObject
import kotlinx.coroutines.launch

class CartaViewModel : ViewModel() {

    private val cartaRepository = CartaRepository()
    private var cartaList: ArrayList<Carta> = ArrayList()

    private val _showMsg: MutableLiveData<String?> = MutableLiveData()
    val showMsg: LiveData<String?> = _showMsg

    private val _cartasList: MutableLiveData<ArrayList<Carta>> = MutableLiveData()
    val cartasList: LiveData<ArrayList<Carta>> = _cartasList

    fun loadCarta(uid: String?) {
        viewModelScope.launch {
            var result = cartaRepository.searchCarta(uid)
            result.let { resourceRemote ->
                when (resourceRemote){
                    is ResourceRemote.Success -> {
                        result.data?.documents?.forEach{ document ->
                            val carta = document.toObject<Carta>()
                            carta?.let { cartaList.add(carta) }
                        }
                        _cartasList.postValue(cartaList)
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