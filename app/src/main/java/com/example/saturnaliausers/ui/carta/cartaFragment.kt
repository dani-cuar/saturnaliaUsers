package com.example.saturnaliausers.ui.carta

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import com.example.saturnaliausers.R
import com.example.saturnaliausers.databinding.FragmentCartaBinding

class cartaFragment : Fragment() {

    private lateinit var cartaBinding: FragmentCartaBinding
    private lateinit var cartaViewModel: CartaViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        cartaBinding = FragmentCartaBinding.inflate(inflater, container, false)
        cartaViewModel = ViewModelProvider(this)[CartaViewModel::class.java]

        return cartaBinding.root
    }
    override fun onResume() {
        super.onResume()
        (activity as AppCompatActivity).supportActionBar!!.hide()
    }
}