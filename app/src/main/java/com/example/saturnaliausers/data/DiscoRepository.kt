package com.example.saturnaliausers.data

import android.util.Log
import com.google.firebase.FirebaseNetworkException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestoreException
import com.google.firebase.firestore.QuerySnapshot
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.tasks.await

class DiscoRepository {

    private var auth: FirebaseAuth = Firebase.auth
    private var db = Firebase.firestore

    suspend fun searchDisco(): ResourceRemote<QuerySnapshot?>{
        return try {
            val docRef = db.collection("discos")
            val result = docRef?.get()?.await()
            Log.d("discotecas",result.toString())
            ResourceRemote.Success(data = result)
        } catch (e: FirebaseFirestoreException){
            ResourceRemote.Error(message = e.localizedMessage)
        } catch (e: FirebaseNetworkException){
            ResourceRemote.Error(message = e.localizedMessage)
        }
    }
}