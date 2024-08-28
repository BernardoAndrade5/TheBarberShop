package com.example.thebarbershop.uiStates

import com.example.thebarbershop.views.loginActivity.LoginViewModel

sealed class LoginUiState{
    data object Idle : LoginUiState()
    data object Loading : LoginUiState()
    data class Error(val message: String) : LoginUiState()
    data class Success(val data: Unit) : LoginUiState()
}