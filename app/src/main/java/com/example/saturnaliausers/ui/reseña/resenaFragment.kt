package com.example.saturnaliausers.ui.reseña

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import com.example.saturnaliausers.R
import com.example.saturnaliausers.databinding.FragmentHomeBinding
import com.example.saturnaliausers.databinding.FragmentResenaBinding

class resenaFragment : Fragment() {

    private lateinit var resenaBinding: FragmentResenaBinding
    private lateinit var resenaViewModel: ResenaViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        resenaBinding = FragmentResenaBinding.inflate(inflater, container, false)
        resenaViewModel = ViewModelProvider(this)[ResenaViewModel::class.java]

        return resenaBinding.root
    }
    override fun onResume() {
        super.onResume()
        (activity as AppCompatActivity).supportActionBar!!.hide()
    }
}