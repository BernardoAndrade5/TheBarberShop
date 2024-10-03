package com.example.thebarbershop.views.registerActivity

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.thebarbershop.models.User
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

    fun register (email : String, password: String, firstName : String, lastName : String, phoneNumber : String, age : String){
        viewModelScope.launch {
            val newUser = User(
                firstName = firstName,
                lastName = lastName,
                phoneNumber = phoneNumber,
                age = age
            )
            val registerSuccessfull = authRepository.registerWithEmailAndPassword(email, password, newUser)
            if (registerSuccessfull){
                _uiState.value = RegisterUiState.Success(Unit)
            }else{
                _uiState.value = RegisterUiState.Error("Invalid email or password")
            }
        }
    }
}