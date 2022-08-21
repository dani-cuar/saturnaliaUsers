package com.example.saturnaliausers.ui.discotecas

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.findNavController
import com.example.saturnaliausers.databinding.FragmentDiscotecasBinding

class discotecasFragment : Fragment() {

    private lateinit var discotecasBinding: FragmentDiscotecasBinding
    private lateinit var discotecasViewModel: DiscotecasViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        discotecasBinding = FragmentDiscotecasBinding.inflate(inflater, container, false)
        discotecasViewModel = ViewModelProvider(this)[DiscotecasViewModel::class.java]


        with(discotecasBinding){
            eventButton.setOnClickListener{
                findNavController().navigate(discotecasFragmentDirections.actionNavigationDiscotecasToNavigationEventos())
            }
            cartaButton.setOnClickListener{
                findNavController().navigate(discotecasFragmentDirections.actionNavigationDiscotecasToNavigationCarta())
            }
            resenaButton.setOnClickListener{
                findNavController().navigate(discotecasFragmentDirections.actionNavigationDiscotecasToNavigationResena())
            }
        }
        return discotecasBinding.root
    }
    override fun onResume() {
        super.onResume()
        (activity as AppCompatActivity).supportActionBar!!.hide()
    }
}