package com.example.saturnaliausers.ui.search

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.saturnaliausers.databinding.FragmentSearchBinding
import com.example.saturnaliausers.model.Carta
import com.example.saturnaliausers.model.Disco
import com.example.saturnaliausers.ui.carta.CartaAdapter
import com.example.saturnaliausers.ui.discotecas.discotecasFragmentDirections
import kotlin.collections.ArrayList

class SearchFragment : Fragment() {

    private lateinit var searchBinding: FragmentSearchBinding
    private lateinit var searchViewModel: SearchViewModel
    private lateinit var SearchAdapter: SearchAdapter
    private var discoList: ArrayList<Disco> = ArrayList()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        searchBinding = FragmentSearchBinding.inflate(inflater, container, false)
        searchViewModel = ViewModelProvider(this)[SearchViewModel::class.java]

        searchViewModel.loadDisco()

        searchViewModel.showMsg.observe(viewLifecycleOwner) { msg ->
            showMsg(msg)
        }

        searchViewModel.discosList.observe(viewLifecycleOwner) { discoList ->
            SearchAdapter.appendItems(discoList)
        }

        //searchViewModel.discoData.observe(viewLifecycleOwner) { discoName ->

        //}

        SearchAdapter = SearchAdapter(discoList, onItemClicked = { onDiscoItemClicked(it)})

        searchBinding.searchRecyclerView.apply {
            layoutManager = LinearLayoutManager(this@SearchFragment.requireContext())
            adapter = SearchAdapter
            setHasFixedSize(false)
        }

        with(searchBinding){
            searchButton.setOnClickListener{
                searchViewModel.validateData(searchEditText.text.toString())
            }
        }
        return searchBinding.root
    }
    private fun showMsg(msg: String?) {
        Toast.makeText(requireActivity(), msg, Toast.LENGTH_LONG).show()
    }

    private fun onDiscoItemClicked(disco: Disco) {
        findNavController().navigate(SearchFragmentDirections.actionNavigationSearchToNavigationDiscotecas(disco))
    }

    override fun onResume() {
        super.onResume()
        (activity as AppCompatActivity).supportActionBar!!.hide()
    }
}