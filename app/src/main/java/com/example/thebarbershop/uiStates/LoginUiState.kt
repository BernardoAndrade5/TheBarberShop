package com.example.thebarbershop.uiStates

sealed class LoginUiState{
    data object Idle : LoginUiState()
    data object Loading : LoginUiState()
    data class Error(val message: String) : LoginUiState()
    data class Success(val data: Unit) : LoginUiState()
}