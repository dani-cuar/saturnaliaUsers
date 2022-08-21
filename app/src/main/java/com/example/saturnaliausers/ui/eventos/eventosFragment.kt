package com.example.saturnaliausers.ui.eventos

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity

import com.example.saturnaliausers.databinding.FragmentEventosBinding

class eventosFragment : Fragment() {

    private lateinit var eventosBinding: FragmentEventosBinding
    private lateinit var eventosViewModel: EventosViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        eventosBinding = FragmentEventosBinding.inflate(inflater, container, false)
        eventosViewModel = ViewModelProvider(this)[EventosViewModel::class.java]

        return eventosBinding.root
    }
    override fun onResume() {
        super.onResume()
        (activity as AppCompatActivity).supportActionBar!!.hide()
    }
}