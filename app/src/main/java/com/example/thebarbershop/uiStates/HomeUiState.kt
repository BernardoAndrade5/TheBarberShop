package com.example.thebarbershop.uiStates

import com.example.thebarbershop.models.Appointment

data class HomeUiState(
    val appointments: List<Appointment> = emptyList(),
    val isLoading: Boolean = false,
    val errorMessage: String? = null
)