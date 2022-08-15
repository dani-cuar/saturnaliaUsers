package com.example.saturnaliausers.ui.login

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.findNavController
import com.example.saturnaliausers.R
import com.example.saturnaliausers.databinding.FragmentLoginBinding

class LoginFragment : Fragment() {

    private lateinit var loginBinding: FragmentLoginBinding
    private lateinit var loginviewModel: LoginViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        loginBinding = FragmentLoginBinding.inflate(inflater, container, false)
        loginviewModel = ViewModelProvider(this)[LoginViewModel::class.java]

        loginviewModel.errorMsg.observe(viewLifecycleOwner){ msg ->
            showErrorMessage(msg)
        }

        loginviewModel.loginSuccess.observe(viewLifecycleOwner){
            goToProfile()
        }

        with(loginBinding){
            enterButton.setOnClickListener{
                loginviewModel.validateFields(
                    editTextTextEmailAddress.text.toString(),
                    passwordEditTextTextPassword.text.toString()
                )
            }

            loginBinding.registerButton2.setOnClickListener{
                findNavController().navigate(LoginFragmentDirections.actionNavigationLoginToNavigationSignUp())
            }
        }

        return loginBinding.root
    }

    private fun goToProfile() {
        findNavController().navigate(LoginFragmentDirections.actionNavigationLoginToNavigationProfile())
    }

    private fun showErrorMessage(msg: String?) {
        Toast.makeText(requireActivity(),msg, Toast.LENGTH_SHORT).show()
    }

    override fun onResume() {
        super.onResume()
        (activity as AppCompatActivity).supportActionBar!!.hide()
    }

}