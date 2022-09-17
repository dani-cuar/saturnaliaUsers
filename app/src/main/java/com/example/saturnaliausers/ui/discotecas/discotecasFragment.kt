package com.example.saturnaliausers.ui.discotecas

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.saturnaliausers.R
import com.example.saturnaliausers.databinding.FragmentDiscotecasBinding

class discotecasFragment : Fragment() {

    private lateinit var discotecasBinding: FragmentDiscotecasBinding
    private lateinit var discotecasViewModel: DiscotecasViewModel
    private var discoExistAux = false

    private val args: discotecasFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        discotecasBinding = FragmentDiscotecasBinding.inflate(inflater, container, false)
        discotecasViewModel = ViewModelProvider(this)[DiscotecasViewModel::class.java]

        val disco = args.disco

        with(discotecasBinding){

            titleDiscoteca.text = disco.nombre

            eventButton.setOnClickListener{
                findNavController().navigate(discotecasFragmentDirections.actionNavigationDiscotecasToNavigationEventos())
            }
            cartaButton.setOnClickListener{
                findNavController().navigate(discotecasFragmentDirections.actionNavigationDiscotecasToNavigationCarta())
            }
            resenaButton.setOnClickListener{
                findNavController().navigate(discotecasFragmentDirections.actionNavigationDiscotecasToNavigationResena())
            }

            favoritesImageView5.setOnClickListener{
                if (discoExistAux)
                    Toast.makeText(context, "${disco.nombre} ya esta en favoritos",Toast.LENGTH_LONG).show()
                else{
                    discotecasBinding.favoritesImageView5.setImageDrawable(resources.getDrawable(R.drawable.ic_favorite))
                    discoExistAux = true
                    discotecasViewModel.addDiscoToFavorites(disco)
                }

            }
        }
        return discotecasBinding.root
    }
    override fun onResume() {
        super.onResume()
        (activity as AppCompatActivity).supportActionBar!!.hide()
    }
}