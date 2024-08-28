package com.example.thebarbershop.uiStates

sealed class BaseUiState<out T> {
    data object Loading : BaseUiState<Nothing>()
    data class Success<T>(val data: T) : BaseUiState<T>()
    data class Error(val message: String) : BaseUiState<Nothing>()
}