package com.example.saturnaliausers.ui.carta

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.saturnaliausers.databinding.FragmentCartaBinding
import com.example.saturnaliausers.model.Carta
import kotlin.collections.ArrayList

class cartaFragment : Fragment() {

    private lateinit var cartaBinding: FragmentCartaBinding
    private lateinit var cartaViewModel: CartaViewModel
    private lateinit var cartaAdapter: CartaAdapter
    private var cartaList: ArrayList<Carta> = ArrayList()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        cartaBinding = FragmentCartaBinding.inflate(inflater, container, false)
        cartaViewModel = ViewModelProvider(this)[CartaViewModel::class.java]

        cartaViewModel.loadCarta()

        cartaViewModel.showMsg.observe(viewLifecycleOwner){msg ->
            showMsg(msg)
        }

        cartaViewModel.cartasList.observe(viewLifecycleOwner) { cartaList ->
            cartaAdapter.appendItems(cartaList)
        }

        cartaAdapter = CartaAdapter(cartaList, onItemClicked = { onCartaItemClicked(it)})

        cartaBinding.cartaRecyclerView.apply {
            layoutManager = LinearLayoutManager(this@cartaFragment.requireContext())
            adapter = cartaAdapter
            setHasFixedSize(false)
        }

        return cartaBinding.root
    }

    private fun showMsg(msg: String?) {
        Toast.makeText(requireActivity(), msg, Toast.LENGTH_LONG).show()
    }

    private fun onCartaItemClicked(carta: Carta) {

    }

    override fun onResume() {
        super.onResume()
        (activity as AppCompatActivity).supportActionBar!!.hide()
    }
}