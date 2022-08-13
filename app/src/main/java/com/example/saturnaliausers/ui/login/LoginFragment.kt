package com.example.saturnaliausers.ui.login

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
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

        loginBinding.enterButton.setOnClickListener{
            findNavController().navigate(LoginFragmentDirections.actionNavigationLoginToNavigationProfile())
        }

        loginBinding.registerButton2.setOnClickListener{
            findNavController().navigate(LoginFragmentDirections.actionNavigationLoginToNavigationSignUp())
        }
        return loginBinding.root
    }

    override fun onResume() {
        super.onResume()
        (activity as AppCompatActivity).supportActionBar!!.hide()
    }

}