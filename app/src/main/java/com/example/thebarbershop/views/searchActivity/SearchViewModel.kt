package com.example.thebarbershop.views.searchActivity

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.thebarbershop.repositorys.AppointmentRepository
import com.example.thebarbershop.repositorys.AuthRepository
import com.example.thebarbershop.repositorys.BusinessRepository
import com.example.thebarbershop.uiStates.BaseUiState
import com.example.thebarbershop.uiStates.SearchUiState
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
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val authRepository: AuthRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow<BaseUiState<SearchUiState.Success>>(BaseUiState.Loading)
    val uiState: StateFlow<BaseUiState<SearchUiState.Success>> = _uiState.asStateFlow()

    init {
        loadData()
    }

    private fun loadData(){
        viewModelScope.launch {
            _uiState.value = BaseUiState.Loading
            try {
                val userEmail = authRepository.getCurrentUserEmail().toString()
                _uiState.value = BaseUiState.Success(
                    SearchUiState.Success(
                        userEmail
                    )
                )
            }catch (e : Exception){
                _uiState.value = BaseUiState.Error(e.message.toString())
            }
        }
    }

    fun logoutCurrentUser() {
        authRepository.signOutCurrentUser()
    }

    fun getUserEmail() {
        authRepository.getCurrentUserEmail()
    }
}

