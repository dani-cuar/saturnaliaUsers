package com.example.saturnaliausers.ui.search

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.saturnaliausers.R
import com.example.saturnaliausers.databinding.DiscotecaItemBinding
import com.example.saturnaliausers.model.Disco
import java.util.ArrayList

class SearchAdapter (
    private val discoList: ArrayList<Disco>, private val onItemClicked: (Disco) -> Unit
): RecyclerView.Adapter<SearchAdapter.SearchViewHolder> () {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.discoteca_item, parent, false)
        return SearchViewHolder(view)
    }

    override fun onBindViewHolder(holder: SearchViewHolder, position: Int) {
        val disco = discoList[position]
        holder.bind(disco)
        holder.itemView.setOnClickListener{onItemClicked(discoList[position])}
    }

    override fun getItemCount(): Int = discoList.size

    fun appendItems(newList: ArrayList<Disco>){
        discoList.clear()
        discoList.addAll(newList)
        notifyDataSetChanged()
    }

    class SearchViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        private val binding = DiscotecaItemBinding.bind(itemView)
        fun bind(disco: Disco){
            with(binding){
                discoTextView3.text = disco.name
            }
        }
    }
}
