package com.example.saturnaliausers.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.LinearSnapHelper
import androidx.recyclerview.widget.SnapHelper
import com.example.saturnaliausers.databinding.FragmentHomeBinding
import com.example.saturnaliausers.model.Disco
import com.example.saturnaliausers.model.Eventos
import com.example.saturnaliausers.model.Home
import com.example.saturnaliausers.ui.eventos.EventosAdapter
import com.example.saturnaliausers.ui.search.SearchAdapter

class HomeFragment : Fragment() {

    private lateinit var homeBinding: FragmentHomeBinding
    private lateinit var homeViewModel: HomeViewModel
    private lateinit var homeAdapter: HomeAdapter
    private lateinit var eventosAdapter: EventosAdapter
    private lateinit var searchAdapter: SearchAdapter

    private var eventRecomendadoList: ArrayList<Eventos> = ArrayList()
    private var discosList: ArrayList<Disco> = ArrayList()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        homeBinding = FragmentHomeBinding.inflate(inflater,container,false)
        homeViewModel = ViewModelProvider(this)[HomeViewModel::class.java]

        homeViewModel.loadEventos()
        homeViewModel.loadDiscos()

        homeViewModel.discosList.observe(viewLifecycleOwner){ discoRecomend ->
            searchAdapter.appendItems(discoRecomend)
        }
        homeViewModel.eventRecomendadoList.observe(viewLifecycleOwner){ eventRecomendado ->
            eventosAdapter.appendItems(eventRecomendado)
        }

        homeViewModel.showMsg.observe(viewLifecycleOwner){ msg ->
            showMsg(msg)
        }

        eventosAdapter = EventosAdapter(eventRecomendadoList, onItemClicked = { onRecomendadosItemClicked(it)})

        searchAdapter = SearchAdapter(discosList, onItemClicked = {onDiscosItemClicked(it)})
        
        homeBinding.recomendadosRecyclerview.apply {
            layoutManager = LinearLayoutManager(this@HomeFragment.requireContext(), LinearLayoutManager.HORIZONTAL, false)
            adapter = searchAdapter
            setHasFixedSize(false)
            val snapHelper: SnapHelper = LinearSnapHelper()
            snapHelper.attachToRecyclerView(this)
        }
        homeBinding.eventsRecomendedRecyclerview.apply {
            layoutManager = LinearLayoutManager(this@HomeFragment.requireContext(), LinearLayoutManager.HORIZONTAL, false)
            adapter = eventosAdapter
            setHasFixedSize(false)
            val snapHelper: SnapHelper = LinearSnapHelper()
            snapHelper.attachToRecyclerView(this)
        }

        return homeBinding.root
    }

    private fun onDiscosItemClicked(it: Disco) {

    }


    private fun onRecomendadosItemClicked(it: Eventos) {

    }

    private fun showMsg(msg: String?) {
        Toast.makeText(requireActivity(), msg, Toast.LENGTH_LONG).show()
    }

    override fun onResume() {
        super.onResume()
        (activity as AppCompatActivity).supportActionBar!!.hide()
    }


}