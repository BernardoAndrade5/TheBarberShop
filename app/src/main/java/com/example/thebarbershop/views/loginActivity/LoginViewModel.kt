package com.example.thebarbershop.views.loginActivity

import androidx.lifecycle.ViewModel
import com.example.thebarbershop.repositorys.AuthRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject
constructor(
    private val authRepository: AuthRepository
): ViewModel(){

}