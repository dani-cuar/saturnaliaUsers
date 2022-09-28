package com.example.saturnaliausers.ui.eventos

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager

import com.example.saturnaliausers.databinding.FragmentEventosBinding
import com.example.saturnaliausers.model.Eventos

class eventosFragment : Fragment() {

    private lateinit var eventosBinding: FragmentEventosBinding
    private lateinit var eventosViewModel: EventosViewModel
    private lateinit var eventosAdapter: EventosAdapter
    private val args: eventosFragmentArgs by navArgs()
    private var eventosList: ArrayList<Eventos> = ArrayList()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        eventosBinding = FragmentEventosBinding.inflate(inflater, container, false)
        eventosViewModel = ViewModelProvider(this)[EventosViewModel::class.java]

        var disco = args.disco

        eventosViewModel.loadEventos(disco.uid!!)



        eventosViewModel.showMsg.observe(viewLifecycleOwner){ msg ->
            showMsg(msg)
        }

        eventosViewModel.eventoList.observe(viewLifecycleOwner){ eventosList ->
            eventosAdapter.appendItems(eventosList)
        }

        eventosAdapter = EventosAdapter(eventosList, onItemClicked = {onEventoItemClicked(it)})

        eventosBinding.eventosRecyclerview.apply {
            layoutManager = LinearLayoutManager(this@eventosFragment.requireContext())
            adapter = eventosAdapter
            setHasFixedSize(false)
        }

        return eventosBinding.root
    }

    private fun onEventoItemClicked(evento: Eventos) {

    }

    private fun showMsg(msg: String?) {
        Toast.makeText(requireActivity(), msg, Toast.LENGTH_LONG).show()
    }

    override fun onResume() {
        super.onResume()
        (activity as AppCompatActivity).supportActionBar!!.hide()
    }
}