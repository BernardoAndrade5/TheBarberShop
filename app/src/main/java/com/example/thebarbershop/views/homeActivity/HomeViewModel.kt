package com.example.thebarbershop.views.homeActivity

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.thebarbershop.repositorys.AppointmentRepository
import com.example.thebarbershop.repositorys.AuthRepository
import com.example.thebarbershop.repositorys.BusinessRepository
import com.example.thebarbershop.uiStates.BaseUiState
import com.example.thebarbershop.uiStates.HomeUiState
import com.example.thebarbershop.utils.UiUtils
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject
constructor(
    private val appointmentsRepository: AppointmentRepository,
    private val businessRepository: BusinessRepository,
    private val authRepository: AuthRepository,
) : ViewModel() {
    private val _uiState = MutableStateFlow<BaseUiState<HomeUiState.Success>>(BaseUiState.Loading)
    val uiState: StateFlow<BaseUiState<HomeUiState.Success>> = _uiState.asStateFlow()
    init {
        loadData()
    }

    private fun loadData(){
        viewModelScope.launch {
            _uiState.value = BaseUiState.Loading
            try {
                val appointments = appointmentsRepository.fetchAppoitmentsFromFirestore()
                val business = businessRepository.fetchBusinessFromFirestore()
                val isAuthenticated = authRepository.isUserAuthenticated()
                val userEmail = authRepository.getCurrentUserEmail().toString()
                _uiState.value = BaseUiState.Success(
                    HomeUiState.Success(
                        appointments,
                        business,
                        userEmail,
                        isAuthenticated
                    )
                )
            } catch (e: Exception) {
                _uiState.value = BaseUiState.Error(e.message.toString())
            }
        }
    }

    fun logoutCurrentUser() {
        authRepository.signOutCurrentUser()
        loadData()
    }

    fun getCurrentUserId() : String?{
        return authRepository.getCurrentUserId()
    }
}

