package com.example.saturnaliausers.ui.signup

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.saturnaliausers.data.ResourceRemote
import com.example.saturnaliausers.data.UserRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class SignUpViewModel : ViewModel() {

    private val userRepository = UserRepository()

    private val _errorMsg: MutableLiveData<String?> = MutableLiveData()
    val errorMsg: LiveData<String?> = _errorMsg

    private val _registerSuccess: MutableLiveData<String?> = MutableLiveData()
    val registerSuccess: LiveData<String?> = _registerSuccess

    fun validateFields(email: String, password: String, repPasssword: String) {
        if(email.isEmpty() || password.isEmpty() || repPasssword.isEmpty()){
            _errorMsg.value = "Debe registrar todos los campos"
        }
        else{
            if(password != repPasssword)
                _errorMsg.value = "Las contraseñas deben ser iguales"
            else
                if (password.length < 6)
                    _errorMsg.value = "La contraseña debe tener minimo 6 caracteres"
                else
                    GlobalScope.launch(Dispatchers.IO) {
                        val result = userRepository.registerUser(email, password)
                        result.let { resourceRemote ->
                            when (resourceRemote){
                                is ResourceRemote.Success -> {
                                    _registerSuccess.postValue(result.data)
                                    _errorMsg.postValue("Registro exitoso")
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
}