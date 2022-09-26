package com.example.saturnaliausers.ui.discotecas

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.saturnaliausers.data.DiscoRepository
import com.example.saturnaliausers.data.ResourceRemote
import com.example.saturnaliausers.local.LocalDisco
import com.example.saturnaliausers.local.repository.LocalDiscoRepository
import com.example.saturnaliausers.model.Disco
import com.example.saturnaliausers.model.Profile
import com.google.firebase.firestore.ktx.toObject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.sql.Types.NULL

class DiscotecasViewModel : ViewModel() {

    val localDiscoRepository = LocalDiscoRepository()
    val discoRepository = DiscoRepository()

    private val _discoExist : MutableLiveData<Boolean> = MutableLiveData()
    val discoExist: LiveData<Boolean> = _discoExist

    private val _profile: MutableLiveData<Profile> = MutableLiveData()
    val profile: LiveData<Profile> = _profile

    private val _showMsg: MutableLiveData<String?> = MutableLiveData()
    val showMsg: LiveData<String?> = _showMsg

    fun addDiscoToFavorites(profile: Profile) {

        val localDisco = LocalDisco(
            id= NULL,
            name = profile.name)

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

    fun loadProfile(uid: String?) {
        viewModelScope.launch {
            val result = discoRepository.loadProfile(uid)
            result.let { resourceRemote ->
                when(resourceRemote){
                    is ResourceRemote.Success -> {
                        _profile.postValue(result.data?.documents?.get(0)?.toObject<Profile>())
                    }
                    is ResourceRemote.Error -> {
                        val msg= result.message
                        _showMsg.postValue(msg)
                    }
                    else -> {
                        //DON'T USE
                    }
                }
            }
        }
    }
}

