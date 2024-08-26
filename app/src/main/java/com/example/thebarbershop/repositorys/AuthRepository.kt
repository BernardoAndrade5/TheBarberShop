package com.example.thebarbershop.repositorys

import com.google.firebase.auth.FirebaseAuth
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
}