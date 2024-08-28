package com.example.thebarbershop.repositorys

import android.util.Log
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext
import javax.inject.Inject

class AuthRepository @Inject
constructor(
    private val firebaseAuth: FirebaseAuth
){
    fun isUserAuthenticated() : Boolean{
        return firebaseAuth.currentUser != null
    }

    fun getCurrentUserEmail() : String?{
        return firebaseAuth.currentUser?.email
    }

    fun signOutCurrentUser() {
        firebaseAuth.signOut()
    }

    suspend fun signInWithEmailAndPassword(email: String, password: String): Boolean {
        return withContext(Dispatchers.IO) {
            try {
                firebaseAuth.signInWithEmailAndPassword(email, password).await()
                return@withContext true // Explicit return within the lambda
            } catch (e: Exception) {
                Log.d("error", e.message.toString())
                return@withContext false // Explicit return within the lambda
            }
        }
    }
}