package com.example.saturnaliausers.ui.reseña

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.saturnaliausers.data.ResourceRemote
import com.example.saturnaliausers.data.ReviewRepository
import com.example.saturnaliausers.model.Review
import com.google.firebase.firestore.ktx.toObject
import kotlinx.coroutines.launch

class ResenaViewModel : ViewModel() {

    private val reviewRepository = ReviewRepository()
    private val reviewList: ArrayList<Review> = ArrayList()

    private val _reviewsList: MutableLiveData<ArrayList<Review>> = MutableLiveData()
    val reviewsList: LiveData<ArrayList<Review>> = _reviewsList

    private val _msg: MutableLiveData<String?> = MutableLiveData()
    val msg: LiveData<String?> = _msg

    fun loadReviews(discoId: String) {
        viewModelScope.launch {
            reviewList.clear()
            var result = reviewRepository.loadRepository(discoId)
            result.let { resourceRemote ->
                when(resourceRemote){
                    is ResourceRemote.Success -> {
                        result.data?.documents?.forEach{ document ->
                            val review = document.toObject<Review>()
                            review?.let { reviewList.add(review) }
                        }
                        _reviewsList.postValue(reviewList)
                    }
                    is ResourceRemote.Error -> {
                        val msg = result.message
                        _msg.postValue(msg)
                    }
                    else -> {
                        //Don't use
                    }
                }
            }
        }
    }
}