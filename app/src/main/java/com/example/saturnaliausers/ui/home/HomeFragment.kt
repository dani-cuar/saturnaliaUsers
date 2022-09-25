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
import com.example.saturnaliausers.databinding.FragmentHomeBinding
import com.example.saturnaliausers.model.Home

class HomeFragment : Fragment() {

    private lateinit var homeBinding: FragmentHomeBinding
    private lateinit var homeViewModel: HomeViewModel
    private lateinit var homeAdapter: HomeAdapter
    private var homeList: ArrayList<Home> = ArrayList()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        homeBinding = FragmentHomeBinding.inflate(inflater,container,false)
        homeViewModel = ViewModelProvider(this)[HomeViewModel::class.java]

        homeViewModel.loadRecomendados()

        homeViewModel.showMsg.observe(viewLifecycleOwner){ msg ->
            showMsg(msg)
        }

        homeViewModel.recomendadoList.observe(viewLifecycleOwner){ recomendadosList ->
            homeAdapter.appendItems(recomendadosList)
        }

        homeAdapter = HomeAdapter(homeList, onItemClicked = { onRecomendadosItemClicked(it)})

        homeBinding.recomendadosRecyclerview.apply {
            layoutManager = LinearLayoutManager(this@HomeFragment.requireContext())
            adapter = homeAdapter
            setHasFixedSize(false)
        }

        return homeBinding.root
    }

    private fun onRecomendadosItemClicked(it: Home) {

    }

    private fun showMsg(msg: String?) {
        Toast.makeText(requireActivity(), msg, Toast.LENGTH_LONG).show()
    }

    override fun onResume() {
        super.onResume()
        (activity as AppCompatActivity).supportActionBar!!.show()
    }


}