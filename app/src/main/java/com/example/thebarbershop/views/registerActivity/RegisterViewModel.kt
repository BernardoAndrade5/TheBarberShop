package com.example.thebarbershop.views.registerActivity

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.thebarbershop.repositorys.AuthRepository
import com.example.thebarbershop.uiStates.BaseUiState
import com.example.thebarbershop.uiStates.LoginUiState
import com.example.thebarbershop.uiStates.RegisterUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject
constructor(
    private val authRepository: AuthRepository
): ViewModel(){

    private val _uiState = MutableStateFlow<RegisterUiState>(RegisterUiState.Idle)
    val uiState: StateFlow<RegisterUiState> = _uiState.asStateFlow()

    fun register (email : String, password: String){
        viewModelScope.launch {
            val registerSuccessfull = authRepository.registerWithEmailAndPassword(email, password)
            if (registerSuccessfull){
                _uiState.value = RegisterUiState.Success(Unit)
            }else{
                _uiState.value = RegisterUiState.Error("Invalid email or password")
            }
        }
    }
}