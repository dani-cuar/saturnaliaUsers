package com.example.saturnaliausers.ui.carta

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.saturnaliausers.R
import com.example.saturnaliausers.databinding.CartaItemBinding
import com.example.saturnaliausers.model.Carta
import com.squareup.picasso.Picasso
import java.util.ArrayList

class CartaAdapter(
    private val cartaList: ArrayList<Carta>, private val onItemClicked: (Carta) -> Unit
): RecyclerView.Adapter<CartaAdapter.CartaViewHolder> () {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CartaViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.carta_item, parent, false)
        return CartaViewHolder(view)
    }

    override fun onBindViewHolder(holder: CartaViewHolder, position: Int) {
        val carta = cartaList[position]
        holder.bind(carta)
        holder.itemView.setOnClickListener{onItemClicked(cartaList[position])}
    }

    override fun getItemCount(): Int = cartaList.size

    fun appendItems(newList: ArrayList<Carta>){
        cartaList.clear()
        cartaList.addAll(newList)
        notifyDataSetChanged()
    }

    class CartaViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){

        private val binding = CartaItemBinding.bind(itemView)
        fun bind(carta: Carta){
            with(binding){
                nameTextView.text = carta.productName
                precioTextView3.text = carta.productPrice.toString()
                if(carta.urlPhoto != null)
                    Picasso.get().load(carta.urlPhoto).into(imageView4)
            }
        }
    }
}