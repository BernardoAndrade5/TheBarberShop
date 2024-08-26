package com.example.thebarbershop.views.homeActivity

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.thebarbershop.repositorys.AppointmentRepository
import com.example.thebarbershop.repositorys.AuthRepository
import com.example.thebarbershop.repositorys.BusinessRepository
import com.example.thebarbershop.uiStates.HomeUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject
constructor(
    private val appointmentsRepository: AppointmentRepository,
    private val businessRepository: BusinessRepository,
    private val authRepository: AuthRepository
) : ViewModel() {
    private val _uiState = MutableStateFlow(HomeUiState())
    val uiState: StateFlow<HomeUiState> = _uiState

    init {
        getDataBusinessandAppoitments()
    }

    private fun loadAppointmentsFlow(): Flow<HomeUiState> = flow {
        emit(HomeUiState(isLoading = true))
        try {
            val appointments = appointmentsRepository.fetchAppoitmentsFromFirestore()
            emit(HomeUiState(appointments = appointments, isLoading = false))
        } catch (e: Exception) {
            emit(HomeUiState(errorMessage = e.message, isLoading = false))
        }
    }

    private fun loadBusinessFlow(): Flow<HomeUiState> = flow {
        emit(HomeUiState(isLoading = true))
        try {
            val business = businessRepository.fetchBusinessFromFirestore()
            emit(HomeUiState(nextToYouBusiness = business, isLoading = false))
        } catch (e: Exception) {
            emit(HomeUiState(errorMessage = e.message, isLoading = false))
        }
    }

    private fun loadUserFlow(): Flow<HomeUiState> = flow {
        emit(HomeUiState(isLoading = true))
        try {
            // Emit initial state with isAuthenticated = false and userEmail = null
            emit(HomeUiState(isAuthenticated = false, userEmail = null.toString(), isLoading = false))

            val isAuthenticated = authRepository.isUserAuthenticated()
            val userEmail = authRepository.getCurrentUserEmail().toString()
            emit(HomeUiState(isAuthenticated = isAuthenticated, userEmail = userEmail, isLoading = false))
        } catch (e: Exception) {
            emit(HomeUiState(errorMessage = e.message, isLoading = false))
        }
    }

    fun getDataBusinessandAppoitments() {
        viewModelScope.launch {
            // Create flows separately and collect in a non-blocking way
            val appointmentsFlow = loadAppointmentsFlow()
            val businessFlow = loadBusinessFlow()
            val userFlow = loadUserFlow()

            combine(
                appointmentsFlow,
                businessFlow,
                userFlow
            ) { appointmentsState, businessState, userState ->
                HomeUiState(
                    appointments = appointmentsState.appointments,
                    nextToYouBusiness = businessState.nextToYouBusiness,
                    isAuthenticated = userState.isAuthenticated,
                    userEmail = userState.userEmail,
                    isLoading = appointmentsState.isLoading || businessState.isLoading || userState.isLoading,
                    errorMessage = appointmentsState.errorMessage ?: businessState.errorMessage ?: userState.errorMessage
                )
            }.collect { combinedState ->
                _uiState.value = combinedState
            }
        }
    }

    fun logoutCurrentUser() {
        authRepository.signOutCurrentUser()
    }

    fun getUserEmail(){
        authRepository.getCurrentUserEmail()
    }

}

