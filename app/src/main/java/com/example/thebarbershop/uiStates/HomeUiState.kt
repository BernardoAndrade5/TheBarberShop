package com.example.thebarbershop.uiStates

import com.example.thebarbershop.models.Appointment
import com.example.thebarbershop.models.Business
import com.example.thebarbershop.models.User

sealed class HomeUiState{
    object Loading : HomeUiState()
    data class Success(
        val appointment: List<Appointment>,
        val nexToYouBusiness: List<Business>,
        val userEmail : String,
        val isAuthenticated : Boolean
    ) : HomeUiState()
    data class Error(val errorMessage: String) : HomeUiState()
}
