package com.example.saturnaliausers.data

import android.util.Log
import com.example.saturnaliausers.model.Review
import com.google.firebase.FirebaseNetworkException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestoreException
import com.google.firebase.firestore.QuerySnapshot
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.tasks.await

class ReviewRepository {

    private var auth: FirebaseAuth = Firebase.auth
    private var db = Firebase.firestore

    suspend fun loadRepository(discoId: String): ResourceRemote<QuerySnapshot> {
        return try {
            val docRef = db.collection("discos").document(discoId).collection("Reviews")
            val result = docRef.get().await()
            ResourceRemote.Success(data = result)
        } catch (e: FirebaseFirestoreException){
            ResourceRemote.Error(message = e.localizedMessage)
        } catch (e: FirebaseNetworkException){
            ResourceRemote.Error(message = e.localizedMessage)
        }
    }

    suspend fun createReview(review: Review, discoId: String): ResourceRemote<String?> {
        return try {
            val path = db.collection("discos").document(discoId).collection("Reviews")
            val documentReview = path?.document(auth.uid.toString())
            review.id = documentReview?.id
            documentReview?.id?.let { path.document(it).set(review).await() }
            ResourceRemote.Success(data = review.id)
        } catch (e: FirebaseFirestoreException) {
            ResourceRemote.Error(message = e.localizedMessage)
        } catch (e: FirebaseNetworkException){
            ResourceRemote.Error(message = e.localizedMessage)
        }
    }
}