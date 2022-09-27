package com.example.saturnaliausers.ui.reseña

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.ViewParent
import androidx.recyclerview.widget.RecyclerView
import com.example.saturnaliausers.R
import com.example.saturnaliausers.databinding.ReviewItemBinding
import com.example.saturnaliausers.model.Review
import java.text.FieldPosition

class ResenaAdapter(
    private val reviewList: ArrayList<Review>
) : RecyclerView.Adapter<ResenaAdapter.ResenaViewHolder> () {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) : ResenaViewHolder{
        val view = LayoutInflater.from(parent.context).inflate(R.layout.review_item, parent, false)
        return ResenaViewHolder(view)
    }

    override fun onBindViewHolder(holder: ResenaViewHolder, position: Int){
        val review = reviewList[position]
        holder.bind(review)
    }

    override fun getItemCount(): Int = reviewList.size

    fun appendItems(newList: ArrayList<Review>){
        reviewList.clear()
        reviewList.addAll(newList)
        notifyDataSetChanged()
    }

    class ResenaViewHolder (itemView: View) : RecyclerView.ViewHolder(itemView){
        private var binding = ReviewItemBinding.bind(itemView)

        fun bind(review: Review){
            with(binding){
                textViewAuthName.text = review.authName
                textViewReviewPunct.text = review.score.toString()
                textViewReviewText.text = review.desc
            }
        }
    }
}