package com.example.velezAdminApp.viewmodels

import android.app.DownloadManager
import android.content.ContentValues
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.velezAdminApp.entidades.Partido
import com.google.firebase.firestore.Query
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await

class ABMViewModel : ViewModel() {

    private val db = Firebase.firestore

    private suspend fun obtenerPartidoID(partido: Partido) : String{
        var partidoID : String = ""
        val partidosRef = db.collection("partidos")
        try {
            val partido = partidosRef.whereEqualTo("id",partido.id).get().await()
            for (document in partido) {
                Log.d(ContentValues.TAG, "${document.id} => ${document.data}")
                partidoID = document.id
            }
        }catch (e: Exception){
            Log.w(ContentValues.TAG, "Error getting documents: ", e)
        }
        return partidoID
    }

    suspend fun editarPartido(partidoID : String, updatePartido: Partido){
        val partidosRef = db.collection("partidos")
        Log.d(ContentValues.TAG, "ID " + partidoID)

        val updatePartidoHash = hashMapOf(
            "rival" to updatePartido.rival,
            "dia" to updatePartido.dia,
            "hora" to updatePartido.hora,
            "mes" to updatePartido.mes,
            "fecha" to updatePartido.fecha,
            "sectorEste" to updatePartido.sectorEste,
            "sectorNorte" to updatePartido.sectorNorte,
            "sectorOeste" to updatePartido.sectorOeste,
            "sectorSurAlta" to updatePartido.sectorSurAlta,
            "sectorSurBaja" to updatePartido.sectorSurBaja,
            "sectorvisitante" to updatePartido.sectorVisitante,
            "torneo" to updatePartido.torneo,
            "estaDisp" to updatePartido.estaDisp
        )

        try{
            partidosRef.document("/${partidoID}").update(updatePartidoHash as Map<String, Any>).await()
            Log.d(ContentValues.TAG, "DocumentSnapshot successfully written!")
        }catch(e: Exception){
            Log.w(ContentValues.TAG, "Error writing document", e)
        }
    }
    suspend fun crearPartido(nuevoPartido: Partido){
        val partidosRef = db.collection("partidos")
        try {
            val lastPartido  = partidosRef.orderBy("id", Query.Direction.DESCENDING).limit(1).get().await()
            val lastPartidoObj = lastPartido.documents[0].toObject<Partido?>()

            if(lastPartidoObj != null){
                nuevoPartido.id =  lastPartidoObj.id + 1
            }else{
                nuevoPartido.id =  1
            }

            val nuevoPartidoMap = hashMapOf(
                "rival" to nuevoPartido.rival,
                "dia" to nuevoPartido.dia,
                "hora" to nuevoPartido.hora,
                "mes" to nuevoPartido.mes,
                "fecha" to nuevoPartido.fecha,
                "id" to nuevoPartido.id,
                "sectorEste" to nuevoPartido.sectorEste,
                "sectorNorte" to nuevoPartido.sectorNorte,
                "sectorOeste" to nuevoPartido.sectorOeste,
                "sectorSurAlta" to nuevoPartido.sectorSurAlta,
                "sectorSurBaja" to nuevoPartido.sectorSurBaja,
                "sectorvisitante" to nuevoPartido.sectorVisitante,
                "torneo" to nuevoPartido.torneo,
                "estaDisp" to nuevoPartido.estaDisp
            )

            partidosRef.add(nuevoPartidoMap).await()
            Log.d(ContentValues.TAG, "DocumentSnapshot written")
        }catch (e: Exception) {
            Log.w(ContentValues.TAG, "Error adding document", e)
        }
    }
    fun editar(partido: Partido, updatePartido : Partido){
        viewModelScope.launch {
            val id = obtenerPartidoID(partido)
            editarPartido(id, updatePartido)
        }
    }

    fun agregar(nuevoPartido : Partido){
        viewModelScope.launch {
            crearPartido(nuevoPartido)
        }
    }
}