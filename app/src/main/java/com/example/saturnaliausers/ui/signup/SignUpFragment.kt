package com.example.saturnaliausers.ui.signup

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.findNavController
import com.example.saturnaliausers.databinding.FragmentSignUpBinding
import com.google.android.material.snackbar.Snackbar

class SignUpFragment : Fragment() {

    private lateinit var signUpBinding: FragmentSignUpBinding
    private lateinit var signUpViewModel: SignUpViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        signUpBinding = FragmentSignUpBinding.inflate(inflater, container, false)
        signUpViewModel = ViewModelProvider(this)[SignUpViewModel::class.java]

        signUpViewModel.errorMsg.observe(viewLifecycleOwner){ msg ->
            showErrorMessage(msg)            
        }

        signUpViewModel.registerSuccess.observe(viewLifecycleOwner){
            goToLogin()
        }

       with(signUpBinding){
           registerButton.setOnClickListener{
               signUpViewModel.validateFields(
                   emailTextEmailAddress2.text.toString(),
                   passwordTextTextPassword.text.toString(),
                   confirmPasswordTextPassword2.text.toString())
           }
       }

        return signUpBinding.root
    }

    private fun showErrorMessage(msg: String?) {
        Toast.makeText(requireActivity(),msg, Toast.LENGTH_SHORT).show()
    //Snackbar.make(activity, msg, Snackbar.LENGTH_INDEFINITE).show()
    }

    fun goToLogin(){
        findNavController().navigate(SignUpFragmentDirections.actionNavigationSignUpToNavigationLogin())
    }

    override fun onResume() {
        super.onResume()
        (activity as AppCompatActivity).supportActionBar!!.hide()
    }

}