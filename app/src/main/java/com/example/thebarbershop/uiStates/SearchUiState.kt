package com.example.thebarbershop.uiStates

import com.example.thebarbershop.models.Appointment
import com.example.thebarbershop.models.Business
import com.example.thebarbershop.models.User

data class SearchUiState(
    val appointments: List<Appointment> = emptyList(),
    val nextToYouBusiness : List<Business> = emptyList(),
    val userEmail : String = "",
    val isAuthenticated : Boolean = false,
    val isLoading: Boolean = false,
    val errorMessage: String? = null
)