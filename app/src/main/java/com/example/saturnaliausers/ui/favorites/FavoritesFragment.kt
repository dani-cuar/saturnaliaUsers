package com.example.saturnaliausers.ui.favorites

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.saturnaliausers.R
import com.example.saturnaliausers.databinding.FragmentFavoritesBinding
import com.example.saturnaliausers.databinding.FragmentHomeBinding
import com.example.saturnaliausers.local.LocalDisco
import com.example.saturnaliausers.ui.home.HomeViewModel

class FavoritesFragment : Fragment() {

    private lateinit var favoritesBinding: FragmentFavoritesBinding
    private lateinit var favoritesViewModel: FavoritesViewModel
    private lateinit var discosFavoritesAdapter: DiscosFavoritesAdapter
    private var discosList: ArrayList<LocalDisco> = ArrayList()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        favoritesBinding = FragmentFavoritesBinding.inflate(inflater,container,false)
        favoritesViewModel = ViewModelProvider(this)[FavoritesViewModel::class.java]

        favoritesViewModel.loadDiscos()

        discosFavoritesAdapter = DiscosFavoritesAdapter(discosList, onItemClicked = {onItemClicked(it)},onItemLongClicked = {onItemLongClicked(it)})

        favoritesBinding.favoritesRecyclerview.apply {
            layoutManager = LinearLayoutManager(this@FavoritesFragment.context)
            adapter = discosFavoritesAdapter
            setHasFixedSize(false)
        }

        favoritesViewModel.discosLoaded.observe(viewLifecycleOwner,{discosFavoritesAdapter.appendItems(it)})

        return favoritesBinding.root
    }

    private fun onItemLongClicked(localDisco: LocalDisco) {
        val alertDialog: AlertDialog? = activity?.let {
            val builder = AlertDialog.Builder(it)
            builder.apply {
                setMessage("¿Desea eliminar ${localDisco.name} de sus favoritos?")
                setPositiveButton(R.string.accept) { dialog, id ->
                    favoritesViewModel.deleteDisco(localDisco)
                    favoritesViewModel.loadDiscos()
                }
                setNegativeButton(R.string.Cancel) { dialog, id ->
                }
            }
            builder.create()
        }
        alertDialog?.show()
    }

    private fun onItemClicked(it: LocalDisco) {

    }

}