package com.example.saturnaliausers.ui.signup

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.findNavController
import com.example.saturnaliausers.databinding.FragmentSignUpBinding

class SignUpFragment : Fragment() {

    private lateinit var signUpBinding: FragmentSignUpBinding
    private lateinit var signUpViewModel: SignUpViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        signUpBinding = FragmentSignUpBinding.inflate(inflater, container, false)
        signUpViewModel = ViewModelProvider(this)[SignUpViewModel::class.java]

       signUpBinding.registerButton.setOnClickListener{
           findNavController().navigate(SignUpFragmentDirections.actionNavigationSignUpToNavigationLogin())
       }

        return signUpBinding.root
    }

    override fun onResume() {
        super.onResume()
        (activity as AppCompatActivity).supportActionBar!!.hide()
    }

}