package com.example.saturnaliausers.ui.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.saturnaliausers.data.ResourceRemote
import com.example.saturnaliausers.data.UserRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class LoginViewModel : ViewModel() {

    private val userRepository = UserRepository()

    private val _errorMsg: MutableLiveData<String?> = MutableLiveData()
    val errorMsg: LiveData<String?> = _errorMsg

    private val _loginSuccess: MutableLiveData<String?> = MutableLiveData()
    val loginSuccess: LiveData<String?> = _loginSuccess

    fun validateFields(email: String, password: String) {
        if(email.isEmpty() || password.isEmpty()){
            _errorMsg.value = "Debe registrar todos los campos"
        }
        else
            if (password.length < 6)
                _errorMsg.value = "La contraseña debe tener minimo 6 caracteres"
            else
                GlobalScope.launch (Dispatchers.IO){
                    val result = userRepository.loginUser(email,password)
                    result.let{ resourceRemote ->
                        when (resourceRemote){
                            is ResourceRemote.Success-> {
                                _loginSuccess.postValue(result.data)
                                _errorMsg.postValue("Bienvenido")
                            }
                            is ResourceRemote.Error -> {
                                var msg = result.message
                                when (result.message) {
                                    "The email address is badly formatted." -> msg = "Email está mal escrito"
                                    "A network error (such as timeout, interrupted connection or unreachable host) has occurred." ->
                                        msg = "Revise su conexión a internet"
                                    "There is no user record corresponding to this identifier. The user may have been deleted." ->
                                        msg = "Correo no registrado"
                                    "The password is invalid or the user does not have a password." ->
                                        msg = "Contraseña inválida"

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