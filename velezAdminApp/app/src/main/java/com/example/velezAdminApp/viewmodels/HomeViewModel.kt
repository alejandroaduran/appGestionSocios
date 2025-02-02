package com.example.velezAdminApp.viewmodels

import android.content.ContentValues
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.velezAdminApp.entidades.Partido
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await

class HomeViewModel : ViewModel() {
    // TODO: Implement the ViewModel
    val partidos = MutableLiveData<MutableList<Partido>>()
    // Access a Cloud Firestore instance from your Activity
    private val db = Firebase.firestore

    fun getPartidos(){
        viewModelScope.launch {
            partidos.value = getAll().toMutableList()
        }
    }
    private suspend fun getAll(): List<Partido> {

        var partidosList = arrayListOf<Partido>()
        val partidosRef = db.collection("partidos")
        // val query = questionRef

        try {
            val data = partidosRef.get().await()
            for (document in data) {
                Log.d(ContentValues.TAG, "${document.id} => ${document.data}")
                partidosList.add(document.toObject<Partido>())
            }
        } catch (e: Exception) {
            Log.d(ContentValues.TAG, "Error getting partidos", e)
        }
        return partidosList
    }
}