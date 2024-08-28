package com.example.thebarbershop.uiStates

import com.example.thebarbershop.models.Appointment
import com.example.thebarbershop.models.Business
import com.example.thebarbershop.models.User

sealed class SearchUiState : BaseUiState<SearchUiState.Success>() {
    data class Success(
        val userEmail : String,
    )
}

