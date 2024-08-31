package com.example.thebarbershop.uiStates

sealed class RegisterUiState{
    data object Idle : RegisterUiState()
    data object Loading : RegisterUiState()
    data class Error(val message: String) : RegisterUiState()
    data class Success(val data: Unit) : RegisterUiState()
}