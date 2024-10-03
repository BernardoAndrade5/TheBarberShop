package com.example.thebarbershop.repositorys

import android.util.Log
import com.example.thebarbershop.data.FirebaseUtils
import com.example.thebarbershop.models.User
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext
import javax.inject.Inject

class AuthRepository @Inject
constructor(
    private val firebaseAuth: FirebaseAuth,
    private val firebaseUtils: FirebaseUtils
){
    fun isUserAuthenticated() : Boolean{
        return firebaseAuth.currentUser != null
    }

    fun getCurrentUserEmail() : String?{
        return firebaseAuth.currentUser?.email
    }

    fun getCurrentUserId() : String?{
        return firebaseAuth.currentUser?.uid
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

    suspend fun registerWithEmailAndPassword(email: String, password: String, user: User): Boolean {
        return withContext(Dispatchers.IO) {
            try {
                firebaseAuth.createUserWithEmailAndPassword(email, password).await()
                firebaseUtils.addUserToFirestore(user)
                return@withContext true // Explicit return within the lambda
            } catch (e: Exception) {
                Log.d("error", e.message.toString())
                return@withContext false // Explicit return within the lambda
            }
        }
    }
}