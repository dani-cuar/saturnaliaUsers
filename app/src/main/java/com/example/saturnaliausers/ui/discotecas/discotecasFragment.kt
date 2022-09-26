package com.example.saturnaliausers.ui.discotecas

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.saturnaliausers.R
import com.example.saturnaliausers.databinding.FragmentDiscotecasBinding
import com.example.saturnaliausers.model.Profile

class discotecasFragment : Fragment() {

    private lateinit var discotecasBinding: FragmentDiscotecasBinding
    private lateinit var discotecasViewModel: DiscotecasViewModel
    private var profile = Profile()
    private var discoExistAux = false

    private val args: discotecasFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        discotecasBinding = FragmentDiscotecasBinding.inflate(inflater, container, false)
        discotecasViewModel = ViewModelProvider(this)[DiscotecasViewModel::class.java]

        val disco = args.disco

        discotecasViewModel.loadProfile(disco.uid)
        discotecasViewModel.searchDiscos(disco.uid)

        discotecasViewModel.profile.observe(viewLifecycleOwner) {
            profile = it
            with(discotecasBinding) {
                titleDiscoteca.text = it.name
                info.text = it.about
                addressTextView2.text = it.address
                emailTextView3.text = it.email
                phoneTextView4.text = it.phone
                ratingBar.numStars = it.rating.toString().toInt()
                ratingBar.rating = it.rating.toString().toFloat()
            }
        }

        discotecasViewModel.showMsg.observe(viewLifecycleOwner) { msg ->
            showMsg(msg)
        }

        discotecasViewModel.discoExist.observe(viewLifecycleOwner) { discoExist ->
            if (discoExist) {
                discotecasBinding.favoritesImageView5.setImageDrawable(resources.getDrawable(R.drawable.ic_favorite))
                discoExistAux = true
            } else {
                discotecasBinding.favoritesImageView5.setImageDrawable(resources.getDrawable(R.drawable.ic_favorite_border))
                discoExistAux = false
            }
        }

        with(discotecasBinding) {

            eventButton.setOnClickListener {
                findNavController().navigate(discotecasFragmentDirections.actionNavigationDiscotecasToNavigationEventos())
            }
            cartaButton.setOnClickListener {
                findNavController().navigate(discotecasFragmentDirections.actionNavigationDiscotecasToNavigationCarta(disco))
            }
            resenaButton.setOnClickListener {
                findNavController().navigate(discotecasFragmentDirections.actionNavigationDiscotecasToNavigationResena(disco))
            }

            favoritesImageView5.setOnClickListener {
                if (discoExistAux)
                    Toast.makeText(
                        context,
                        "${profile.name} ya esta en favoritos",
                        Toast.LENGTH_LONG
                    ).show()
                else {
                    discotecasBinding.favoritesImageView5.setImageDrawable(resources.getDrawable(R.drawable.ic_favorite))
                    discotecasViewModel.addDiscoToFavorites(profile)
                    discoExistAux = true
                }
            }
        }
        return discotecasBinding.root
    }

    private fun showMsg(msg: String?) {
        Toast.makeText(requireActivity(), msg, Toast.LENGTH_LONG).show()
    }

}


