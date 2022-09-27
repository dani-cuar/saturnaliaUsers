package com.example.saturnaliausers.ui.createreview

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.saturnaliausers.data.ResourceRemote
import com.example.saturnaliausers.data.ReviewRepository
import com.example.saturnaliausers.model.Review
import kotlinx.coroutines.launch

class CreateReviewViewModel : ViewModel() {

    private val reviewRepository = ReviewRepository()

    private val _createReviewSuccess: MutableLiveData<String?> = MutableLiveData()
    val createReviewSuccess: LiveData<String?> = _createReviewSuccess

    private val _msg: MutableLiveData<String?> = MutableLiveData()
    val msg: LiveData<String?> = _msg

    fun checkFields(rating_: Float, author_: String, comment_: String, discoId: String) {
        if (author_.isEmpty() || comment_.isEmpty())
            _msg.value = "Todos los campos deben ser llenados"
        else {
            viewModelScope.launch {
                var review = Review(score = rating_, desc = comment_, authName = author_)
                var result = reviewRepository.createReview(review,discoId)
                result.let { resourceRemote ->
                    when(resourceRemote) {
                        is ResourceRemote.Success ->{
                            _createReviewSuccess.postValue(result.data)
                            _msg.postValue("Reseña agregada exitosamente")
                        }
                        is ResourceRemote.Error ->{
                            //TODO
                        }
                        else ->{}
                    }
                }
            }
        }
    }
}