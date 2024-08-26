package com.example.thebarbershop.views.searchActivity

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.thebarbershop.repositorys.AppointmentRepository
import com.example.thebarbershop.repositorys.AuthRepository
import com.example.thebarbershop.repositorys.BusinessRepository
import com.example.thebarbershop.uiStates.HomeUiState
import com.example.thebarbershop.uiStates.SearchUiState
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
class SearchViewModel @Inject
constructor(
    private val authRepository: AuthRepository
) : ViewModel() {
    private val _uiState = MutableStateFlow(SearchUiState())
    val uiState: StateFlow<SearchUiState> = _uiState

    init {
    }





    private fun loadUserFlow(): Flow<SearchUiState> = flow {
        emit(SearchUiState(isLoading = true))
        try {
            // Emit initial state with isAuthenticated = false and userEmail = null
            emit(SearchUiState(isAuthenticated = false, userEmail = null.toString(), isLoading = false))

            val isAuthenticated = authRepository.isUserAuthenticated()
            val userEmail = authRepository.getCurrentUserEmail().toString()
            emit(SearchUiState(isAuthenticated = isAuthenticated, userEmail = userEmail, isLoading = false))
        } catch (e: Exception) {
            emit(SearchUiState(errorMessage = e.message, isLoading = false))
        }
    }


    fun logoutCurrentUser() {
        authRepository.signOutCurrentUser()
    }

    fun getUserEmail(){
        authRepository.getCurrentUserEmail()
    }

}

