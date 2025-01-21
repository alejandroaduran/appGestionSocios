package com.example.velezAdminApp.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class LoginViewModel : ViewModel() {

    private val auth: FirebaseAuth = Firebase.auth
    private val db = Firebase.firestore
    var user : MutableLiveData<FirebaseUser?> = MutableLiveData(auth.currentUser)

    fun login(email : String, pass : String) {
        auth.signInWithEmailAndPassword(email, pass)
            .addOnSuccessListener{
                val inputEmail = auth.currentUser?.email
                if(inputEmail != null && inputEmail == "admin@admin.com"){
                    user.value = auth.currentUser
                }
            }
            .addOnFailureListener {

            }
    }
}