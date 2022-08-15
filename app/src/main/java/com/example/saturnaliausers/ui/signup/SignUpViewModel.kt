package com.example.saturnaliausers.ui.signup

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.saturnaliausers.data.ResourceRemote
import com.example.saturnaliausers.data.UserRepository
import com.example.saturnaliausers.model.User
import kotlinx.coroutines.launch

class SignUpViewModel : ViewModel() {

    private val userRepository = UserRepository()
    private lateinit var user: User

    private val _errorMsg: MutableLiveData<String?> = MutableLiveData()
    val errorMsg: LiveData<String?> = _errorMsg

    private val _registerSuccess: MutableLiveData<String?> = MutableLiveData()
    val registerSuccess: LiveData<String?> = _registerSuccess

    fun validateFields(email: String, password: String, repPasssword: String, name: String) {
        if(email.isEmpty() || password.isEmpty() || repPasssword.isEmpty() || name.isEmpty()){
            _errorMsg.value = "Debe registrar todos los campos"
        }
        else{
            if(password != repPasssword)
                _errorMsg.value = "Las contraseñas deben ser iguales"
            else
                if (password.length < 6)
                    _errorMsg.value = "La contraseña debe tener minimo 6 caracteres"
                else
                    viewModelScope.launch{
                        val result = userRepository.registerUser(email, password)
                        result.let { resourceRemote ->
                            when (resourceRemote){
                                is ResourceRemote.Success -> {
                                    user = User(result.data, name, email)
                                    createUser(user)
                                }
                                is ResourceRemote.Error -> {
                                    var msg = result.message
                                    when (result.message) {
                                        "The email address is already in use by another account." ->
                                            msg = "Ya existe una cuenta con ese correo electrónico"
                                        "The email address is badly formatted." -> msg = "Email está mal escrito"
                                        "A network error (such as timeout, interrupted connection or unreachable host) has occurred." ->
                                            msg = "Revise su conexión a internet"
                                    }
                                    _errorMsg.postValue(msg)
                                }
                                else -> {
                                    //dont use
                                }
                            }
                        }
                    }
            }
    }

    private fun createUser(user: User) {
        viewModelScope.launch {
            val result = userRepository.createUser(user)
            result.let { resourceRemote ->
                when(resourceRemote){
                    is ResourceRemote.Success -> {
                        _registerSuccess.postValue(result.data)
                        _errorMsg.postValue("Registro exitoso")
                    }
                    is ResourceRemote.Error -> {
                        var msg = result.message
                        _errorMsg.postValue(msg)
                    }
                    else -> {
                        /* dont use */
                    }
                }
            }
        }
    }
}