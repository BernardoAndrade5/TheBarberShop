package com.example.thebarbershop.views.homeActivity

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.thebarbershop.repositorys.AppointmentRepository
import com.example.thebarbershop.repositorys.BusinessRepository
import com.example.thebarbershop.uiStates.HomeUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class HomeViewModel (private val appoitmentsRepository: AppointmentRepository, private val businessRepository: BusinessRepository) : ViewModel() {
    private val _uiState = MutableStateFlow(HomeUiState())
    val uiState: StateFlow<HomeUiState> = _uiState

    fun loadAppointments(){
        _uiState.value = _uiState.value.copy(isLoading = true)

        viewModelScope.launch {
            try{
                val appointments = appoitmentsRepository.fetchAppoitmentsFromFirestore()
                _uiState.value = _uiState.value.copy(
                    appointments = appointments,
                    isLoading = false
                )
            }catch (e : Exception){
                _uiState.value = _uiState.value.copy(
                    errorMessage = e.message,
                    isLoading = false
                )
            }
        }
    }

    fun loadBusiness(){
        _uiState.value = _uiState.value.copy(isLoading = true)

        viewModelScope.launch {
            try{
                val business = businessRepository.fetchBusinessFromFirestore()
                _uiState.value = _uiState.value.copy(
                    nextToYouBusiness = business,
                    isLoading = false
                )
            }catch (e : Exception){
                _uiState.value = _uiState.value.copy(
                    errorMessage = e.message,
                    isLoading = false
                )
            }
        }
    }
}