package com.example.saturnaliausers.data

import com.google.firebase.FirebaseNetworkException
import com.google.firebase.firestore.FirebaseFirestoreException
import com.google.firebase.firestore.QuerySnapshot
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.tasks.await

class EventosRepository {

    private var db = Firebase.firestore

    suspend fun searchEvento(discoId: String): ResourceRemote<QuerySnapshot?>{
        return try {
            val docRef = db.collection("discos").document(discoId).collection("Events")
            val result = docRef?.get()?.await()
            ResourceRemote.Success(data = result)
        } catch (e: FirebaseFirestoreException){
            ResourceRemote.Error(message = e.localizedMessage)
        } catch (e: FirebaseNetworkException){
            ResourceRemote.Error(message = e.localizedMessage)
        }
    }

    suspend fun loadEventRecomend(): ResourceRemote<QuerySnapshot?>{
        return try {
            val docRef = db.collection("eventReco")
            val result = docRef?.get()?.await()
            ResourceRemote.Success(data = result)
        } catch (e: FirebaseFirestoreException){
            ResourceRemote.Error(message = e.localizedMessage)
        } catch (e: FirebaseNetworkException){
            ResourceRemote.Error(message = e.localizedMessage)
        }
    }
}