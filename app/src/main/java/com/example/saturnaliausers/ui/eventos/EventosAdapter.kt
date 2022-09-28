package com.example.saturnaliausers.ui.eventos

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.saturnaliausers.R
import com.example.saturnaliausers.databinding.EventosItemBinding
import com.example.saturnaliausers.model.Eventos
import com.squareup.picasso.Picasso
import java.util.ArrayList

class EventosAdapter (
    private val eventosList: ArrayList<Eventos>, private val onItemClicked: (Eventos) -> Unit
): RecyclerView.Adapter<EventosAdapter.EventosViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EventosViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.eventos_item, parent, false)
        return EventosViewHolder(view)
    }

    override fun onBindViewHolder(holder: EventosViewHolder, position: Int) {
        val eventos = eventosList[position]
        holder.bind(eventos)
        holder.itemView.setOnClickListener{onItemClicked(eventosList[position])}
    }

    override fun getItemCount(): Int = eventosList.size

    fun appendItems(newList: ArrayList<Eventos>){
        eventosList.clear()
        eventosList.addAll(newList)
        notifyDataSetChanged()
    }

    class EventosViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

        private val binding = EventosItemBinding.bind(itemView)
        fun bind(eventos: Eventos){
            with(binding){
                nameEventsTextView.text = eventos.name
                coverTextView2.text = eventos.cover
                fechaTextView3.text = eventos.date
                descriptionTextView4.text = eventos.description
                horaTextView5.text = eventos.time
                if(eventos.urlPhoto != null)
                    Picasso.get().load(eventos.urlPhoto).into(imageView3)
            }
        }
    }
}