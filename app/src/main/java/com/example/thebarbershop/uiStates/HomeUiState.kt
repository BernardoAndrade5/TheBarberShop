package com.example.thebarbershop.uiStates

import com.example.thebarbershop.models.Appointment
import com.example.thebarbershop.models.Business

data class HomeUiState(
    val appointments: List<Appointment> = emptyList(),
    val nextToYouBusiness : List<Business> = emptyList(),
    val isLoading: Boolean = false,
    val errorMessage: String? = null
)