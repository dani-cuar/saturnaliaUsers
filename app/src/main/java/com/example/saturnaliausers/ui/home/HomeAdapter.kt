package com.example.saturnaliausers.ui.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.saturnaliausers.R
import com.example.saturnaliausers.databinding.RecomendadosItemBinding
import com.example.saturnaliausers.model.Home
import java.util.ArrayList

class HomeAdapter (
    private val homeList: ArrayList<Home>, private val onItemClicked: (Home) -> Unit
): RecyclerView.Adapter<HomeAdapter.HomeViewHolder> () {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.recomendados_item, parent, false)
        return HomeViewHolder(view)
    }

    override fun onBindViewHolder(holder: HomeViewHolder, position: Int) {
        val recomendados = homeList[position]
        holder.bind(recomendados)
        holder.itemView.setOnClickListener{onItemClicked(homeList[position])}
    }

    override fun getItemCount(): Int = homeList.size

    fun appendItems(newList: ArrayList<Home>){
        homeList.clear()
        homeList.addAll(newList)
        notifyDataSetChanged()
    }

    class HomeViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){

        private val binding = RecomendadosItemBinding.bind(itemView)
        fun bind(home: Home){
            with(binding){
                titleDisco.text = home.nombre
            }
        }
    }
}