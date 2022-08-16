package com.example.saturnaliausers.ui.favorites

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.saturnaliausers.databinding.FragmentFavoritesBinding
import com.example.saturnaliausers.databinding.FragmentHomeBinding
import com.example.saturnaliausers.ui.home.HomeViewModel

class FavoritesFragment : Fragment() {

    private lateinit var favoritesBinding: FragmentFavoritesBinding
    private lateinit var favoritesViewModel: FavoritesViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        favoritesBinding = FragmentFavoritesBinding.inflate(inflater,container,false)
        favoritesViewModel = ViewModelProvider(this)[FavoritesViewModel::class.java]

        return favoritesBinding.root
    }

}