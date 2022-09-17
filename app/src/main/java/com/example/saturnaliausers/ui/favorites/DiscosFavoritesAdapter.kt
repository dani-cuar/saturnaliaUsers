package com.example.saturnaliausers.ui.favorites

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.saturnaliausers.R
import com.example.saturnaliausers.databinding.DiscotecaItemBinding
import com.example.saturnaliausers.local.LocalDisco

class DiscosFavoritesAdapter(
    private val discosList: ArrayList<LocalDisco>,
    private val onItemClicked: (LocalDisco) -> Unit,
    private val onItemLongClicked: (LocalDisco) -> Unit,
        ): RecyclerView.Adapter<DiscosFavoritesAdapter.DiscosFavoritesViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DiscosFavoritesViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.discoteca_item, parent, false)
        return DiscosFavoritesViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: DiscosFavoritesViewHolder, position: Int) {
        val disco= discosList[position]
        holder.bindDisco(disco)
        holder.itemView.setOnClickListener{ onItemClicked(discosList[position])}
        holder.itemView.setOnLongClickListener{ onItemLongClicked(discosList[position])
            true
        }
    }

    override fun getItemCount(): Int = discosList.size

    fun appendItems(newList: ArrayList<LocalDisco>){
        discosList.clear()
        discosList.addAll(newList)
        notifyDataSetChanged()
    }

    class DiscosFavoritesViewHolder(itemView: View):RecyclerView.ViewHolder(itemView) {
        private val binding = DiscotecaItemBinding.bind(itemView)
        fun bindDisco(disco: LocalDisco) {
            with(binding){
                discoTextView3.text = disco.name
            }
        }

    }

}

